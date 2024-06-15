import General.CONSTANTS;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Game {

    private GAME_STAGE gameStage = GAME_STAGE.MAIN;

    private SplashScreen splashScreen;
    private GameIntro gameIntro;
    private ScoreRanking scoreRanking;
    private GameController gameController;

    private Text titleScore;
    private Text titleHiScore;
    private Text displayScore;
    private Text displayHiScore;

    private int score = 0;


    public Game(PApplet pApplet) {
        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");

        // three stages of game
        splashScreen = new SplashScreen(pApplet);
        gameIntro = new GameIntro(pApplet);
        gameController = new GameController(pApplet, this);
        scoreRanking = new ScoreRanking(pApplet);


        titleScore = new Text(pApplet, new Point(2 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "1-UP"); // adding +1 for better optic
        titleHiScore = new Text(pApplet, new Point(5 * CONSTANTS.CHUNK_SIZE, 0 + 1), TEXT_COLOR.WHITE, "Hi-Score");

        String stringScore = String.valueOf(score);
        displayScore = new Text(pApplet, new Point(1 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));
        displayHiScore = new Text(pApplet, new Point(7 * CONSTANTS.CHUNK_SIZE + 8, 8 + 1), TEXT_COLOR.RED, ("00000" + stringScore).substring(stringScore.length()));


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



    private void drawHeader(PApplet pApplet) {
        titleScore.draw(pApplet);
        titleHiScore.draw(pApplet);
        displayScore.draw(pApplet);
    }

    public void drawLives() {

    }

    public void draw(PApplet pApplet) {

        switch (gameStage) {
            case SPLASH_SCREEN:
                splashScreen.draw(pApplet);
                if (splashScreen.isFinished()) {
                    gameStage = GAME_STAGE.GAME_INTRO;
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
