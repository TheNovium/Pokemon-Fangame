package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.texture.TextureAtlasType;

import java.util.ArrayList;
import java.util.List;

public class GameResourceLocations {
    private static final List<ResourceLocation> BACKGROUND_RESOURCES = new ArrayList<>();

    //Locations of background images for stuff like menus
    public static final ResourceLocation PIXEL_BACKGROUND = registerBackground("background/loading_fractal");

    @EventListener(event = EventType.IMAGE_REGISTRATION)
    public static void registerImages(ResourceEventRegister register){
        for(ResourceLocation loc : BACKGROUND_RESOURCES){
            register.register(loc, TextureAtlasType.BACKGROUND);
        }
    }

    private static ResourceLocation registerBackground(String loc){
        ResourceLocation rl = new ResourceLocation(loc);
        BACKGROUND_RESOURCES.add(rl);
        return rl;
    }
}
