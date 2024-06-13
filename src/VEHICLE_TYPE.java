import General.UTILS;

public enum VEHICLE_TYPE {
    TRUCK(UTILS.chunksToPixel(2)),
    RACE_CAR(UTILS.chunksToPixel(1)),
    COUPE(UTILS.chunksToPixel(1)),
    BULLDOZER(UTILS.chunksToPixel(1)),
    DUNE_BUGGY(UTILS.chunksToPixel(1));

    private int width;

    VEHICLE_TYPE(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
    }
