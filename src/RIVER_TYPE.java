import General.UTILS;

public enum RIVER_TYPE {
    FIRST(UTILS.chunksToPixel(3), UTILS.chunksToPixel(4)),
    SECOND(UTILS.chunksToPixel(4), UTILS.chunksToPixel(2)),
    THIRD(UTILS.chunksToPixel(5), UTILS.chunksToPixel(6)),
    FOURTH(UTILS.chunksToPixel(6), UTILS.chunksToPixel(3)),
    FIFTH(UTILS.chunksToPixel(7), UTILS.chunksToPixel(3));

    private int positionY;
    private int width;

    RIVER_TYPE(int positionY, int width) {
        this.positionY = positionY;
        this.width = width;
    }

    /**
     *
     * @return positionY in px
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     *
     * @return length in px
     */
    public int getWidth() {
        return width;
    }
}
