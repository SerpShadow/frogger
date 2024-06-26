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

/**
 * Controls the game logic, including managing game entities, handling user input, and maintaining game state.
 */
public class GameController {

    private final PApplet pApplet;
    private final Game game;

    private final Home home;
    private final Grass medianStrip;
    private final Grass startStrip;
    private final FrogManual frogManual;

    private River[] rivers = new River[5];
    private Lane[] lanes = new Lane[5];

    private int lives = 3;
    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE_HALF, CONSTANTS.CHUNK_SIZE_HALF, ANCHORTYPE.TOP_LEFT);
    private Text remainingTimeText;
    private final Text footerTimeText;
    private long remainingSeconds;
    private long endTime = System.currentTimeMillis() + 60 * 1000;

    private int frogPrevMaxPositionY = UTILS.chunksToPixel(14);


    /**
     * Initializes the game controller with necessary game components and UI elements.
     *
     * @param pApplet The Processing applet used for drawing.
     * @param game The main game object managing different game screens.
     */
    public GameController(PApplet pApplet, Game game) {
        this.pApplet = pApplet;
        this.game = game;

        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");
        PImage snakeSprite = pApplet.loadImage("assets/snake.png");

        home = new Home(pApplet);
        generateRivers(pApplet);
        medianStrip = new Grass(pApplet, spriteMap, snakeSprite, 8);
        generateVehicles(pApplet);
        startStrip = new Grass(pApplet, spriteMap, 14);
        frogManual = new FrogManual(pApplet);
        multiSprite.addFrames(pApplet, spriteMap, UTILS.chunksToPixel(13), 16, 14);
        footerTimeText = new Text(pApplet, new Point(UTILS.chunksToPixel(12), CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.YELLOW, "Time");

    }


    /**
     * Processes keypress events to control the frog or manage game functions.
     *
     * @param keyCode The keycode representing the key pressed by the user.
     */
    public void keyPressed(int keyCode) {
        if (keyCode == 75) {
            UTILS.setCurrentLevel(0);
            startNextLevel();
        } else if (keyCode == 76) {
            startNextLevel();
        } else {
            frogManual.keyPressed(keyCode);
        }
    }

    private void generateRivers(PApplet pApplet) {
        LevelData levelData = UTILS.getCurrentLevelData();
        PImage logSprite = pApplet.loadImage("assets/log.png");
        PImage turtleSprite = pApplet.loadImage("assets/turtle.png");
        rivers = new River[5];
        rivers[0] = new RiverLog(pApplet, logSprite, RIVER_TYPE.FIRST, levelData.getRiverFirstAmount(), levelData.getRiverFirstSpeed());
        rivers[1] = new RiverTurtle(pApplet, turtleSprite, RIVER_TYPE.SECOND, levelData.getRiverSecondAmount(), levelData.getRiverSecondSpeed());
        rivers[2] = new RiverLog(pApplet, logSprite, RIVER_TYPE.THIRD, levelData.getRiverThirdAmount(), levelData.getRiverThirdSpeed());
        rivers[3] = new RiverLog(pApplet, logSprite, RIVER_TYPE.FOURTH, levelData.getRiverFourthAmount(), levelData.getRiverFourthSpeed());
        rivers[4] = new RiverTurtle(pApplet, turtleSprite, RIVER_TYPE.FIFTH, levelData.getRiverFifthAmount(), levelData.getRiverFifthSpeed());
    }

    private void generateVehicles(PApplet pApplet) {
        LevelData levelData = UTILS.getCurrentLevelData();
        lanes = new Lane[5];
        lanes[0] = new Lane(pApplet, VEHICLE_TYPE.TRUCK, levelData.getVehicleTruckAmount(), levelData.getVehicleTruckSpeed());
        lanes[1] = new Lane(pApplet, VEHICLE_TYPE.RACE_CAR, levelData.getVehicleRaceCarAmount(), levelData.getVehicleRaceCarSpeed());
        lanes[2] = new Lane(pApplet, VEHICLE_TYPE.COUPE, levelData.getVehicleCoupeAmount(), levelData.getVehicleCoupeSpeed());
        lanes[3] = new Lane(pApplet, VEHICLE_TYPE.BULLDOZER, levelData.getVehicleBulldozerAmount(), levelData.getVehicleBulldozerSpeed());
        lanes[4] = new Lane(pApplet, VEHICLE_TYPE.DUNE_BUGGY, levelData.getVehicleDuneBuggyAmount(), levelData.getVehicleDuneBuggySpeed());

    }

    /**
     * Restarts the timer for the current level or game state, used when replaying or advancing levels.
     */
    private void restartTimer() {
        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            endTime = System.currentTimeMillis() + 60 * 1000;
        });
    }

    private void resetFrogPosition() {
        frogPrevMaxPositionY = UTILS.chunksToPixel(14);
        frogManual.onRestart();
    }

    private void handleHome(Home home) {
        game.increaseScoreBy(CONSTANTS.POINTS_PER_FROG);

        remainingTimeText = new Text(pApplet, new Point(UTILS.chunksToPixel(5.5), UTILS.chunksToPixel(8.5)), TEXT_COLOR.RED, "Time " + String.format("%02d", remainingSeconds));

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY + 2000, TimeUnit.MILLISECONDS).execute(() -> {
            remainingTimeText = null;
        });

        if (home.getAllHomesOccupied()) {
            startNextLevel();
        } else {
            resetFrogPosition();
        }
    }

    private void handleDeath() {
        frogPrevMaxPositionY = UTILS.chunksToPixel(14);
        frogManual.onDeath();
        lives -= 1;

        if (lives < 0) {
            game.gameOver();
        } else {
            restartTimer();
        }
    }

    private void startNextLevel() {
        game.increaseScoreBy(CONSTANTS.POINTS_PER_LEVEL);
        game.increaseScoreBy(CONSTANTS.POINTS_PER_SECOND * (int) remainingSeconds);

        UTILS.increaseLevel();
        generateRivers(pApplet);
        medianStrip.generateSnakes(pApplet);
        generateVehicles(pApplet);

        resetFrogPosition();

        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(home::resetOccupiedHomes);

    }

    private void checkCollision() {
        if (!frogManual.isDead()) {
            Hitbox frogHitbox = frogManual.getHitboxAbsolute(0);
            int frogPositionY = frogManual.getPositionY();

            int homePositionY = 32; // in PX
            if (frogPositionY == homePositionY) {
                if (home.checkCollision(frogHitbox)) {
                    handleHome(home);
                } else {
                    handleDeath();
                }
            }


            for (River river : rivers) {
                int riverPositionY = river.getRiverType().getPositionY();
                if (frogPositionY == riverPositionY) {
                    if (river.checkCollision(frogHitbox)) {
                        frogManual.increaseFixedMovementXBy(river.getMovementSpeed());
                    } else {
                        handleDeath();
                    }
                }
            }

//            if (frogPositionY == UTILS.chunksToPixel(8)) {
            if (medianStrip.checkCollision(frogHitbox)) {
                handleDeath();
            }
//            }

            if (frogPositionY > UTILS.chunksToPixel(8)) {
                for (Lane lane : lanes) {
                    if (lane.checkCollision(frogHitbox)) {
                        handleDeath();
                    }
                }
            }

            // check if frog is outside the allowed area
            if (!UTILS.isColliding(frogHitbox, new Hitbox(0, CONSTANTS.PIXEL_HORIZONTAL, CONSTANTS.PIXEL_VERTICAL, 0))) {
                handleDeath();
            }
        }
    }

    private void calcRemainingTime() {
        remainingSeconds = (endTime - System.currentTimeMillis()) / 1000;
    }

    private void drawFooter(PApplet pApplet) {
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

        if (!frogManual.isDead()) {
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
        footerTimeText.draw(pApplet);
        pApplet.popMatrix();
    }

    /**
     * Draws the game UI, entities, and handles game logic updates.
     * This method should be called repeatedly in the draw loop of the Processing applet.
     *
     * @param pApplet The Processing applet used for drawing.
     */
    public void draw(PApplet pApplet) {

        if (frogManual.getDestinationY() < frogPrevMaxPositionY) {
            frogPrevMaxPositionY = frogManual.getDestinationY();
            game.increaseScoreBy(CONSTANTS.POINTS_PER_STEP);
        }

        home.draw(pApplet);
        for (River river : rivers) {
            river.draw(pApplet);
        }
        medianStrip.draw(pApplet);
        for (Lane lane : lanes) {
            lane.draw(pApplet);
        }
        startStrip.draw(pApplet);

        drawFooter(pApplet);

        if (remainingTimeText != null) {
            pApplet.pushMatrix();
            pApplet.fill(0, 0, 0);
            pApplet.rect(UTILS.chunksToPixel(5.5), UTILS.chunksToPixel(8.5), UTILS.chunksToPixel(3.5), UTILS.chunksToPixel(0.5));
            pApplet.popMatrix();

            remainingTimeText.draw(pApplet);
        }
        frogManual.draw(pApplet);

        checkCollision();
    }
}
