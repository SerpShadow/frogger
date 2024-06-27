import General.Hitbox;
import SpriteLib.SequencedSprite;
import processing.core.PApplet;
import processing.core.PImage;

public class Crocodile extends Floating {


    private SequencedSprite sequencedSprite;

    public Crocodile(PApplet pApplet, int length, int speed, int startPosition) {
        super(pApplet, length, speed, startPosition, new Hitbox(0, -24, 0, 8));

        PImage crocodileSprite = pApplet.loadImage("assets/crocodile.png");
        getMultiSprite().addFrames(pApplet, crocodileSprite, 0, 0, 3);


    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
    }
}
