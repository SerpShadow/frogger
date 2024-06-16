import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Log extends Floating {

    private Hitbox hitbox = new Hitbox(0, -8, 0, 8);


    public Log(PApplet pApplet, PImage logSprite,  int width, double speed, int startPosition) {
//    public Log(PApplet pApplet, PImage logSprite,  int width, double speed, int startPosition, double probabilityCrocodile) {
        super(pApplet, width, speed, startPosition);
        setHitboxRelative(hitbox);

        getMultiSprite().addFrames(pApplet, logSprite, 0, 0, 3);
    }



    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        for (int i = 0; i < getWidthInChunks(); i++) {
            int positionX = getPositionX() + UTILS.chunksToPixel(i);
            Point drawPosition = new Point(positionX, getPositionY());
            if (i == 0) {
                getMultiSprite().setFrame(0);
            } else if (i == getWidthInChunks() - 1) {
                getMultiSprite().setFrame(2);
            } else {
                getMultiSprite().setFrame(1);
            }
            getMultiSprite().draw(pApplet, drawPosition);
        }
    }
}
