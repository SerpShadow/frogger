import General.CONSTANTS;
import General.UTILS;
import SpriteLib.ANCHORTYPE;
import SpriteLib.MultiSprite;
import SpriteLib.Point;
import Text.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Handles the introduction animation sequence of the game, displaying a sequence of title animation, point table,
 * and score ranking before the actual gameplay starts.
 */
public class GameIntro {

    private final PApplet pApplet;
    private GAME_INTRO_STAGE gameIntroStage = GAME_INTRO_STAGE.SPAWNING;
    private int counter = 0;

    private final ArrayList<FrogAuto> frogs = new ArrayList<>();
    private final Point frogStartPosition = new Point(CONSTANTS.PIXEL_HORIZONTAL, (int) (7.5 * CONSTANTS.CHUNK_SIZE));

    private final MultiSprite multiSprite = new MultiSprite(24, (int) ((int) CONSTANTS.CHUNK_SIZE * 1.5), ANCHORTYPE.TOP_LEFT);

    private final ArrayList<ArrayList<Text>> pointTable = new ArrayList<>();
    private final Text copyRight;

    private final ScoreRanking scoreRanking;

    /**
     * Constructs a GameIntro instance, initializing the title animation, point table texts, and score ranking view.
     *
     * @param pApplet the PApplet instance for drawing operations.
     */
    public GameIntro(PApplet pApplet) {
        this.pApplet = pApplet;

        // Sprite for title
        PImage spriteMap = pApplet.loadImage("assets/frogger-sprite.png");
        multiSprite.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE + 0 * (CONSTANTS.CHUNK_SIZE + 8), 23 * CONSTANTS.CHUNK_SIZE, 1);
        multiSprite.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE + 1 * (CONSTANTS.CHUNK_SIZE + 8), 23 * CONSTANTS.CHUNK_SIZE, 1);
        multiSprite.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE + 2 * (CONSTANTS.CHUNK_SIZE + 8), 23 * CONSTANTS.CHUNK_SIZE, 1);
        multiSprite.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE + 3 * (CONSTANTS.CHUNK_SIZE + 8), 23 * CONSTANTS.CHUNK_SIZE, 1);
        multiSprite.addFrameCopy(3);
        multiSprite.addFrames(pApplet, spriteMap, 25 * CONSTANTS.CHUNK_SIZE + 4 * (CONSTANTS.CHUNK_SIZE + 8), 23 * CONSTANTS.CHUNK_SIZE, 1);
        multiSprite.addFrameCopy(1);

        // Fill Point Table
        ArrayList<Text> arrayList0 = new ArrayList<>();
        ArrayList<Text> arrayList1 = new ArrayList<>();
        ArrayList<Text> arrayList2 = new ArrayList<>();
        ArrayList<Text> arrayList3 = new ArrayList<>();
        ArrayList<Text> arrayList4 = new ArrayList<>();

        arrayList0.add(new Text(pApplet, new Point(3 * CONSTANTS.CHUNK_SIZE + 8, 4 * CONSTANTS.CHUNK_SIZE + CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.WHITE, "-Point Table-"));
        arrayList1.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 6 * CONSTANTS.CHUNK_SIZE), TEXT_COLOR.YELLOW, "10 PTS for each step"));
        arrayList2.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 7 * CONSTANTS.CHUNK_SIZE), TEXT_COLOR.YELLOW, "50 PTS for every frog"));
        arrayList2.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 7 * CONSTANTS.CHUNK_SIZE + CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.RED, "arrived home safely"));
        arrayList3.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 8 * CONSTANTS.CHUNK_SIZE + CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.YELLOW, "1000 PTS by saving frogs"));
        arrayList3.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 9 * CONSTANTS.CHUNK_SIZE), TEXT_COLOR.RED, "into five homes"));
        arrayList4.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 10 * CONSTANTS.CHUNK_SIZE), TEXT_COLOR.YELLOW, "Plus Bonus"));
        arrayList4.add(new Text(pApplet, new Point(CONSTANTS.CHUNK_SIZE, 10 * CONSTANTS.CHUNK_SIZE + CONSTANTS.CHUNK_SIZE_HALF), TEXT_COLOR.RED, "10 PTS x remaining second"));

        pointTable.add(arrayList0);
        pointTable.add(arrayList1);
        pointTable.add(arrayList2);
        pointTable.add(arrayList3);
        pointTable.add(arrayList4);

        copyRight = new Text(pApplet, new Point(3 * CONSTANTS.CHUNK_SIZE, 13 * CONSTANTS.CHUNK_SIZE), TEXT_COLOR.WHITE, "Konami  ©  1981");


        scoreRanking = new ScoreRanking(pApplet);

        addNewFrog(0);
    }

    /**
     * Retrieves the current stage of the game intro.
     *
     * @return The current {@link GAME_INTRO_STAGE} of the intro.
     */
    public GAME_INTRO_STAGE getGameIntroStage() {
        return gameIntroStage;
    }

    /**
     * Adds a new frog to the intro animation sequence at a specified index.
     *
     * @param index The index at which the new frog is added to the animation sequence.
     */
    private void addNewFrog(int index) {
        FrogAuto frogAuto = new FrogAuto(pApplet, frogStartPosition, 2);
        frogAuto.goToPositionX(UTILS.chunksToPixel(2) + UTILS.chunksToPixel(1.5) * index);
        frogs.add(frogAuto);
    }

    private void startMovingUp() {
        for (FrogAuto frogAuto : frogs) {
            frogAuto.goToPositionY(3 * CONSTANTS.CHUNK_SIZE);
        }
    }

    private void drawTitle(PApplet pApplet) {
        drawTitle(pApplet, 7);
    }

    private void drawTitle(PApplet pApplet, int index) {
        for (int i = 0; i < index; i++) {
            multiSprite.setFrame(i);
            multiSprite.draw(pApplet, new Point(CONSTANTS.CHUNK_SIZE * 2 + (CONSTANTS.CHUNK_SIZE + CONSTANTS.CHUNK_SIZE_HALF) * i, 3 * CONSTANTS.CHUNK_SIZE));
        }
    }

    /**
     * Draws the game intro sequence, updating according to the current animation stage.
     * Manages transitions between SPAWNING, MOVING_UP, TITLE, POINT_TABLE, and SCORE_RANKING stages.
     *
     * @param pApplet The PApplet instance for drawing.
     */
    public void draw(PApplet pApplet) {

        switch (gameIntroStage) {
            case SPAWNING:
                for (int i = 0; i < frogs.size(); i++) {
                    frogs.get(i).draw(pApplet);
                    if (i == frogs.size() - 1) {
                        if (frogs.size() < 7) {
                            if (!frogs.get(i).isMoving()) {
                                addNewFrog(i + 1);
                            }
                        } else {
                            if (!frogs.get(i).isMoving()) {
                                startMovingUp();
                                gameIntroStage = GAME_INTRO_STAGE.MOVING_UP;
                            }
                        }
                    }
                }
                break;
            case MOVING_UP:

                for (FrogAuto frogAuto : frogs) {
                    frogAuto.draw(pApplet);
                }

                if (!frogs.getFirst().isMoving()) {
                    counter = 0;
                    gameIntroStage = GAME_INTRO_STAGE.TITLE;

                }
                break;
            case TITLE:
                int indexFrog = (int) Math.floor((double) counter / 20);

                if (indexFrog < 8) {

                    drawTitle(pApplet, indexFrog);

                    for (FrogAuto frogAuto : frogs) {
                        frogAuto.draw(pApplet);
                    }

                    if (counter % 20 == 0) {
                        frogs.removeFirst();
                    }
                } else {
                    counter = 0;
                    gameIntroStage = GAME_INTRO_STAGE.POINT_TABLE;
                }
                break;
            case POINT_TABLE:
                drawTitle(pApplet);

                int index = (int) Math.floor((double) counter / 40);
                for (int i = 0; i < index && i < pointTable.size(); i++) {
                    for (Text text : pointTable.get(i)) {
                        text.draw(pApplet);
                    }
                }

                copyRight.draw(pApplet);

                if (counter > 300) {
                    counter = 0;
                    gameIntroStage = GAME_INTRO_STAGE.SCORE_RANKING;
                }
                break;
            case SCORE_RANKING:
                drawTitle(pApplet);
                scoreRanking.draw(pApplet);
                break;
        }

        counter += 1;
    }
}
