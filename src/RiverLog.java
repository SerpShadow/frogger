import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class RiverLog extends River {

    private ArrayList<Log> logs = new ArrayList<>();

    private final RIVER_TYPE riverType;

    private Crocodile crocodile;


    public RiverLog(PApplet pApplet, PImage logSprite, RIVER_TYPE riverType, int amount, double speed) {
        super(pApplet, riverType, speed);
        this.riverType = riverType;

        for (int i = 0; i < amount; i++) {
            boolean isCrocodile = UTILS.randomBoolean(0.9);
            if (isCrocodile && crocodile == null) {

            }
            int xPosition = UTILS.generateVehicleXPosition(riverType.getWidth(), i, amount);
            logs.add(new Log(pApplet, logSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition));
            logs.add(new Log(pApplet, logSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition + CONSTANTS.PIXEL_HORIZONTAL));
        }


    }

    /**
     * "Is Frog on Floating"
     *
     * @param frogHitbox Hitbox of active frog
     * @return boolean
     */
    public boolean checkCollision(Hitbox frogHitbox) {
        for (Log log : logs) {
            if (UTILS.isColliding(frogHitbox, log.getHitboxAbsolute(riverType.getPositionY()))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        pApplet.pushMatrix();
        pApplet.translate(0, riverType.getPositionY());
        logs.forEach(log -> log.draw(pApplet));
        pApplet.popMatrix();
    }
}
