import General.CONSTANTS;
import General.UTILS;
import SpriteLib.Point;
import Text.TEXT_COLOR;
import Text.Text;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class Game {

    private final PApplet pApplet;

    private GAME_STAGE gameStage;

    private SplashScreen splashScreen;
    private GameIntro gameIntro;
    private ScoreRanking scoreRanking;
    private GameController gameController;

    private final Text titleScore;
    private final Text titleHiScore;
    private final Text displayScore;
    private final Text displayScoreHi;

    private int score = 0;
    private int scoreHi = 0;


    public Game(PApplet pApplet) {
        this.pApplet = pApplet;
        setGameStage(GAME_STAGE.SCORE_RANKING);

        ArrayList<Integer> allScores = UTILS.getScores();
        if (!allScores.isEmpty()) {
            score = allScores.getLast();
        }

        ArrayList<Integer> xxx = UTILS.getScoresSorted(1);
        if (!xxx.isEmpty()) {
            scoreHi = xxx.getFirst();
        }

        titleScore = new Text(pApplet, new Point(UTILS.chunksToPixel(2), 0 + 1), TEXT_COLOR.WHITE, "1-UP"); // adding +1 for better optic
        titleHiScore = new Text(pApplet, new Point(UTILS.chunksToPixel(5), 0 + 1), TEXT_COLOR.WHITE, "Hi-Score");

        displayScore = new Text(pApplet, new Point(UTILS.chunksToPixel(1.5), 8 + 1), TEXT_COLOR.RED, String.format("%05d", score));
        displayScoreHi = new Text(pApplet, new Point(UTILS.chunksToPixel(5.5), 8 + 1), TEXT_COLOR.RED, String.format("%05d", scoreHi));
    }

    public void setGameStage(GAME_STAGE gameStage) {
        switch (gameStage) {
            case SPLASH_SCREEN -> splashScreen = new SplashScreen(pApplet);
            case GAME_INTRO -> gameIntro = new GameIntro(pApplet);
            case MAIN -> gameController = new GameController(pApplet, this);
            case SCORE_RANKING -> scoreRanking = new ScoreRanking(pApplet);
        }
        this.gameStage = gameStage;
    }

    public void keyPressed(int keyCode) {
        if (gameStage == GAME_STAGE.GAME_INTRO) {
            if (gameIntro.getGameIntroStage() == GAME_INTRO_STAGE.SCORE_RANKING) {
                if (keyCode == PConstants.BEVEL) {
                    setGameStage(GAME_STAGE.MAIN);
                }
            }
        } else if (gameStage == GAME_STAGE.SCORE_RANKING) {
            if (keyCode == PConstants.BEVEL) {
                setScore(0);
                setGameStage(GAME_STAGE.MAIN);
            }
        } else {
            gameController.keyPressed(keyCode);
        }
    }

    public void setScore(int score) {
        this.score = score;
        displayScore.setString(String.format("%05d", score));
    }

    public void increaseScoreBy(int points) {
        this.score += points;
        if (score > scoreHi) {
            scoreHi = score;
        }
        System.out.println(score);
        displayScore.setString(String.format("%05d", score));
        displayScoreHi.setString(String.format("%05d", scoreHi));
    }

    public void gameOver() {
        UTILS.setScore(score);
        setGameStage(GAME_STAGE.SCORE_RANKING);
    }

    private void drawHeader(PApplet pApplet) {
        titleScore.draw(pApplet);
        titleHiScore.draw(pApplet);
        displayScore.draw(pApplet);
        displayScoreHi.draw(pApplet);
    }

    public void draw(PApplet pApplet) {

        pApplet.pushMatrix();
        pApplet.translate(0, 0);
        pApplet.fill(0, 0, 71);
        pApplet.rect(0, 0, CONSTANTS.PIXEL_HORIZONTAL, (int) (CONSTANTS.PIXEL_VERTICAL / 2));
        pApplet.popMatrix();

        switch (gameStage) {
            case SPLASH_SCREEN:
                splashScreen.draw(pApplet);
                if (splashScreen.isFinished()) {
                    setGameStage(GAME_STAGE.GAME_INTRO);
                }
                break;
            case GAME_INTRO:
                drawHeader(pApplet);
                gameIntro.draw(pApplet);
                break;
            case MAIN:
                drawHeader(pApplet);
                gameController.draw(pApplet);
                break;
            case SCORE_RANKING:
                drawHeader(pApplet);
                scoreRanking.draw(pApplet);
                break;
        }
    }
}
