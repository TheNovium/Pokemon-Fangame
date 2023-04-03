package space.novium.nebula.world;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import space.novium.nebula.core.TilePos;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.entity.Entity;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.enums.Direction;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.nebula.world.tiles.Tile;
import space.novium.utils.data.Table;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector2i;
import space.novium.utils.math.Vector4f;

import java.util.*;

public class Chunk {
    public static final int CHUNK_HEIGHT = 8;
    public static final int CHUNK_WIDTH = 12;
    //TODO implement a custom hash based data struct that allows for stacking of tiles
    private final Table<Tile> tiles;

    public Chunk(ILevelScene scene, Level level, JsonObject chunkInfo){
        tiles = new Table<>(CHUNK_WIDTH, CHUNK_HEIGHT);
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
                            tile.setRegistryName(tileLoc);
                            tile.setPosition(x, y);
                            level.addTile(tile, z);
                            tiles.add(tile, x, y);
                        }
                    }
                }
            }
        }
    }

    public Vector2f movementAllowed(Direction direction, Entity entity){
        Vector2f ret = new Vector2f(entity.getSpeed() * direction.getDirX(), entity.getSpeed() * direction.getDirY());
        TilePos pos = entity.getPos();
        pos.setPosition((CHUNK_WIDTH - 1) - pos.getX(), (CHUNK_HEIGHT - 1) - pos.getY());
        System.out.println(pos);
        Vector4f entityHitBox = entity.getHitBox();
        List<Tile> tilesAtPosition = tiles.get(pos.getX(), pos.getY());
        for(Tile t : tilesAtPosition){
            if(t.collide()){
                return new Vector2f(0, 0);
            }
        }
        return ret;
    }
}
