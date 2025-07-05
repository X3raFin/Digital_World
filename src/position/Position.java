package position;

public class Position {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public static int getIndexOfDx(int i) {
        return Position.dx[i];
    }

    public static int getIndexOfDy(int i) {
        return Position.dy[i];
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;
    static private int[] dx = { 1, 0, 0, -1 };
    static private int[] dy = { 0, 1, -1, 0 };
}