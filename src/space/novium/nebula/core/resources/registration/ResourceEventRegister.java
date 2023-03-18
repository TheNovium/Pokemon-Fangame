package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.annotations.AnnotationHandler;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;

public class ResourceEventRegister {
    private TextureAtlasHandler.Builder builder;

    public ResourceEventRegister(TextureAtlasHandler.Builder builder){
        this.builder = builder;
    }

    public void registerAll(){
        AnnotationHandler.doEvent(EventType.IMAGE_REGISTRATION, this);
    }

    public void register(ResourceLocation loc, TextureAtlasType atlasType){
        builder.loadTexture(loc, atlasType);
    }
}
