import General.CONSTANTS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Game {

    boolean isIntro = true;
    GameIntro gameIntro;

    boolean isGameOver = false;
    ScoreRanking scoreRanking;

    GameController gameController;


    Text titleScore;
    Text titleHiScore;
    Text displayScore;
    Text displayHiScore;

    MultiSprite multiSprite = new MultiSprite(CONSTANTS.CHUNK_SIZE / 2, CONSTANTS.CHUNK_SIZE / 2, ANCHORTYPE.TOP_LEFT);

    int score = 0;
    int level = 0;
    int lives = 7;

    long endTime = System.currentTimeMillis() + 60 * 1000;


    public Game(PApplet pApplet) {
        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        // three stages of game
        gameIntro = new GameIntro(pApplet);
        scoreRanking = new ScoreRanking(pApplet);
        gameController = new GameController(pApplet, this);


        titleScore = new Text(pApplet, new Point(2 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "1-UP"); // adding +1 for better optic
        titleHiScore = new Text(pApplet, new Point(5 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "Hi-Score");

        String stringScore = String.valueOf(score);
        displayScore = new Text(pApplet, new Point(1 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));
        displayHiScore = new Text(pApplet, new Point(7 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));

//        Writer wr = new FileWriter("123.txt");
    }

    public void keyPressed(int key) {
        if (isIntro || isGameOver) {
            // todo: handle space-bar
        } else {
            gameController.keyPressed(key, this);
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

        if (isIntro) {
            // todo: render GameIntro
            gameIntro.draw(pApplet);
        } else if (isGameOver) {
            scoreRanking.draw(pApplet);
            // todo: render ScoreRanking
        } else {
            gameController.draw(pApplet);
            // todo: render GameController
        }

    }
}
