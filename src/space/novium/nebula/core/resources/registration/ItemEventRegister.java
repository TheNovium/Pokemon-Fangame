package space.novium.nebula.core.resources.registration;

import com.google.gson.JsonObject;
import space.novium.nebula.core.annotations.AnnotationHandler;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.item.Item;
import space.novium.utils.IOUtils;

public class ItemEventRegister implements IEventRegister<Item> {
    private TextureAtlasHandler.Builder builder;

    public ItemEventRegister(TextureAtlasHandler.Builder builder){
        this.builder = builder;
    }

    @Override
    public void registerAll(){
        AnnotationHandler.doEvent(event(), this);
    }

    @Override
    public boolean register(RegistryObject<Item> value){
        ResourceLocation loc = value.getKey().getLocation();
        Item item = value.get();
        item.setRegistryName(loc);
        ResourceLocation dataLoc = new ResourceLocation(loc.getNamespace(), "item/" + loc.getPath() + ".json");
        JsonObject obj = IOUtils.loadJson(dataLoc);
        //TODO add texture to texture atlas based off string information
        return false;
    }

    @Override
    public EventType event(){
        return EventType.ITEM_REGISTRATION;
    }
}
