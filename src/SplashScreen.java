import General.CONSTANTS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a splash screen featuring an animated matrix of zeros before the main game starts.
 * The animation consists of rapidly displaying zeros and then removing them to transition to the next screen.
 */
public class SplashScreen {
    /**
     * A matrix (2D List) that stores {@link Text} objects representing zeros to be displayed on the screen.
     */
    private final List<List<Text>> zerosMatrix = new ArrayList<>();

    /**
     * A counter to manage the animation state and timing.
     */
    private int counter = 0;

    /**
     * Indicates whether the splash screen animation has finished.
     */
    private boolean finished = false;

    /**
     * Initializes a new instance of the {@link SplashScreen} class, populating the zerosMatrix with {@link Text} objects.
     *
     * @param pApplet The {@link PApplet} instance used to draw the zeros on the screen.
     */
    public SplashScreen(PApplet pApplet) {
        for (int i = 0; i < CONSTANTS.CHUNKS_VERTICAL * 2; i++) {
            List<Text> zerosArray = new ArrayList<>();
            for (int ii = 0; ii < CONSTANTS.CHUNKS_HORIZONTAL * 2; ii++) {
                Text xxx = new Text(pApplet, new Point(ii * 8, i * 8), TEXT_COLOR.WHITE, "0");
                zerosArray.add(xxx);
            }
            zerosMatrix.add(zerosArray);
        }
    }

    /**
     * Checks if the splash screen animation is complete.
     *
     * @return {@code true} if the animation has finished, {@code false} otherwise.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Handles the animation logic and drawing of the splash screen. It updates the animation state
     * based on the counter and removes elements from the zerosMatrix accordingly to create a "disappearing" effect.
     *
     * @param pApplet The {@link PApplet} instance used for drawing.
     */
    public void draw(PApplet pApplet) {

        if (counter == 20) {
            zerosMatrix.subList(2, CONSTANTS.CHUNKS_VERTICAL * 2 - 2).clear();
        } else if (counter > 25) {
            for (List<Text> zerosArray : zerosMatrix) {
                if (!zerosArray.isEmpty()) {
                    zerosArray.removeLast();
                } else {
                    finished = true;
                }
            }
        }

        for (List<Text> zerosArray : zerosMatrix) {
            for (Text text : zerosArray) {
                text.draw(pApplet);
            }
        }

        counter += 1;
    }
}
