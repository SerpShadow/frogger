package SpriteLib;

public class Rectangle
{
    private Point topleft;
    private Size size;

    public Rectangle()
    {
        this.topleft = new Point();
        this.size = new Size();
    }

    public Rectangle( Point topleft, Size size )
    {
        this.topleft = topleft;
        this.size = size;
    }

    public int top()
    {
        return topleft.getY();
    }

    public int bottom()
    {
        return topleft.getY() + size.getHeight();
    }

    public int left()
    {
        return topleft.getX();
    }

    public int right()
    {
        return topleft.getX() + size.getWidth();
    }

    public Point getTopleft()
    {
        return topleft;
    }

    public Size getSize()
    {
        return size;
    }
}
