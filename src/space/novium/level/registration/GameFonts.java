package space.novium.level.registration;

import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.core.resources.registration.IEventRegister;
import space.novium.nebula.graphics.renderer.FontRenderer;

import java.awt.*;

public class GameFonts {
    public static final ResourceLocation BASE_FONT = new ResourceLocation("base_font");
    public static final ResourceLocation SYMBOLMON = new ResourceLocation("symbol_font");

    public static final RegistryObject<FontRenderer> BASE_NORMAL = Registry.FONT_REGISTRY.register("base_white", () -> new FontRenderer(BASE_FONT, Color.WHITE, 72.0f, false));
    public static final RegistryObject<FontRenderer> SYMBOL_NORMAL = Registry.FONT_REGISTRY.register("symbol_white", () -> new FontRenderer(SYMBOLMON, Color.WHITE, 96.0f, false));

    @EventListener(event = EventType.FONT_REGISTRATION)
    public static void register(IEventRegister<FontRenderer> event){
        event.register(BASE_NORMAL);
        event.register(SYMBOL_NORMAL);
    }
}
