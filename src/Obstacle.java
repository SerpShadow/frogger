import General.CONSTANTS;
import SpriteLib.Point;
import processing.core.PApplet;

public class Obstacle {

    private final int width;
    private final int height = UTILS.chunksToPixel(1);

    private final Point position = new Point();
    private final int speed;

    private final Hitbox hitboxRelativ;


    public Obstacle(int width, int speed, Hitbox hitboxRelativ) {
        this.width = width;
        this.speed = speed;
        this.hitboxRelativ = hitboxRelativ;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPosition() {
        return position;
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public void setPositionX(int positionX) {
        position.setX(positionX);
    }

    public int getSpeed() {
        return speed;
    }

    public Hitbox getHitboxAbsolute(int chunksY) {
        return new Hitbox(position.getY() + hitboxRelativ.getTop() + UTILS.chunksToPixel(chunksY), position.getX() + width - hitboxRelativ.getRight(), position.getY() + height - hitboxRelativ.getBottom() + UTILS.chunksToPixel(chunksY), position.getX() + hitboxRelativ.getLeft());
    }

    private void checkPosition() {
        if (speed > 0) {
            if (position.getX() > 14 * CONSTANTS.CHUNK_SIZE) {
                position.setX(-width); // move object to the left but -width to render outside the screen
            }
        } else {
            if (position.getX() < -width) {
                position.setX(CONSTANTS.CHUNKS_HORIZONTALLY * CONSTANTS.CHUNK_SIZE); // move object to the right
            }
        }
    }

    public void draw(PApplet pApplet) {
        position.setX(position.getX() + speed);
        checkPosition();
    }
}
