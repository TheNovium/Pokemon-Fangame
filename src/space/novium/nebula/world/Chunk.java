package space.novium.nebula.world;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;
import space.novium.nebula.world.tiles.Tile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Chunk {
    //TODO implement a custom hash based data struct that allows for stacking of tiles
    private final Set<Tile> tiles;

    public Chunk(ILevelScene scene, Level level, JsonObject chunkInfo){
        tiles = new HashSet<>();
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
                        Tile tile = Registry.TILE_REGISTRY.getValue(tileLoc);
                        tile.setRegistryName(tileLoc);
                        tile.setPosition(x, y);
                        level.addTile(tile, z);
                    }
                }
            }
        }
    }

    public void tick(){

    }
}
