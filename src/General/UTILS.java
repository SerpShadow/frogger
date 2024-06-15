package General;

import java.util.Random;

public class UTILS {
    public static int generateVehicleXPosition(int width, int index, int totalAmount) {
        Random rand = new Random();
        int min = CONSTANTS.PIXEL_HORIZONTAL / totalAmount * index;
        int max = CONSTANTS.PIXEL_HORIZONTAL / totalAmount * (index + 1) - width;
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

    public static int pixelToChunks(double pixel) {
        return (int) (pixel / CONSTANTS.CHUNK_SIZE);
    }

    public static boolean randomBoolean(double probability){
        return Math.random() < probability;
    }

    private static int currentLevel = 1;

    public static void increaseLevel() {
        UTILS.currentLevel += 1;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static LevelData getCurrentLevelData() {
        if (currentLevel > 5) return CONSTANTS.LEVEL_LIST[4]; // level 5 is max
        return CONSTANTS.LEVEL_LIST[currentLevel - 1];
    }


}
