package space.novium.nebula.world.enums;

import space.novium.nebula.core.TilePos;

public enum Direction {
    NORTH(0, -1),
    EAST(-1, 0),
    SOUTH(0, 1),
    WEST(1, 0);

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

    public Direction getClockwise(){
        switch (this){
            case EAST -> {
                return SOUTH;
            }
            case SOUTH -> {
                return WEST;
            }
            case WEST -> {
                return NORTH;
            }
            case NORTH -> {
                return EAST;
            }
        }
        return this;
    }

    public Direction getCounterClockwise(){
        switch(this){
            case NORTH -> {
                return WEST;
            }
            case EAST -> {
                return NORTH;
            }
            case SOUTH -> {
                return EAST;
            }
            case WEST -> {
                return SOUTH;
            }
        }
        return this;
    }

    public TilePos getDirection(TilePos pos){
        return getDirection(pos, 1);
    }

    public TilePos getDirection(TilePos pos, int offset){
        return new TilePos(pos.getX() + (offset * dirX), pos.getY() + (offset * dirY));
    }
}
