import SpriteLib.Point;
import processing.core.PApplet;

public class GameIntro {

    private Frog frog;

    private int counter = 0;

    public GameIntro(PApplet pApplet) {
        frog = new Frog(pApplet, new Point(200, 200));
    }

    public void draw(PApplet pApplet) {

        if (counter % 10 == 0) {
            frog.goLeft();
        }


        frog.draw(pApplet);

        counter += 1;
    }
}
