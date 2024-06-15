import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class RiverTurtle extends River {

    private ArrayList<Turtle> turtles = new ArrayList<>();

    private final RIVER_TYPE riverType;


    public RiverTurtle(PApplet pApplet, PImage turtleSprite, RIVER_TYPE riverType, int amount, double speed) {
        super(pApplet, riverType, speed);
        this.riverType = riverType;

        boolean submergingTurtleRendered = false;
//        boolean isSubmerging = false;
        for (int i = 0; i < amount; i++) {
            boolean isSubmerging = false;
            if (!submergingTurtleRendered) {
                double probability = 1.0 / amount * (i + 1);
                isSubmerging = UTILS.randomBoolean(probability);
                if (isSubmerging) {
                    submergingTurtleRendered = true;
                }
            }

            int xPosition = UTILS.generateVehicleXPosition(riverType.getWidth(), i, amount);
            turtles.add(new Turtle(pApplet, turtleSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition, isSubmerging));
            turtles.add(new Turtle(pApplet, turtleSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition + CONSTANTS.PIXEL_HORIZONTAL, isSubmerging));
        }


    }

    public boolean checkCollision(Hitbox frogHitbox) {
        for (Turtle turtle : turtles) {
            if (UTILS.isColliding(frogHitbox, turtle.getHitboxAbsolute(riverType.getPositionY()))) {
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
        turtles.forEach(turtle -> turtle.draw(pApplet));
        pApplet.popMatrix();
    }
}
