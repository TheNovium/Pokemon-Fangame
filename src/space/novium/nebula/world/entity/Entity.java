package space.novium.nebula.world.entity;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.Level;
import space.novium.utils.math.Vector2f;

import java.util.UUID;

public class Entity {
    private Vector2f position;
    private Direction facing;
    private float speed;

    private final UUID uuid = UUID.randomUUID();
    private final String stringUUID = uuid.toString();

    public Entity(Properties properties){
        this.position = properties.position;
        this.facing = properties.facing;
        this.speed = properties.speed;
    }

    public String getUUID(){
        return stringUUID;
    }

    public void tick(Level level){}

    public TilePos getPos(){
        return new TilePos(position);
    }

    public float getSpeed() {
        return speed;
    }

    public Direction getFacing() {
        return facing;
    }

    public Vector2f getPosition(){
        return position;
    }

    private void setSpeed(float speed){
        this.speed = speed;
    }

    public void move(float dx, float dy){
        this.position.add(dx, dy);
    }

    public static class Properties {
        Vector2f position = new Vector2f();
        Direction facing = Direction.NORTH;
        float speed = 0.01f;
        EntityCategory category = EntityCategory.PASSIVE_POKEMON;

        public Properties setSpeed(float speed){
            this.speed = Math.max(0, speed);
            return this;
        }

        public Properties setCategory(EntityCategory category){
            this.category = category;
            return this;
        }
    }
}
