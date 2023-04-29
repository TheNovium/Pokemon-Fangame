package space.novium.nebula.world;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Entity;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.utils.IOUtils;
import space.novium.utils.math.Vector2f;

import java.util.LinkedList;
import java.util.List;

public class ChunkLoader {
    //Will eventually load multiple chunks, but for now we'll only load the test chunk
    private List<Chunk> chunks;

    private Chunk tempChunk;
    
    public ChunkLoader(ResourceLocation startingChunk, ILevelScene scene, Level level){
        chunks = new LinkedList<>();
        tempChunk = new Chunk(scene, level, IOUtils.loadJson(startingChunk));
    }

    public void move(Entity entity, Direction dir){
        Vector2f allowedMovement = tempChunk.movementAllowed(dir, entity);
        entity.move(allowedMovement);
    }

    public void tick(){
        tempChunk.tick();
    }
}
