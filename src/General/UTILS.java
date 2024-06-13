package General;

import java.util.Random;

public class UTILS {
    public static int generateVehicleXPosition(int width, int index, int totalAmount) {
        Random rand = new Random();
        int min = CONSTANTS.CHUNKS_HORIZONTALLY * CONSTANTS.CHUNK_SIZE / totalAmount * index;
        int max = CONSTANTS.CHUNKS_HORIZONTALLY * CONSTANTS.CHUNK_SIZE / totalAmount * (index + 1) - width;
        return rand.nextInt(max - min) + min;
    }

    public static boolean isColliding(Hitbox hitbox1, Hitbox hitbox2) {
        return hitbox1.getRight() > hitbox2.getLeft()
                && hitbox1.getLeft() < hitbox2.getRight()
                && hitbox1.getTop() < hitbox2.getBottom()
                && hitbox1.getBottom() > hitbox2.getTop();

    }

    public static int chunksToPixel(double chunks) {
        return (int) (chunks * CONSTANTS.CHUNK_SIZE);
    }

    private int currentLevel = 1;

    public void increaseLevel() {
        this.currentLevel += 1;
    }

    public LevelData getCurrentLevelData() {
        return CONSTANTS.LEVEL_LIST[currentLevel - 1];
    }


}
