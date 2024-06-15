import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Turtle extends Floating {
    private Hitbox hitbox = new Hitbox(0, -8, 0, 8);

    private final boolean isSubmerging = false;


    public Turtle(PApplet pApplet, int width, double speed, int startPosition, boolean isSubmerging) {
        super(pApplet, width, speed, startPosition);
        setHitboxRelativ(hitbox);

        PImage turtleSprite = pApplet.loadImage("assets/turtle.png");
        getMultiSprite().addFrames(pApplet, turtleSprite, 0, 0, 5);
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        int counter = (pApplet.millis() / 500) % 3;

        for (int i = 0; i < getWidthInChunks(); i++) {
            int positionX = getPositionX() + UTILS.chunksToPixel(i);
            Point drawPosition = new Point(positionX, getPositionY());
            getMultiSprite().setFrame(counter);
            getMultiSprite().draw(pApplet, drawPosition);

        }
    }

}
