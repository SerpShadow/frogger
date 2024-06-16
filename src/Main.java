import General.CONSTANTS;
import processing.core.PApplet;
import processing.core.PImage;


public class Main extends PApplet {
    private int windowWidth = CONSTANTS.CHUNKS_HORIZONTAL * CONSTANTS.CHUNK_SIZE * 3;
    private int windowHeight = CONSTANTS.CHUNKS_VERTICAL * CONSTANTS.CHUNK_SIZE * 3;

    private int nextAnimAt = 0;

    private Game game;

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
        game = new Game(this);
    }

    @Override
    public void keyPressed() {
//        System.out.println("keyPressed: " + keyCode);
//        System.out.println(keyCode);
//        System.out.println("keyPressed: " + key);
//        System.out.println(key);
//        if (key == CODED) {
        if (game != null) game.keyPressed(keyCode);
//        }
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
        nextAnimAt = millis() + 33; // 30fps
        scale(3);
        background(0, 0, 0);

        if (game != null) game.draw(this);
    }

}