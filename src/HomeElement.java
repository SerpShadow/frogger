import General.CONSTANTS;
import General.Hitbox;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import processing.core.PApplet;
import processing.core.PImage;

public class HomeElement extends Obstacle {

    private boolean occupied = false;
    private final MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE, CONSTANTS.CHUNK_SIZE, ANCHORTYPE.TOP_LEFT);

    public HomeElement(PApplet pApplet, int startPositionX) {
        super(1, 0, startPositionX, new Hitbox(0, -4, 0, 4));

        PImage homeElementsSprite = pApplet.loadImage("assets/home-elements.png");
        for (int i = 0; i < 5; i++) {
            multiSprite.addFrames(pApplet, homeElementsSprite, i * UTILS.chunksToPixel(1.5), 0, 1);
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    protected void checkPosition() {
        // not used, as this element does not move
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        pApplet.pushMatrix();
        pApplet.translate(0, UTILS.chunksToPixel(0.5));
        if (occupied) {
            multiSprite.setFrame(1);
            multiSprite.draw(pApplet, getPosition());
        } else {

        }
        pApplet.popMatrix();

    }


}
