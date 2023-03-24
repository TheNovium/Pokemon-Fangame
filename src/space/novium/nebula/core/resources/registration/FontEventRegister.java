package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.annotations.AnnotationHandler;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.graphics.renderer.FontRenderer;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;

public class FontEventRegister implements IEventRegister<FontRenderer> {
    private TextureAtlasHandler.Builder builder;

    public FontEventRegister(TextureAtlasHandler.Builder builder){
        this.builder = builder;
    }

    @Override
    public void registerAll(){
        AnnotationHandler.doEvent(event(), this);
    }

    @Override
    public boolean register(RegistryObject<FontRenderer> value){
        FontRenderer renderer = value.get();
        renderer.setRegistryName(value.getKey().getLocation());
        if(renderer.getTexImage() != null){
            builder.loadTexture(value.getKey().getLocation(), TextureAtlasType.TEXT, renderer.getTexImage());
            return true;
        }
        return false;
    }

    @Override
    public EventType event(){
        return EventType.FONT_REGISTRATION;
    }
}
