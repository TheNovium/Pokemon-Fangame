package space.novium.nebula.core;

import space.novium.utils.math.Vector2f;

public class TilePos {
    private int x, y;

    public static final TilePos ZERO = new TilePos(0, 0);

    public TilePos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public TilePos(double x, double y){
        this.x = (int)Math.floor(x);
        this.y = (int)Math.floor(y);
    }

    public TilePos(Vector2f vec2){
        this(vec2.x, vec2.y);
    }

    public TilePos(IPosition pos){
        this(pos.x(), pos.y());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
