package space.novium.nebula.world.level;

import space.novium.nebula.graphics.Camera;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.tiles.Tile;

public interface ILevelScene {
    void setRegion(String string);

    void addTile(Tile tile, int z);
    
    Camera getCamera();
}
