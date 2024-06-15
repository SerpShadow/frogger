import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;

import java.util.ArrayList;

public class RiverTurtle extends River {

    private ArrayList<Turtle> turtles = new ArrayList<>();

    private final RIVER_TYPE riverType;


    public RiverTurtle(PApplet pApplet, RIVER_TYPE riverType, int amount, double speed) {
        super(pApplet, riverType, speed);
        this.riverType = riverType;

        for (int i = 0; i < amount; i++) {

            int xPosition = UTILS.generateVehicleXPosition(riverType.getWidth(), i, amount);
            turtles.add(new Turtle(pApplet, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition, false));
            turtles.add(new Turtle(pApplet, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition + CONSTANTS.PIXEL_HORIZONTAL, false));
        }


    }

    public boolean isFrogOnFloating(Hitbox frogHitbox) {
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
