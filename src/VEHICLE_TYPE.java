import General.UTILS;

public enum VEHICLE_TYPE {
    TRUCK(UTILS.chunksToPixel(9), 2),
    RACE_CAR(UTILS.chunksToPixel(10), 1),
    COUPE(UTILS.chunksToPixel(11), 1),
    BULLDOZER(UTILS.chunksToPixel(12), 1),
    DUNE_BUGGY(UTILS.chunksToPixel(13), 1);

    private final int positionY;
    private final int width;

    VEHICLE_TYPE(int positionY, int width) {
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

    public int getWidth() {
        return width;
    }
}
