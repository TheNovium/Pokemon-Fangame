package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.texture.TextureAtlasType;

import java.util.ArrayList;
import java.util.List;

public class GameResourceLocations {
    private static final List<ResourceLocation> BACKGROUND_RESOURCES = new ArrayList<>();
    private static final List<ResourceLocation> UI_RESOURCES = new ArrayList<>();

    //Locations of background images for stuff like menus
    public static final ResourceLocation PIXEL_BACKGROUND = registerBackground("intro");
    public static final ResourceLocation PLANET = registerBackground("homepage");

    //Locations of UI elements
    public static final ResourceLocation WARNING_SYMBOL = registerUIComponent("symbols/warning");
    public static final ResourceLocation LIGHT_GREATER_ARROW = registerUIComponent("light_arrow");
    public static final ResourceLocation LIGHT_GREY_BANNER = registerUIComponent("banner/light_grey");

    @EventListener(event = EventType.IMAGE_REGISTRATION)
    public static void registerImages(ResourceEventRegister register){
        for(ResourceLocation loc : BACKGROUND_RESOURCES){
            register.register(loc, TextureAtlasType.BACKGROUND);
        }
        for(ResourceLocation loc : UI_RESOURCES){
            register.register(loc, TextureAtlasType.UI);
        }
    }

    private static ResourceLocation registerBackground(String loc){
        ResourceLocation rl = new ResourceLocation("background/" + loc);
        BACKGROUND_RESOURCES.add(rl);
        return rl;
    }

    private static ResourceLocation registerUIComponent(String loc){
        ResourceLocation rl = new ResourceLocation("gui/" + loc);
        UI_RESOURCES.add(rl);
        return rl;
    }
}
