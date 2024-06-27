import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * A class that extends the {@code River} class, representing a segment of the river that is populated with logs.
 * Players can interact with these logs to navigate the river.
 * The segment may also contain a crocodile, adding an obstacle for the player.
 */
public class RiverLog extends River {

    private final ArrayList<Log> logs = new ArrayList<>();
    private final RIVER_TYPE riverType;
    private Crocodile crocodile;

    /**
     * Constructs a new {@code RiverLog} segment with specified parameters.
     * Generates both log and optional crocodile obstacles for the player.
     *
     * @param pApplet   The Processing applet used for drawing. Must not be {@code null}.
     * @param logSprite The image sprite for logs. Must not be {@code null}.
     * @param riverType The type of river, which may affect visuals or behavior. Must not be {@param null}.
     * @param amount    The number of logs to be generated in this segment.
     * @param speed     The speed at which logs move across the river.
     */
    public RiverLog(PApplet pApplet, PImage logSprite, RIVER_TYPE riverType, int amount, double speed) {
        super(riverType, speed);
        this.riverType = riverType;

        for (int i = 0; i < amount; i++) {
            boolean isCrocodile = UTILS.randomBoolean(0); // needs to be changed to eg 0.5 once implemented
            if (isCrocodile && crocodile == null) {
                // todo: implement crocodile stuff
            } else {
                // Create and place logs
                int xPosition = UTILS.generateVehicleXPosition(riverType.getWidth(), i, amount);
                logs.add(new Log(pApplet, logSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition));
                // Duplicate for continuous appearance, ensuring off-screen to on-screen seamless transition
                logs.add(new Log(pApplet, logSprite, UTILS.pixelToChunks(riverType.getWidth()), speed, xPosition + CONSTANTS.PIXEL_HORIZONTAL));
            }
        }


    }

    /**
     * Checks if the specified hitbox collides with any of the logs in this river segment.
     * Useful for determining if the player has safely landed on a log.
     *
     * @param frogHitbox The hitbox of the player's frog character. Must not be {@code null}.
     * @return {@code true} if a collision is detected with any log, {@code false} otherwise.
     */
    @Override
    public boolean checkCollision(Hitbox frogHitbox) {
        for (Log log : logs) {
            if (UTILS.isColliding(frogHitbox, log.getHitboxAbsolute(riverType.getPositionY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draws the river segment, including all logs (and potentially a crocodile),
     * to the provided {@code PApplet}.
     * Translates the drawing based on the river's Y position to ensure correct placement.
     *
     * @param pApplet The Processing applet used for drawing. Must not be {@code null}.
     */
    @Override
    public void draw(PApplet pApplet) {
        // Translate to river's Y position for drawing
        pApplet.pushMatrix();
        pApplet.translate(0, riverType.getPositionY());
        for (Log log : logs) {
            log.draw(pApplet); // Draw each log
        }
        pApplet.popMatrix();
    }
}
