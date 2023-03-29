package space.novium.nebula.core.resources.registration;

import com.google.gson.JsonObject;
import space.novium.nebula.core.annotations.AnnotationHandler;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.nebula.world.tiles.Tile;
import space.novium.utils.IOUtils;

public class TileEventRegister implements IEventRegister<Tile> {
    private TextureAtlasHandler.Builder builder;

    public TileEventRegister(TextureAtlasHandler.Builder builder){
        this.builder = builder;
    }

    @Override
    public void registerAll() {
        AnnotationHandler.doEvent(event(), this);
    }

    @Override
    public boolean register(RegistryObject<Tile> value) {
        ResourceLocation loc = value.getKey().getLocation();
        Tile tile = value.get();
        tile.setRegistryName(loc);
        ResourceLocation dataLoc = new ResourceLocation(loc.getNamespace(), "tile/" + loc.getPath());
        JsonObject obj = IOUtils.loadJson(dataLoc);
        if(obj.has("textures")){
            JsonObject textures = obj.getAsJsonObject("textures");
            int i = 0;
            while(textures.has("layer" + i)){
                String imgLoc = textures.get("layer" + i).getAsString();
                ResourceLocation img = new ResourceLocation(loc.getNamespace(), imgLoc);
                builder.loadTexture(img, loc, TextureAtlasType.TILE);
                i++;
            }
            if(i > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public EventType event() {
        return EventType.TILE_REGISTRATION;
    }
}
