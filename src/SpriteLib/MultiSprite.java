package SpriteLib;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;


/**
 * The type MultiSprite.
 * The MultiSprite is a container for multiple images that are meant to be related in some way
 * The MutliSprite always has a current sprite, which is drawn in the draw method
 * The current sprite can be changed by the using application
 * all images must have the same size
 */
public class MultiSprite extends Sprite
{
    private int currentFrame = 0;
    private ArrayList<PImage> frames = new ArrayList<>();
    private SpriteEvent onSpriteEvent = null;

    /**
     * Instantiates a new Multi sprite.
     *
     * @param width  the width
     * @param height the height
     */
    public MultiSprite( int width, int height )
    {
        super( width, height );
    }

    /**
     * Instantiates a new Multi sprite.
     *
     * @param width  the width
     * @param height the height
     * @param anchor the anchor
     */
    public MultiSprite( int width, int height, ANCHORTYPE anchor )
    {
        super( width, height, anchor );
    }

    /**
     * Gets current frame.
     *
     * @return the current frame
     */
    public int getCurrentFrame()
    {
        return currentFrame;
    }

    /**
     * Gets frames.
     *
     * @return the frames
     */
    public List<PImage> getFrames()
    {
        return frames;
    }

    /**
     * Sets onSpriteEvent.
     * The set spriteEvent is called whenever something remarkable happens, e.g. that the frames changes
     * inherited classes can introduce their own events
     *
     * @param onSpriteEvent the onSpriteEvent
     */
    public void setOnSpriteEvent( SpriteEvent onSpriteEvent )
    {
        this.onSpriteEvent = onSpriteEvent;
    }

    /**
     *
     *
     * @param applet the applet where the images are drawn
     * @param newPos the position where the images should be plotted. It's shifted by the draw-anchor (TOPLEFT...)
     */
    @Override
    public void draw( PApplet applet, Point newPos )
    {
        super.draw( applet,newPos );
        Point plotPoint = getPlotRect( newPos ).getTopleft();   // get the shifted rect for the phys. pos.
        if ( frames.size() > currentFrame )
            applet.image( frames.get( currentFrame ), plotPoint.getX(), plotPoint.getY() );
    }

    /**
     * Gets next frame.
     * This method can be overridden by inherited classes if this classes has no linear stepping through images
     *
     * @return the next frame
     */
    public int getNextFrame()
    {
        if ( getFrameCount() > getCurrentFrame() + 1 )
            return getCurrentFrame() + 1;
        return 0;
    }

    /**
     * Next frame.
     */
    public void nextFrame()
    {
        if ( onSpriteEvent != null )
            onSpriteEvent.event( this, currentFrame, EVENTTYPE.LEAVEFRAME );
        currentFrame = getNextFrame();    // call of optional overridden method
        if ( onSpriteEvent != null )
            onSpriteEvent.event( this, currentFrame, EVENTTYPE.ENTERFRAME );
    }

    /**
     * Gets frame count.
     *
     * @return the frame count
     */
    public int getFrameCount()
    {
        return frames.size();
    }

    /**
     * Sets the current frame.
     *
     * @param newFrame the new frame
     * @return true if the newFrame is in range of the framelist, else false
     */
    public boolean setFrame( int newFrame )
    {
        if ( onSpriteEvent != null )
            onSpriteEvent.event( this, currentFrame, EVENTTYPE.LEAVEFRAME );
        if ( newFrame >= 0 && newFrame < frames.size() )
        {
            currentFrame = newFrame;
            if ( onSpriteEvent != null )
                onSpriteEvent.event( this, currentFrame, EVENTTYPE.ENTERFRAME );
            return true;
        }
        return false;
    }

    /**
     * Add frames to the list of frames
     * The images are taken from an imagemap, starting at the position gridOffsetX, gridOffsetY
     * count defines the number of images that are taken in a row
     * all images must have the same size which is defined by the class-members
     *
     * @param applet      the applet
     * @param source      the source
     * @param gridOffsetX the grid offset x
     * @param gridOffsetY the grid offset y
     * @param count       the count
     */
    public void addFrames( PApplet applet, PImage source, int gridOffsetX, int gridOffsetY, int count )
    {
        for ( int i = 0; i < count; i++ )
        {
            PImage img = applet.createImage( getSize().getWidth(), getSize().getHeight(), PConstants.ARGB );  // create an empty image as target for the copy
            // slice a rect part of the imagemap and copy it the created empty images
            img.copy( source,
                    gridOffsetX, gridOffsetY,
                    getSize().getWidth(), getSize().getHeight(),
                    0, 0,
                    getSize().getWidth(), getSize().getHeight() );
            frames.add( img );   // add the image to the list of frames
            gridOffsetX += getSize().getWidth();   // increase the x-pos for the next frame
        }
    }

    /**
     * Add frame copy.
     *
     * @param source the source
     */
    public void addFrameCopy( int source )
    {
        frames.add( frames.get( source ) );
    }

    /**
     * Clear frames.
     */
    public void clearFrames()
    {
        frames = new ArrayList<>();
    }
}
