import General.CONSTANTS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import processing.core.PApplet;
import processing.core.PImage;

public class Vehicle {
    protected int width = CONSTANTS.CHUNK_SIZE;
    protected int height = CONSTANTS.CHUNK_SIZE;

    protected Point position = new Point(0, 0);
    protected int speed;

    private MultiSprite multiSprite;
    private Hitbox hitbox;

    public Vehicle(PApplet pApplet, PImage spriteMap, VEHICLE_TYPE vehicleType, int startPosition) {
        position.setX(startPosition);

        switch (vehicleType) {
            case TRUCK:
                position.setY(8 * CONSTANTS.CHUNK_SIZE);
                width *= 2;
                speed = -1;
                multiSprite = new MultiSprite(width, height, ANCHORTYPE.TOP_LEFT);
                multiSprite.addFrames(pApplet, spriteMap, 3 * CONSTANTS.CHUNK_SIZE, 0, 1);
//                hitbox = new Hitbox(position.getY() + 3, position.getX() + width - 2, position.getY() + height - 3, position.getX() + 3);
                break;
            case RACE_CAR:
                position.setY(9 * CONSTANTS.CHUNK_SIZE);
                speed = 3;
                multiSprite = new MultiSprite(width, height, ANCHORTYPE.TOP_LEFT);
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, 0, 1);
//                hitbox = new Hitbox(position.getY() + 1, position.getX() + width, position.getY() + height - 1, position.getX());
                break;
            case COUPE:
                position.setY(10 * CONSTANTS.CHUNK_SIZE);
                speed = -1;
                multiSprite = new MultiSprite(width, height, ANCHORTYPE.TOP_LEFT);
                multiSprite.addFrames(pApplet, spriteMap, 5 * CONSTANTS.CHUNK_SIZE + 8, 0, 1);
//                hitbox = new Hitbox(position.getY() + 3, position.getX() + width - 1, position.getY() + height - 3, position.getX());
                break;
            case BULLDOZER:
                position.setY(11 * CONSTANTS.CHUNK_SIZE);
                speed = 1;
                multiSprite = new MultiSprite(width, height, ANCHORTYPE.TOP_LEFT);
                multiSprite.addFrames(pApplet, spriteMap, CONSTANTS.CHUNK_SIZE + 8, 0, 1);
//                hitbox = new Hitbox(position.getY() + 2, position.getX() + width - 1, position.getY() + height - 2, position.getX() + 1);
                break;
            case DUNE_BUGGY:
                position.setY(12 * CONSTANTS.CHUNK_SIZE);
                speed = -2;
                multiSprite = new MultiSprite(width, height, ANCHORTYPE.TOP_LEFT);
                multiSprite.addFrames(pApplet, spriteMap, 7 * CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, 1);
//                hitbox = new Hitbox(position.getY() + 1, position.getX() + width, position.getY() + height - 1, position.getX());
                break;
            default:
                break;
        }


    }

    void resetPosition() {
        if (speed > 0) {
            position.setX(-width);
        } else {
            position.setX(14 * CONSTANTS.CHUNK_SIZE);
        }
    }

    public Hitbox getHitbox() {
        return new Hitbox(position.getY(), position.getX() + width, position.getY() + height, position.getX());
    }

    public void draw(PApplet pApplet) {
        position.setX(position.getX() + speed);
        multiSprite.draw(pApplet, position);

        if (speed > 0) {
            if (position.getX() > 14 * CONSTANTS.CHUNK_SIZE) resetPosition();

        } else {
            if (position.getX() < -width) resetPosition();
        }

    }

}
