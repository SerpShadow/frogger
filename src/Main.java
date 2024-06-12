import General.CONSTANTS;
import processing.core.PApplet;
import processing.core.PImage;


public class Main extends PApplet {
    private int windowWidth = CONSTANTS.CHUNKS_HORIZONTALLY * CONSTANTS.CHUNK_SIZE * 3;
    private int windowHeight = CONSTANTS.CHUNKS_VERTICALLY * CONSTANTS.CHUNK_SIZE * 3;

    private int nextAnimAt = 0;

    private Game game;
    private SplashScreen splashScreen;

//    private ArrayList<ArrayList> arrayList = new ArrayList<>(){
//        new ArrayList<>();
//    };


//    int currentLevel = 0;
//
//    private LevelData[] LevelList = new LevelData[]{
//            new LevelData(2, 1, 2, 2, 2, 1, 2, 1, 2, 1)
//    };
//
//    public LevelData getCurrentLevelData() {
//        return LevelList[currentLevel];
//    }


//    levelData[0] =

    /**
     * Statische main-Funktion als erste Methode beim Programmstart
     *
     * @param args Argumente, die der Amwendung beim Start übergeben wurden
     */
    public static void main(String[] args) {
        PApplet.main("Main");
    }

    /**
     * Setzen einiger Processing-Parameter vor der Darstellung des App.-Windows
     * Die PApplet-Instanz ist hier noch nicht vollständig initialisiert
     *
     * @see Main.setup()
     */
    @Override
    public void settings() {
        setSize(windowWidth, windowHeight);
    }

    /**
     * setup wird von Processing aufgerufen, wenn die Lib fertig initialisiert und das
     * App.-Window dargestellt wurde.
     */
    @Override
    public void setup() {
        PImage spriteMap = loadImage("assets/frogger-sprite.png");
        splashScreen = new SplashScreen(this);
    }

    @Override
    public void keyPressed() {
        System.out.println("keyPressed: " + keyCode);
        System.out.println(keyCode);
        System.out.println("keyPressed: " + key);
        System.out.println(key);
//        if (key == CODED) {
        if (game != null) game.keyPressed(keyCode);
//        }
    }

    public void initializeGame() {
        if (game == null) game = new Game(this);
    }

    /**
     * die Draw-Funktion wird von Processing "ununterbrochen" aufgerufen. Die Frequenz ist
     * dabei abhängig von der CPU-Geschwindigkeit. Zur Normierung der FPS die millis()-Funktion
     * von Processing nutzen.
     */
    @Override
    public void draw() {
        if (millis() < nextAnimAt)
            return;
        nextAnimAt = millis() + 33; // 50fps
        scale(3);
        background(0, 0, 0);

        pushMatrix();
        translate(0, 0);
        fill(0, 0, 71);
        rect(0, 0, CONSTANTS.CHUNKS_HORIZONTALLY * CONSTANTS.CHUNK_SIZE, (int) (7.5 * CONSTANTS.CHUNK_SIZE) + 8);
        popMatrix();

        if (splashScreen != null) {
            splashScreen.draw(this);
            if (splashScreen.isFinished()) initializeGame();
        }

        if (game != null) game.draw(this);
    }

}