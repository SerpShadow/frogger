import General.CONSTANTS;
import General.Hitbox;
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

//        generateFloatings(spriteMap);

        home = new Home(pApplet, spriteMap);
        medianStrip = new Grass(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE);
        generateRivers(pApplet);
        generateVehicles(pApplet);
        startStrip = new Grass(pApplet, spriteMap, 13 * CONSTANTS.CHUNK_SIZE);

        frog = new Frog(pApplet, CONSTANTS.SPEED_FROG);
    }

    public void keyPressed(int keyCode, Game game) {
        frog.keyPressed(keyCode, game);
    }

    private void generateFloatings(PImage spriteMap) {

//        int LOG_TOP = 2;
//        for (int i = 0; i < LOG_TOP; i++) {
//            Log log = new Log(pApplet, spriteMap, 4, 4, UTILS.generateVehicleXPosition(4 * CONSTANTS.CHUNK_SIZE, i, LOG_TOP));
//            logs.add(log);
//        }
//
//        int LOG_MIDDLE = 1;
//        for (int i = 0; i < LOG_MIDDLE; i++) {
//            Log log = new Log(pApplet, spriteMap, 6, 6, UTILS.generateVehicleXPosition(6 * CONSTANTS.CHUNK_SIZE, i, LOG_MIDDLE));
//            logs.add(log);
//        }
//
//        int LOG_BOTTOM = 2;
//        for (int i = 0; i < LOG_BOTTOM; i++) {
//            Log log = new Log(pApplet, spriteMap, 3, 7, UTILS.generateVehicleXPosition(3 * CONSTANTS.CHUNK_SIZE, i, LOG_BOTTOM));
//            logs.add(log);
//        }

//        Log log1 = new Log(pApplet, spriteMap, 4, 4, UTILS.generateVehicleXPosition(3 * CONSTANTS.CHUNK_SIZE, 0, 2));
//
//
//        Log log2 = new Log(pApplet, spriteMap, 6, 6, 88);
//        Log log3 = new Log(pApplet, spriteMap, 3, 7, 22);
//        logs.add(log1);
//        logs.add(log2);
//        logs.add(log3);
    }

    private void generateRivers(PApplet pApplet) {
        rivers = new ArrayList<>();

        RiverLog riverLog = new RiverLog(pApplet);
        rivers.add(riverLog);
    }

    private void generateVehicles(PApplet pApplet) {
        lanes = new ArrayList<>();

        LaneTruck laneTruck = new LaneTruck(pApplet);
        lanes.add(laneTruck);

        LaneRaceCar laneRaceCar = new LaneRaceCar(pApplet);
        lanes.add(laneRaceCar);

        LaneCoupe laneCoupe = new LaneCoupe(pApplet);
        lanes.add(laneCoupe);

        LaneBulldozer laneBulldozer = new LaneBulldozer(pApplet);
        lanes.add(laneBulldozer);

        LaneDuneBuggy laneDuneBuggy = new LaneDuneBuggy(pApplet);
        lanes.add(laneDuneBuggy);
    }

    private void checkCollision() {
        if (!frog.isDead()) {
            Hitbox froggerHitbox = frog.getHitbox();

            for (Lane lane : lanes) {
                if (lane.checkCollision(froggerHitbox)) {
                    frog.onDeath();
                    game.onDeath();
                }
            }
        }
    }

    public void draw(PApplet pApplet) {
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
