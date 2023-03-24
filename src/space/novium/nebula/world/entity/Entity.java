package space.novium.nebula.world.entity;

import space.novium.nebula.world.enums.Direction;
import space.novium.utils.math.Vector2f;

import java.util.UUID;

public class Entity {
    private Vector2f position;
    private Direction facing;
    private float speed;

    private UUID uuid = UUID.randomUUID();
    private String stringUUID = uuid.toString();

    public Entity(){}
}
