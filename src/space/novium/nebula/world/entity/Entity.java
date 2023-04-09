package space.novium.nebula.world.entity;

import space.novium.nebula.core.TilePos;
import space.novium.nebula.world.Chunk;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.Level;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.UUID;

public class Entity {
    private Vector2f position;
    private Direction facing;
    private float speed;
    private Vector4f hitBox;

    private final UUID uuid = UUID.randomUUID();
    private final String stringUUID = uuid.toString();

    public Entity(Properties properties){
        this.position = properties.position;
        this.facing = properties.facing;
        this.speed = properties.speed;
        this.hitBox = properties.hitBox;
    }

    public String getUUID(){
        return stringUUID;
    }

    public void tick(Level level){}

    public TilePos getPos(){
        return new TilePos(Chunk.CHUNK_WIDTH - (position.getX() + 1.0f) * ((float) Chunk.CHUNK_WIDTH) / 2.0f, Chunk.CHUNK_HEIGHT - ((position.getY()) + 1.0f) * ((float)Chunk.CHUNK_HEIGHT) / 2.0f);
    }

    public void setPosition(float x, float y){
        position.setX(x);
        position.setY(y);
    }

    public float getSpeed() {
        return speed;
    }

    public Direction getFacing() {
        return facing;
    }

    public Vector2f getPosition(){
        return position.copy();
    }

    public Vector2f getScaledPos(){
        return new Vector2f(((float)Chunk.CHUNK_WIDTH) - (position.getX() + 1.0f) * ((float) Chunk.CHUNK_WIDTH) / 2.0f, ((float)Chunk.CHUNK_HEIGHT) - ((position.getY()) + 1.0f) * ((float)Chunk.CHUNK_HEIGHT) / 2.0f);
    }

    public Vector4f getHitBox() {
        return hitBox.copy();
    }

    private void setSpeed(float speed){
        this.speed = speed;
    }

    public void move(float dx, float dy){
        this.position.add(dx, dy);
    }

    public void move(Vector2f dm){
        move(dm.getX(), dm.getY());
    }

    public void setFacing(Direction dir){
        this.facing = dir;
    }

    public static class Properties {
        Vector2f position = new Vector2f();
        Direction facing = Direction.NORTH;
        float speed = 0.01f;
        EntityCategory category = EntityCategory.PASSIVE_POKEMON;
        Vector4f hitBox = new Vector4f(1.0f);

        public Properties setHitBox(float x, float y, float w, float h){
            this.hitBox = new Vector4f(x, y, w, h);
            return this;
        }

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
