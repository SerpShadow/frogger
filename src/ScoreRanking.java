import General.CONSTANTS;
import General.UTILS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreRanking {

    private final Text title;
    private final ArrayList<Text> scoreRanking = new ArrayList<>();


    public ScoreRanking(PApplet pApplet) {

        ArrayList<Integer> allScores = UTILS.getScores();
        int lastScore = -1;
        if (!allScores.isEmpty()) {
            lastScore = allScores.getLast();
        }

        title = new Text(pApplet, new Point((int) (3.5 * CONSTANTS.CHUNK_SIZE), (int) (5.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.YELLOW, "Score Ranking");

        String[] list = new String[]{
                "1 ST",
                "2 ND",
                "3 RD",
                "4 TH",
                "5 TH",
        };
        ArrayList<Integer> scores = UTILS.getScoresSorted(5);
        for (int i = 0; i < 5; i++) {
            int score;
            if (scores.size() <= i) score = 0;
            else score = scores.get(i);
            TEXT_COLOR textColor = score == lastScore ? TEXT_COLOR.PURPLE : TEXT_COLOR.WHITE;
            scoreRanking.add(new Text(pApplet, new Point(UTILS.chunksToPixel(3), (int) ((6.5 + i) * CONSTANTS.CHUNK_SIZE)), textColor, list[i] + "   " + String.format("%05d", score) + " PTS"));
        }


//        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (6.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "1 ST   " + String.format("%05d", scores.get(0)) + " PTS"));
//        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (7.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "2 ND   " + String.format("%05d", scores.get(1)) + " PTS"));
//        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (8.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "3 RD   " + String.format("%05d", scores.get(2)) + " PTS"));
//        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (9.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "4 TH   " + String.format("%05d", scores.get(3)) + " PTS"));
//        scoreRanking.add(new Text(pApplet, new Point(offsetX, (int) (10.5 * CONSTANTS.CHUNK_SIZE)), TEXT_COLOR.WHITE, "5 TH   " + String.format("%05d", scores.get(4)) + " PTS"));


    }

    public void draw(PApplet pApplet) {

        title.draw(pApplet);
        scoreRanking.forEach(text -> text.draw(pApplet));

    }
}
