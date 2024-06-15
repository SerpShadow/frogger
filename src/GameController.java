import General.CONSTANTS;
import General.Hitbox;
import General.LevelData;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import Text.Text;
import Text.TEXT_COLOR;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class GameController {

    private Game game;

    private Home home;
    private Grass medianStrip;
    private Grass startStrip;
    private FrogManual frog;

    private boolean[] homesOccupied = new boolean[5];
    private River[] rivers = new River[5];
    private Lane[] lanes = new Lane[5];

    private int lives = 5;
    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF, ANCHORTYPE.TOP_LEFT);
    private final Text timeText;
    private long remainingSeconds;
    private long endTime = System.currentTimeMillis() + 60 * 1000;

//    private ArrayList<Log> logs = new ArrayList();

    public GameController(PApplet pApplet, Game game) {
        this.game = game;

        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        home = new Home(pApplet, spriteMap);
        generateRivers(pApplet);
        medianStrip = new Grass(pApplet, spriteMap, UTILS.chunksToPixel(8));
        generateVehicles(pApplet);
        startStrip = new Grass(pApplet, spriteMap, UTILS.chunksToPixel(14));
        frog = new FrogManual(pApplet);
        multiSprite.addFrames(pApplet, spriteMap, UTILS.chunksToPixel(13), 16, 14);
        timeText = new Text(pApplet, new Point(UTILS.chunksToPixel(12), CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.YELLOW, "Time");

    }

    public void keyPressed(int keyCode, Game game) {
        frog.keyPressed(keyCode, game);
    }

    private void generateRivers(PApplet pApplet) {
        LevelData levelData = UTILS.getCurrentLevelData();
        rivers = new River[5];
        rivers[0] = new RiverLog(pApplet, RIVER_TYPE.FIRST, levelData.getRiverFirstAmount(), levelData.getRiverFirstSpeed());
        rivers[1] = new RiverTurtle(pApplet, RIVER_TYPE.SECOND, levelData.getRiverSecondAmount(), levelData.getRiverSecondSpeed());
        rivers[2] = new RiverLog(pApplet, RIVER_TYPE.THIRD, levelData.getRiverThirdAmount(), levelData.getRiverThirdSpeed());
        rivers[3] = new RiverLog(pApplet, RIVER_TYPE.FOURTH, levelData.getRiverFourthAmount(), levelData.getRiverFourthSpeed());
        rivers[4] = new RiverTurtle(pApplet, RIVER_TYPE.FIFTH, levelData.getRiverFifthAmount(), levelData.getRiverFifthSpeed());
    }

    private void generateVehicles(PApplet pApplet) {
        lanes = new Lane[5];
        lanes[0] = new LaneTruck(pApplet);
        lanes[1] = new LaneRaceCar(pApplet);
        lanes[2] = new LaneCoupe(pApplet);
        lanes[3] = new LaneBulldozer(pApplet);
        lanes[4] = new LaneDuneBuggy(pApplet);
    }

    private void restartTimer() {
        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            endTime = System.currentTimeMillis() + 60 * 1000;
        });
    }

    private void handleDeath() {
        frog.onDeath();
        lives -= 1;

        if (lives < 1) {
            game.setGameStage(GAME_STAGE.SCORE_RANKING);
        } else {
            restartTimer();
        }
    }

    private void checkCollision() {
        if (!frog.isDead()) {
            Hitbox frogHitbox = frog.getHitbox();

            for (Lane lane : lanes) {
                if (lane.checkCollision(frogHitbox)) {
                    handleDeath();
                }
            }

            for (River river : rivers) {
                int frogPositionY = frog.getPositionCurrent().getY();
                int riverPositionY = river.getRiverType().getPositionY();
                if (riverPositionY == frogPositionY) {
                    if (river.isFrogOnFloating(frogHitbox)) {
                        frog.increaseMovementXBy(river.getSpeed());
                    } else {
                        handleDeath();
                    }
                }
            }
        }
    }

    private void calcRemainingTime() {
        remainingSeconds = (endTime - System.currentTimeMillis()) / 1000;
    }

    public void draw(PApplet pApplet) {
        home.draw(pApplet);
        for (River river : rivers) {
            river.draw(pApplet);
        }
        medianStrip.draw(pApplet);
        for (Lane lane : lanes) {
            lane.draw(pApplet);
        }
        startStrip.draw(pApplet);

        // Footer - START
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(15));
        multiSprite.setFrame(2);
        for (int i = 0; i < lives; i++) {
            multiSprite.draw(pApplet, new Point(i * CONSTANTS.CHUNK_SIZE_HALF, 0));
        }
        multiSprite.setFrame(0);
        for (int i = 0; i < UTILS.getCurrentLevel(); i++) {
            multiSprite.draw(pApplet, new Point(CONSTANTS.PIXEL_HORIZONTAL - CONSTANTS.CHUNK_SIZE - i * CONSTANTS.CHUNK_SIZE_HALF, 0));
        }

        if (!frog.isDead()) {
            calcRemainingTime();

            if (remainingSeconds == 0) {
                handleDeath();
            }
        }

        for (int i = 0; i < remainingSeconds; i += 4) {
            Point point = new Point(CONSTANTS.PIXEL_HORIZONTAL - UTILS.chunksToPixel(2) - CONSTANTS.CHUNK_SIZE_HALF - i / 4 * CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF);
            int spareSeconds = (int) remainingSeconds - i;
            if (remainingSeconds % 4 > 0 && spareSeconds < 4) {
                multiSprite.setFrame(14 - spareSeconds * 2);
                multiSprite.draw(pApplet, point);

            } else {
                multiSprite.setFrame(6);
                multiSprite.draw(pApplet, point);
            }
        }
        timeText.draw(pApplet);
        pApplet.popMatrix();
        // Footer - END

        frog.draw(pApplet);

        checkCollision();
    }
}
