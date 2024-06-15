import General.CONSTANTS;
import General.Hitbox;
import General.LevelData;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class GameController {

    private Game game;

    private Home home;
    private Grass medianStrip;
    private Grass startStrip;
    private Frog frog;

    private boolean[] homesOccupied = new boolean[5];
    private River[] rivers = new River[5];
    private Lane[] lanes = new Lane[5];

    private int lives = 7;
    private long endTime = System.currentTimeMillis() + 60 * 1000;

//    private ArrayList<Log> logs = new ArrayList();

    public GameController(PApplet pApplet, Game game) {
        this.game = game;

        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        home = new Home(pApplet, spriteMap);
        generateRivers(pApplet);
        medianStrip = new Grass(pApplet, spriteMap, 8 * CONSTANTS.CHUNK_SIZE);
        generateVehicles(pApplet);
        startStrip = new Grass(pApplet, spriteMap, 14 * CONSTANTS.CHUNK_SIZE);

        frog = new Frog(pApplet, CONSTANTS.SPEED_FROG);
    }

    public void keyPressed(int keyCode, Game game) {
        frog.keyPressed(keyCode, game);
    }

    private void generateRivers(PApplet pApplet) {
        LevelData levelData = UTILS.getCurrentLevelData();
        rivers = new River[5];
        rivers[0]=new RiverLog(pApplet, RIVER_TYPE.FIRST, levelData.getRiverFirstAmount(), levelData.getRiverFirstSpeed());
        rivers[1]=new RiverTurtle(pApplet, RIVER_TYPE.SECOND, levelData.getRiverSecondAmount(), levelData.getRiverSecondSpeed());
        rivers[2]=new RiverLog(pApplet, RIVER_TYPE.THIRD, levelData.getRiverThirdAmount(), levelData.getRiverThirdSpeed());
        rivers[3]=new RiverLog(pApplet, RIVER_TYPE.FOURTH, levelData.getRiverFourthAmount(), levelData.getRiverFourthSpeed());
        rivers[4]=new RiverTurtle(pApplet, RIVER_TYPE.FIFTH, levelData.getRiverFifthAmount(), levelData.getRiverFifthSpeed());
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
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        endTime = System.currentTimeMillis() + 60 * 1000;
                    }
                },
                CONSTANTS.RESPAWN_DELAY * 1000
        );
    }

    private void handleDeath() {
        frog.onDeath();
        lives -= 1;
        restartTimer();
    }

    private void checkCollision() {
        if (!frog.isDead()) {
            Hitbox frogHitbox = frog.getHitbox();

            for (Lane lane : lanes) {
                if (lane.checkCollision(frogHitbox)) {
//                    handleDeath();
                }
            }

            for (River river : rivers) {
                int frogPositionY = frog.getPositionCurrent().getY();
                int riverPositionY = river.getRiverType().getPositionY();
                if (riverPositionY == frogPositionY) {
                    if (river.isFrogOnFloating(frogHitbox)) {
                        frog.setPositionAbsoluteX(frog.getPositionAbsolute().getX() + river.getSpeed());
                        frog.setPositionCurrentX(frog.getPositionCurrent().getX() + river.getSpeed());
                    } else {
                        handleDeath();
                    }


                }

//                if (river.checkCollision)
            }


        }
    }

    public void draw(PApplet pApplet) {
        long remainingTime = endTime - System.currentTimeMillis();
        long remainingSeconds = remainingTime / 1000;
//        System.out.println("TIME:" + remainingSeconds);


//        rivers.forEach(river -> river.draw(pApplet));
        for (River river : rivers) {
            river.draw(pApplet);
        }
        for (Lane lane : lanes) {
            lane.draw(pApplet);
        }

        home.draw(pApplet);
        medianStrip.draw(pApplet);
        startStrip.draw(pApplet);
        frog.draw(pApplet);

        checkCollision();
    }
}
