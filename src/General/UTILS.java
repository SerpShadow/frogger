package General;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class UTILS {
    public static int generateVehicleXPosition(int width, int index, int totalAmount) {
        Random rand = new Random();
        int min = CONSTANTS.PIXEL_HORIZONTAL / totalAmount * index;
        int max = CONSTANTS.PIXEL_HORIZONTAL / totalAmount * (index + 1) - width - CONSTANTS.CHUNK_SIZE_HALF;
        System.out.println("min: " + min + " max: " + max);
        return rand.nextInt(Math.max(max, 1) - min) + min;
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

    public static boolean randomBoolean(double probability) {
        return Math.random() < probability;
    }

    private static int currentLevel = 1;

    public static void increaseLevel() {
        UTILS.currentLevel += 1;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        UTILS.currentLevel = currentLevel;
    }

    public static LevelData getCurrentLevelData() {
        if (currentLevel > 5) return CONSTANTS.LEVEL_LIST[4]; // level 5 is max
        return CONSTANTS.LEVEL_LIST[currentLevel - 1];
    }

    public static ArrayList<Integer> getScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        try {
            File myObj = new File("src/scores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                scores.add(Integer.parseInt(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return scores;
    }

    public static ArrayList<Integer> getScoresSorted(int scoreListLength) {
        ArrayList<Integer> scores = UTILS.getScores();

        scores.sort(Comparator.reverseOrder());
        while (scores.size() > scoreListLength) {
            scores.removeLast();
        }
        return scores;
    }

    public static void setScore(int score) {
        try {

            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/scores.txt", true)));
            writer.println(score);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
