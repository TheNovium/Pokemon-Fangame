package space.novium.nebula.world.tiles;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.Objects;

public class Tile {
    private TilePos position;
    protected ResourceLocation tileType;
    protected String registryName;

    public Tile(){
        this(new TilePos(0, 0));
    }

    public Tile(TilePos position){
        this.position = position;
    }

    public TilePos getPosition() {
        return position;
    }

    public Vector2f getAbsolutePosition(){
        return new Vector2f(((float) position.getX()) / 8.0f - 1.0f, ((float) position.getY()) / 6.0f - 1.0f);
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
        return false;
    }

    public void setRegistryName(ResourceLocation loc){
        setRegistryName(loc.toString());
    }

    public void setRegistryName(String name){
        if(getRegistryName() != null){
            System.err.println("Unable to set registry name to " + name + " because tile is already registered as "+ getRegistryName());
        } else {
            this.registryName = name;
        }
    }

    public String getRegistryName(){
        return registryName;
    }

    public boolean collide(){
        return false;
    }

    public Vector4f getHitBox(){
        return new Vector4f();
    }

    public Tile clone() {
        Tile ret = new Tile(getPosition());
        ret.setRegistryName(getRegistryName());
        return ret;
    }

    @Override
    public String toString() {
        return getRegistryName() + "@(" + getPosition().toString() + ")";
    }
}
