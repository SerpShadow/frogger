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
    private ArrayList<River> rivers = new ArrayList();
    private ArrayList<Lane> lanes = new ArrayList(); // todo: switch to array

//    private ArrayList<Log> logs = new ArrayList();

    public GameController(PApplet pApplet, Game game) {
        this.game = game;

        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        home = new Home(pApplet, spriteMap);
        generateRivers(pApplet);
        medianStrip = new Grass(pApplet, spriteMap, 8 * CONSTANTS.CHUNK_SIZE);
//        generateVehicles(pApplet);
        startStrip = new Grass(pApplet, spriteMap, 14 * CONSTANTS.CHUNK_SIZE);

        frog = new Frog(pApplet, CONSTANTS.SPEED_FROG);
    }

    public void keyPressed(int keyCode, Game game) {
        frog.keyPressed(keyCode, game);
    }

    private void generateRivers(PApplet pApplet) {
        LevelData levelData = UTILS.getCurrentLevelData();
        rivers = new ArrayList<>();
        rivers.add(new RiverLog(pApplet, RIVER_TYPE.FIRST, levelData.getRiverFirstAmount(), levelData.getRiverFirstSpeed()));
        rivers.add(new RiverTurtle(pApplet, RIVER_TYPE.SECOND, levelData.getRiverSecondAmount(), levelData.getRiverSecondSpeed()));
        rivers.add(new RiverLog(pApplet, RIVER_TYPE.THIRD, levelData.getRiverThirdAmount(), levelData.getRiverThirdSpeed()));
        rivers.add(new RiverLog(pApplet, RIVER_TYPE.FOURTH, levelData.getRiverFourthAmount(), levelData.getRiverFourthSpeed()));
        rivers.add(new RiverTurtle(pApplet, RIVER_TYPE.FIFTH, levelData.getRiverFifthAmount(), levelData.getRiverFifthSpeed()));
    }

    private void generateVehicles(PApplet pApplet) {
        lanes = new ArrayList<>();
        lanes.add(new LaneTruck(pApplet));
        lanes.add(new LaneRaceCar(pApplet));
        lanes.add(new LaneCoupe(pApplet));
        lanes.add(new LaneBulldozer(pApplet));
        lanes.add(new LaneDuneBuggy(pApplet));
    }

    private void handleDeath() {
        frog.onDeath();
        game.onDeath();
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
        rivers.forEach(river -> river.draw(pApplet));
//        for (River river : rivers) {
//            river.draw(pApplet);
//        }
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
