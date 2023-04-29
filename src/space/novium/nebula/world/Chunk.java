package space.novium.nebula.world;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Entity;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.nebula.world.tiles.Tile;
import space.novium.utils.data.Table;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.*;

public class Chunk {
    public static final int CHUNK_HEIGHT = 8;
    public static final int CHUNK_WIDTH = 12;
    //TODO implement a custom hash based data struct that allows for stacking of tiles
    private final Table<Tile> tiles;
    private final List<Tile> tickingTiles;
    private final Level level;
    private final Vector2f spawnLoc;

    public Chunk(ILevelScene scene, Level level, JsonObject chunkInfo){
        this.level = level;
        tiles = new Table<>(CHUNK_WIDTH, CHUNK_HEIGHT);
        tickingTiles = new ArrayList<>();
        Map<String, ResourceLocation> definitions = new HashMap<>();
        if(chunkInfo.has("define")){
            JsonObject define = chunkInfo.get("define").getAsJsonObject();
            if(define.has("tile")){
                JsonObject tiles = define.get("tile").getAsJsonObject();
                for(String keys : tiles.keySet()){
                    definitions.put(keys, new ResourceLocation(tiles.get(keys).getAsString()));
                }
            }
            //TODO implement getting entities
            if(define.has("entities")){

            }
        }
        if(chunkInfo.has("tiles")) {
            JsonArray layers = chunkInfo.getAsJsonArray("tiles");
            for(int z = 0; z < layers.size(); z++){
                JsonArray layer = layers.get(z).getAsJsonArray();
                for(int y = 0; y < layer.size(); y++){
                    String row = layer.get(y).getAsString();
                    for(int x = 0; x < row.length(); x++){
                        ResourceLocation tileLoc = definitions.get(String.valueOf(row.charAt(x)));
                        if(tileLoc != null){
                            Tile tile = Registry.TILE_REGISTRY.getValue(tileLoc);
                            tile.setPosition(x, y);
                            tile.setRegistryName(tileLoc);
                            if(tile.ticks()) tickingTiles.add(tile);
                            level.addTile(tile, z);
                            tiles.add(tile, x, y);
                        }
                    }
                }
            }
        }
        if(chunkInfo.has("spawn")){
            JsonObject spawnInfo = chunkInfo.get("spawn").getAsJsonObject();
            spawnLoc = new Vector2f(
                spawnInfo.has("x") ? spawnInfo.get("x").getAsFloat() : 0.0f,
                spawnInfo.has("y") ? spawnInfo.get("y").getAsFloat() : 0.0f
            );
        } else {
            spawnLoc = new Vector2f();
        }
    }

    public Vector2f movementAllowed(Direction direction, Entity entity){
        Vector2f speed = new Vector2f(2.0f);
        Vector2f pos = entity.getPosition();
        System.out.println(pos);
        Vector4f entityHitBox = entity.getHitBox();
        speed.mult((float)direction.getDirX(), (float)direction.getDirY());
        speed.mult(entity.getSpeed());
        Set<TilePos> interactionTiles = new HashSet<>(4);
        interactionTiles.add(new TilePos(pos.getX(), pos.getY()));
        interactionTiles.add(new TilePos(pos.getX() + entityHitBox.getW(), pos.getY()));
        interactionTiles.add(new TilePos(pos.getX() + entityHitBox.getW(), pos.getY() + entityHitBox.getH()));
        interactionTiles.add(new TilePos(pos.getX(), pos.getY() + entityHitBox.getH()));
        for(TilePos tPos : interactionTiles){
            List<Tile> tilesAtPos = tiles.get(tPos.getX(), tPos.getY());
            for(Tile tile : tilesAtPos){
                if(tile.collide()){
                    Vector4f tileHitBox = tile.getHitBox();
                    tileHitBox.add((float) tPos.getX(), (float) tPos.getY(), 0.0f, 0.0f);
                }
            }
        }
        speed.mult(0.5f);
        return speed;
    }

    public void tick(){
        for(Tile t : tickingTiles){
            t.tick(level, level.getPlayer(), level.random);
        }
    }
    
    public Vector2f getSpawnLoc(){
        return spawnLoc.copy();
    }
    
    public static Vector2f getNormalizedDrawLocation(float x, float y){
        return new Vector2f(
                x / (CHUNK_WIDTH / 2.0f) - 1.0f,
                y / (CHUNK_HEIGHT / 2.0f) - 1.0f
        );
    }
    
    public static Vector2f getNormalizedDrawLocation(Vector2f vec){
        return getNormalizedDrawLocation(vec.getX(), vec.getY());
    }
}
