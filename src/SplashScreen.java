import General.CONSTANTS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen {

    private List<List<Text>> zerosMatrix = new ArrayList<>();
    private int counter = 0;
    private boolean finished = false;

    public SplashScreen(PApplet pApplet) {
        for (int i = 0; i < CONSTANTS.CHUNKS_VERTICALLY * 2; i++) {
            List zerosArray = new ArrayList();
            for (int ii = 0; ii < CONSTANTS.CHUNKS_HORIZONTALLY * 2; ii++) {
                Text xxx = new Text(pApplet, new Point(ii * 8, i * 8), TEXT_COLOR.WHITE, "0");
                zerosArray.add(xxx);
            }
            zerosMatrix.add(zerosArray);
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void draw(PApplet pApplet) {

        if (counter == 20) {
            zerosMatrix.subList(2, CONSTANTS.CHUNKS_VERTICALLY * 2 - 2).clear();
        } else if (counter > 25 && (counter % 2 == 0)) {
            for (List<Text> zerosArray : zerosMatrix) {
                if (!zerosArray.isEmpty()) {
                    zerosArray.remove(zerosArray.size() - 1);
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
