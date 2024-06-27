package Text;

public enum TEXT_COLOR {
    WHITE(0),
    YELLOW(1),
    RED(2),
    BLUE(3),
    PURPLE(4);

    private final int i;

    TEXT_COLOR(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
