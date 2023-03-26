package space.novium.nebula.world.tiles;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;

public class Tile {
    private TilePos position;
    protected ResourceLocation tileType;

    public Tile(){
        this(new TilePos(0, 0));
    }

    public Tile(TilePos position){
        this.position = position;
    }

    public TilePos getPosition() {
        return position;
    }

    public void setPosition(int x, int y){
        position.setPosition(x, y);
    }

    public ResourceLocation getTileType() {
        return tileType;
    }

    public void tick(Level level, Player player){}
}
