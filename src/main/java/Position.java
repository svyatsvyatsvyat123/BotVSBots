public class Position {
    int x, y;

    public Position(int x_, int y_) {
        x = x_;
        y = y_;
    }

    public static Position Add(Position a, Position b) {
        return new Position(a.x + b.x, a.y + b.y);
    }

    public static Position Sub(Position a, Position b) {
        return new Position(a.x - b.x, a.y - b.y);
    }
}
