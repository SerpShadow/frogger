package SpriteLib;

public class Point
{
    private int x;
    private int y;

    public Point()
    {
        x = y = 0;
    }

    public Point( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return x in PX
     */
    public int getX()
    {
        return x;
    }

    public void setX( int x )
    {
        this.x = x;
    }

    /**
     *
     * @return y in PX
     */
    public int getY()
    {
        return y;
    }

    public void setY( int y )
    {
        this.y = y;
    }
}
