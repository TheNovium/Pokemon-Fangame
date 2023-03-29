package space.novium.nebula.world.tiles;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(position.getX() % 16, position.getY() % 12);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tile tile){
            return tile.getPosition().compareTo(getPosition()) == 0 &&
                    tile.getTileType().equals(getTileType());
        }
    }
}
