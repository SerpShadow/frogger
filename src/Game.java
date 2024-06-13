import General.CONSTANTS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Game {

    GAME_STAGE gameStage = GAME_STAGE.MAIN;

    GameIntro gameIntro;
    ScoreRanking scoreRanking;
    GameController gameController;

    Text titleScore;
    Text titleHiScore;
    Text displayScore;
    Text displayHiScore;

    int score = 0;
    int level = 1;
    int lives = 7;

    long endTime = System.currentTimeMillis() + 60 * 1000;


    public Game(PApplet pApplet) {
        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        // three stages of game
        gameIntro = new GameIntro(pApplet);
        gameController = new GameController(pApplet, this);
        scoreRanking = new ScoreRanking(pApplet);


        titleScore = new Text(pApplet, new Point(2 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "1-UP"); // adding +1 for better optic
        titleHiScore = new Text(pApplet, new Point(5 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "Hi-Score");

        String stringScore = String.valueOf(score);
        displayScore = new Text(pApplet, new Point(1 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));
        displayHiScore = new Text(pApplet, new Point(7 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));

//        Writer wr = new FileWriter("123.txt");
    }

    public void keyPressed(int keyCode) {
//        System.out.println("keyPressed: " + keyCode);
//        System.out.println(keyCode);
        if (gameStage == GAME_STAGE.GAME_INTRO) {
            if (gameIntro.getGameIntroStage() == GAME_INTRO_STAGE.SCORE_RANKING) {
                if (keyCode == PConstants.BEVEL) {
                    gameStage = GAME_STAGE.MAIN;
                }
            }
        } else if (gameStage == GAME_STAGE.SCORE_RANKING) {
            // todo: handle space-bar

        } else {
            gameController.keyPressed(keyCode, this);
        }
    }

    public void increaseScoreBy(int points) {
        this.score += points;
        String stringScore = String.valueOf(score);
        displayScore.setString(("00000" + stringScore).substring(stringScore.length()));
    }

    public void restartTimer() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        endTime = System.currentTimeMillis() + 60 * 1000;
                    }
                },
                CONSTANTS.RESPAWN_DELAY * 1000
        );
    }

    public void onDeath() {
        lives -= 1;
        restartTimer();
    }

    public void drawLives() {

    }

    public void draw(PApplet pApplet) {
        long remainingTime = endTime - System.currentTimeMillis();
        long remainingSeconds = remainingTime / 1000;
//        System.out.println("TIME:" + remainingSeconds);
        titleScore.draw(pApplet);
        titleHiScore.draw(pApplet);
        displayScore.draw(pApplet);

        switch (gameStage) {
            case GAME_INTRO:
                gameIntro.draw(pApplet);
                break;
            case MAIN:
                gameController.draw(pApplet);
                break;
            case SCORE_RANKING:
                scoreRanking.draw(pApplet);
                break;
        }
    }
}
