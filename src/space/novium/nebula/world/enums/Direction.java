package space.novium.nebula.world.enums;

import space.novium.nebula.core.TilePos;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private final int dirX;
    private final int dirY;

    Direction(int x, int y){
        this.dirX = x;
        this.dirY = y;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public TilePos getDirection(TilePos pos){
        return getDirection(pos, 1);
    }

    public TilePos getDirection(TilePos pos, int offset){
        return new TilePos(pos.getX() + (offset * dirX), pos.getY() + (offset * dirY));
    }
}
