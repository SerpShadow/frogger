import General.CONSTANTS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreRanking {

    private ArrayList<Integer> scores = new ArrayList<>();

    private Text title;
    private ArrayList<Text> scoreRanking = new ArrayList<>();


    public ScoreRanking(PApplet pApplet) {

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

        scores.sort(Comparator.reverseOrder());

        while (scores.size() > 5) {
            scores.removeLast();
        }

        title = new Text(pApplet, new Point((int) (3.5 * CONSTANTS.CHUNK_SIZE), (int) (5.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.YELLOW, "Score Ranking");


        int offsetX = 3 * CONSTANTS.CHUNK_SIZE;
        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (6.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "1 ST   " + String.format("%05d", scores.get(0)) + " PTS"));
        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (7.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "2 ND   " + String.format("%05d", scores.get(1)) + " PTS"));
        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (8.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "3 RD   " + String.format("%05d", scores.get(2)) + " PTS"));
        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (9.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "4 TH   " + String.format("%05d", scores.get(3)) + " PTS"));
        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (10.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "5 TH   " + String.format("%05d", scores.get(4)) + " PTS"));


    }

    public void draw(PApplet pApplet) {

        title.draw(pApplet);
        scoreRanking.forEach(text -> text.draw(pApplet));

    }
}
