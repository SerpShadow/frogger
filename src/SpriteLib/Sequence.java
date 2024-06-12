package SpriteLib;

import java.util.ArrayList;

public class Sequence
{
    private ArrayList<Integer> frames = new ArrayList<>();
    private String name;
    private String nextSequenceName = null;

    public Sequence( String name )
    {
        this.name = name;
    }

    public Sequence( String name, String nextSequenceName )
    {
        this( name );
        this.nextSequenceName = nextSequenceName;
    }

    public Sequence( String name, int... elems )
    {
        this( name );
        for ( int i = 0; i < elems.length; i++ )
            frames.add( i, elems[i] );
    }

    public Sequence( String name, String nextSequenceName, int... elems )
    {
        this( name, nextSequenceName );
        for ( int i = 0; i < elems.length; i++ )
            frames.add( i, elems[i] );
    }

    public String getName()
    {
        return name;
    }

    public String getNextSequenceName()
    {
        return nextSequenceName;
    }

    public void setNextSequenceName( String nextSequenceName )
    {
        this.nextSequenceName = nextSequenceName;
    }

    public void addFrame( int frame )
    {
        frames.add( frame );
    }

    public void addRange( int from, int to )
    {
        for ( int i = from; i <= to; i++ )
            addFrame( i );
    }

    public int getLength()
    {
        return frames.size();
    }

    public int getFrame( int i )
    {
        return frames.get( i );
    }
}
