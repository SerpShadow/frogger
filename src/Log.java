import General.CONSTANTS;
import General.Hitbox;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Log {

    protected Point position = new Point(0, 0);
    protected int speed = 1;

    protected int length;

    private Hitbox hitbox;

    private MultiSprite log = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);

    public Log(PApplet pApplet, PImage spriteMap, int length, int topPosition, int startPosition) {
        position.setX(startPosition);
        this.length = length;

        position.setY(topPosition * CONSTANTS.CHUNK_SIZE);

        log.addFrames(pApplet, spriteMap, 31 * CONSTANTS.CHUNK_SIZE, 19 * CONSTANTS.CHUNK_SIZE, 3);

    }

    public void draw(PApplet pApplet) {
        position.setX(position.getX() + speed);

        for (int i = 0; i < length; i++) {
            Point drawPosition;
            if (i == 0) {
                drawPosition = position;
                log.setFrame(0);
            } else if (i == length - 1) {
                drawPosition = new Point(position.getX() + i * CONSTANTS.CHUNK_SIZE, position.getY());
                log.setFrame(2);
            } else {
                drawPosition = new Point(position.getX() + i * CONSTANTS.CHUNK_SIZE, position.getY());
                log.setFrame(1);
            }
            log.draw(pApplet, drawPosition);
        }

    }

}
