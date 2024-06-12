package SpriteLib;

import processing.core.PApplet;

/**
 * The type Sprite contains all information about a sprite.
 */
public abstract class Sprite
{
    private Size size;                                  // the size is set by the constructor
    private ANCHORTYPE anchor = ANCHORTYPE.CENTER;      // position of the "plotpoint" relative to the surrounding rect
    private boolean visible;                            // is the sprite visible?

    // this instance is created as a member to prevent millions of small mem-allocations in getPlotRect
    private Rectangle plotRectangle = new Rectangle();

    /**
     * Gets plot rect calls the overloaded method
     *
     * @param p the p
     * @return the plot rect
     */
    public Rectangle getPlotRect( Point p )
    {
        return getPlotRect( p.getX(), p.getY() );
    }

    /**
     * Gets plot rect.
     * The "PlotRect" depends on the anchor of the sprite. The Sprite is always plotted in the plotrect which has the same size as (all) images
     *
     * @param origX the orig x
     * @param origY the orig y
     * @return the plot rect
     */
    public Rectangle getPlotRect( int origX, int origY )
    {
        int x = 0;
        int y = 0;
        switch ( getAnchor() )
        {
            case TOP_LEFT:
                x = origX;
                y = origY;
                break;
            case CENTER_LEFT:
                x = origX;
                y = origY - getSize().getHeight() / 2;
                break;
            case BOTTOM_LEFT:
                x = origX;
                y = origY - getSize().getHeight();
                break;
            case TOP_CENTER:
                x = origX - getSize().getWidth() / 2;
                y = origY;
                break;
            case CENTER:
                x = origX - getSize().getWidth() / 2;
                y = origY - getSize().getHeight() / 2;
                break;
            case BOTTOM_CENTER:
                x = origX - getSize().getWidth() / 2;
                y = origY - getSize().getHeight();
                break;
            case TOP_RIGHT:
                x = origX - getSize().getWidth();
                y = origY;
                break;
            case CENTER_RIGHT:
                x = origX - getSize().getWidth();
                y = origY - getSize().getHeight() / 2;
                break;
            case BOTTOM_RIGHT:
                x = origX - getSize().getWidth();
                y = origY - getSize().getHeight();
                break;
        }
        plotRectangle.getTopleft().setX( x );
        plotRectangle.getTopleft().setY( y );
        return plotRectangle;
    }

    /**
     * Instantiates a new Sprite.
     *
     * @param width  the width of the sprite
     * @param height the height of the sprite
     */
    protected Sprite( int width, int height )
    {
        size = new Size( width, height );
        this.visible = true;
        plotRectangle.getSize().setWidth( width );
        plotRectangle.getSize().setHeight( height );
    }

    /**
     * Instantiates a new Sprite.
     *
     * @param width  the width
     * @param height the height
     * @param anchor the anchor
     */
    protected Sprite( int width, int height, ANCHORTYPE anchor )
    {
        this( width, height );
        this.anchor = anchor;
    }

    /**
     * Is visible boolean.
     *
     * @return the boolean
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public Size getSize()
    {
        return size;
    }

    /**
     * Gets anchor.
     *
     * @return the anchor
     */
    public ANCHORTYPE getAnchor()
    {
        return anchor;
    }

    /**
     * Draw.
     *
     * @param applet the applet
     * @param pos    the pos
     */
    public void draw( PApplet applet, Point pos )
    {
        getPlotRect( pos );
    }

    /**
     * Show.
     * shows the sprite
     */
    public void show()
    {
        this.visible = true;
    }

    /**
     * Hide.
     * hides the sprite
     */
    public void hide()
    {
        this.visible = false;
    }
}
