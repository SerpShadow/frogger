package SpriteLib;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The type Single sprite.
 */
public class SingleSprite extends Sprite
{
    private PImage image;

    /**
     * Instantiates a new Single sprite.
     *
     * @param width  the width of the sprite
     * @param height the height of the sprite
     * @param image  the image of the sprite of processing-type PImage
     */
    public SingleSprite( int width, int height, PImage image )
    {
        super( width, height );
        this.image = image;
    }

    @Override
    public void draw( PApplet applet, Point newPos )
    {
        super.draw( applet, newPos );
        if ( isVisible() )
            applet.image( image, newPos.getX(), newPos.getY() );
    }
}
