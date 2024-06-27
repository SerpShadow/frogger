import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Represent a segment of the river populated with turtles, extending the functionality of the {@link River} class.
 * Turtles provide platforms for the player to stand on, but may also pose a challenge if they are submerging turtles.
 */
public class RiverTurtle extends River {

    private final ArrayList<Turtle> turtles = new ArrayList<>();
    private final RIVER_TYPE riverType;

    /**
     * Constructs a new {@code RiverTurtle} segment of river populated with turtles.
     *
     * @param pApplet      The processing applet used for drawing.
     * @param turtleSprite The sprite image used to represent turtles.
     * @param riverType    The river type that influences various properties of the turtles.
     * @param amount       The number of turtles to generate in this segment.
     * @param speed        The horizontal speed at which the turtles move.
     */
    public RiverTurtle(PApplet pApplet, PImage turtleSprite, RIVER_TYPE riverType, int amount, double speed) {
        super(riverType, speed);
        this.riverType = riverType;

        boolean submergingTurtleRendered = false;
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

    /**
     * Checks if a collision occurs between the player's frog and any turtle in this segment.
     *
     * @param frogHitbox The hitbox of the player's frog character to determine collisions.
     * @return {@code true} if a collision is detected with a turtle, {@code false} otherwise.
     */
    @Override
    public boolean checkCollision(Hitbox frogHitbox) {
        for (Turtle turtle : turtles) {
            if (UTILS.isColliding(frogHitbox, turtle.getHitboxAbsolute(riverType.getPositionY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draws all turtles in this river segment to the provided {@link PApplet}.
     *
     * @param pApplet The Processing applet used for drawing.
     */
    @Override
    public void draw(PApplet pApplet) {
        // Translate to river's Y position for drawing
        pApplet.pushMatrix();
        pApplet.translate(0, riverType.getPositionY());
        turtles.forEach(turtle -> turtle.draw(pApplet));
        pApplet.popMatrix();
    }
}
