package space.novium.nebula.world.tiles;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;

public class Tile {
    private TilePos position;
    protected ResourceLocation tileType;

    public Tile(TilePos position, ResourceLocation tileType){
        this.position = position;
        this.tileType = tileType;
    }

    public TilePos getPosition() {
        return position;
    }

    public ResourceLocation getTileType() {
        return tileType;
    }

    public void tick(Level level, Player player){}
}
