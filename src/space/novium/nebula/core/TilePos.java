package space.novium.nebula.core;

import space.novium.nebula.world.enums.Direction;
import space.novium.utils.math.Vector2f;

import java.util.Objects;

public class TilePos implements Comparable<TilePos> {
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



    /**
     * @return the x position as an integer
     * **/
    public int getX() {
        return x;
    }

    /**
     * @return the y position as an integer
     * **/
    public int getY() {
        return y;
    }

    /**
     * Gets a tile position one away in the specified direction
     *
     * @param dir The direction to check
     *
     * @return the TilePos of that direction
     * **/
    public TilePos getDirection(Direction dir){
        return getDirection(dir, 1);
    }

    /**
     * Gets a tile position a specified amount away in the specified direction
     *
     * @param dir The direction to check
     * @param length The distance to cover
     *
     * @return the TilePos at the specified distance and direction
     * **/
    public TilePos getDirection(Direction dir, int length){
        return new TilePos(getX() + dir.getDirX() * length, getY() + dir.getDirY() * length);
    }

    /**
     * Set the position of the tile
     *
     * @param x the x position of the tile
     * @param y the y position of the tile
     * **/
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }


    /**
     * Creates a copy of the tile
     *
     * @return a TilePos with the same x and y location
     * **/
    public TilePos copy(){
        return new TilePos(x, y);
    }

    /**
     * @return the manhattan distance between two positions
     * **/
    @Override
    public int compareTo(TilePos o) {
        return Math.abs(x - o.getX()) + Math.abs(y - o.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TilePos other){
            return x == other.getX() && y == other.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
