package SpriteLib;

import java.util.HashMap;

/**
 * The type Sequenced sprite.
 * SequencedSprite combines several sprite sequences in one animated instance
 * the current sequence can be changed programatically and will be animated by the baseclass
 * For more information about a sequence look at the according class "Sequence"
 * @see Sequence
 * @see AnimSprite
 */
public class SequencedSprite extends AnimSprite
{
    private HashMap<String, Sequence> sequences = new HashMap<>();
    private Sequence currentSequence = null;
    private int currentFrameNr = 0;

    /**
     * Instantiates a new Sequenced sprite.
     *
     * @param width     the width
     * @param height    the height
     * @param framerate the framerate
     */
    public SequencedSprite( int width, int height, int framerate )
    {
        super( width, height, framerate );
    }

    /**
     * Instantiates a new Sequenced sprite.
     *
     * @param width     the width
     * @param height    the height
     * @param framerate the framerate
     * @param anchor    the anchor
     */
    public SequencedSprite( int width, int height, int framerate, ANCHORTYPE anchor )
    {
        super( width, height, framerate, anchor );
    }

    /**
     * Add sequence.
     *
     * @param sequence the sequence
     */
    public void addSequence( Sequence sequence )
    {
        sequences.put( sequence.getName(), sequence );
        if ( currentSequence == null )
            currentSequence = sequence;
    }

    /**
     * Goto sequence.
     * Starts the named sequence at the first frame.
     * By "resetAnimCount" the method ensures that the first frame of the new sequence occurs immediately
     *
     * @param sequenceName the sequence name
     */
    public void gotoSequence( String sequenceName )
    {
        if ( currentSequence.getName().equals( sequenceName ) )
        {
            resetAnimCounter();
            currentFrameNr = 0;
        }
        currentSequence = sequences.get( sequenceName );
    }

    /**
     * Get Next Frame
     * overridden implementation of getNextFrame of the baseclass
     * this implementation consider the method getNextSequenceName of the current sequence
     * @see AnimSprite#getNextFrame()
     * @see Sequence#getNextSequenceName()
     */
    @Override
    public int getNextFrame()
    {
        if ( currentFrameNr < currentSequence.getLength() - 1 )  // reached the end of the current sequence?
            currentFrameNr++;
        else
        {
            gotoSequence( currentSequence.getNextSequenceName() );   // if yes -> getTheNextSequence (maybe the same again)
            currentFrameNr = 0;
        }
        return currentSequence.getFrame( currentFrameNr );
    }
}
