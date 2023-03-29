package space.novium.nebula.world.level;

import space.novium.nebula.world.tiles.Tile;

public interface ILevelScene {
    void setRegion(String string);

    void addTile(Tile tile);
}
