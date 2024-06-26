package SpriteLib;

public class Size
{
    int width;
    int height;

    public Size()
    {
        width = height = 0;
    }

    public Size( int width, int height )
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth( int width )
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight( int height )
    {
        this.height = height;
    }
}
