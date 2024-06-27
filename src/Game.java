import General.CONSTANTS;
import General.UTILS;
import SpriteLib.Point;
import Text.TEXT_COLOR;
import Text.Text;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Manages the main game environment, including the game stages, scores, rendering of UI elements,
 * and responding to user inputs.
 */
public class Game {

    private final PApplet pApplet; // The Processing applet used for drawing the game.
    private GAME_STAGE gameStage; // The current stage of the game.

    // Game stage components
    private SplashScreen splashScreen;
    private GameIntro gameIntro;
    private ScoreRanking scoreRanking;
    private GameController gameController;

    // UI text elements
    private final Text titleScore;
    private final Text titleHiScore;
    private final Text displayScore;
    private final Text displayScoreHi;

    // Player score variables
    private int score = 0;
    private int scoreHi = 0;

    /**
     * Initializes a new game environment, including setting up UI elements and loading scores.
     *
     * @param pApplet The Processing applet context for rendering.
     */
    public Game(PApplet pApplet) {
        this.pApplet = pApplet;
        setGameStage(GAME_STAGE.MAIN);

        // Load scores from storage
        ArrayList<Integer> allScores = UTILS.getScores();
        if (!allScores.isEmpty()) {
            score = allScores.getLast();
        }

        ArrayList<Integer> scoresSorted = UTILS.getScoresSorted(1);
        if (!scoresSorted.isEmpty()) {
            scoreHi = scoresSorted.getFirst();
        }

        // Initialize UI text elements
        titleScore = new Text(pApplet, new Point(UTILS.chunksToPixel(2), 0 + 1), TEXT_COLOR.WHITE, "1-UP"); // adding +1 for better optic
        titleHiScore = new Text(pApplet, new Point(UTILS.chunksToPixel(5), 0 + 1), TEXT_COLOR.WHITE, "Hi-Score");

        displayScore = new Text(pApplet, new Point(UTILS.chunksToPixel(1.5), 8 + 1), TEXT_COLOR.RED, String.format("%05d", score));
        displayScoreHi = new Text(pApplet, new Point(UTILS.chunksToPixel(5.5), 8 + 1), TEXT_COLOR.RED, String.format("%05d", scoreHi));
    }

    /**
     * Sets the current game stage and initializes relevant components.
     *
     * @param gameStage The new stage of the game to be set.
     */
    public void setGameStage(GAME_STAGE gameStage) {
        switch (gameStage) {
            case SPLASH_SCREEN:
                splashScreen = new SplashScreen(pApplet);
                break;
            case GAME_INTRO:
                gameIntro = new GameIntro(pApplet);
                break;
            case MAIN:
                setScore(0); // Reset score when starting the main game stage
                UTILS.setCurrentLevel(1); // Initialize game level
                gameController = new GameController(pApplet, this);
                break;
            case SCORE_RANKING:
                scoreRanking = new ScoreRanking(pApplet);
                break;
        }
        this.gameStage = gameStage;
    }

    /**
     * Handles key press events according to the current game stage, possibly delegating to
     * specific stage handlers like {@code gameController} or changing the game stage.
     *
     * @param keyCode The code representing the key pressed.
     */
    public void keyPressed(int keyCode) {
        if (gameStage == GAME_STAGE.GAME_INTRO) {
            if (gameIntro.getGameIntroStage() == GAME_INTRO_STAGE.SCORE_RANKING) {
                if (keyCode == PConstants.BEVEL) {
                    setGameStage(GAME_STAGE.MAIN);
                }
            }
        } else if (gameStage == GAME_STAGE.SCORE_RANKING) {
            if (keyCode == PConstants.BEVEL) {
                setGameStage(GAME_STAGE.MAIN);
            }
        } else {
            gameController.keyPressed(keyCode);
        }
    }

    /**
     * Sets the player's current score and updates the score display.
     *
     * @param score The new score to set.
     */
    public void setScore(int score) {
        this.score = score;
        displayScore.setString(String.format("%05d", score));
    }

    /**
     * Increases the player's score by a specified amount and updates both the current score
     * and high score displays as necessary.
     *
     * @param points The amount of points to add to the current score.
     */
    public void increaseScoreBy(int points) {
        this.score += points;
        if (score > scoreHi) {
            scoreHi = score;
        }
        displayScore.setString(String.format("%05d", score));
        displayScoreHi.setString(String.format("%05d", scoreHi));
    }

    /**
     * Performs necessary actions when the game is over, such as saving the score and transitioning to the score ranking stage.
     */
    public void gameOver() {
        UTILS.setScore(score);
        CompletableFuture.delayedExecutor(CONSTANTS.RESPAWN_DELAY, TimeUnit.MILLISECONDS).execute(() -> {
            setGameStage(GAME_STAGE.SCORE_RANKING);
        });
    }

    /**
     * Draws the game header, including score and high score displays, to the PApplet.
     *
     * @param pApplet The Processing applet used for rendering.
     */
    private void drawHeader(PApplet pApplet) {
        titleScore.draw(pApplet);
        titleHiScore.draw(pApplet);
        displayScore.draw(pApplet);
        displayScoreHi.draw(pApplet);
    }

    /**
     * Renders the current game stage, delegating to specific stage components as needed.
     *
     * @param pApplet The Processing applet used for rendering.
     */
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
