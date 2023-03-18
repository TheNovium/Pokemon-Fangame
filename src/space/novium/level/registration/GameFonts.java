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

    public static final RegistryObject<FontRenderer> BASE_LARGE = Registry.FONT_REGISTRY.register("base_large", () -> new FontRenderer(BASE_FONT, Color.WHITE, 96.0f, false));
    public static final RegistryObject<FontRenderer> BASE_NORMAL = Registry.FONT_REGISTRY.register("base_white", () -> new FontRenderer(BASE_FONT, Color.WHITE, 72.0f, false));
    public static final RegistryObject<FontRenderer> BASE_SMALL = Registry.FONT_REGISTRY.register("base_small", () -> new FontRenderer(BASE_FONT, Color.WHITE, 48.0f, false));
    public static final RegistryObject<FontRenderer> BASE_TINY = Registry.FONT_REGISTRY.register("base_tiny", () -> new FontRenderer(BASE_FONT, Color.WHITE, 24.0f, false));
    public static final RegistryObject<FontRenderer> SYMBOL_NORMAL = Registry.FONT_REGISTRY.register("symbol_white", () -> new FontRenderer(SYMBOLMON, Color.WHITE, 128.0f, false));

    @EventListener(event = EventType.FONT_REGISTRATION)
    public static void register(IEventRegister<FontRenderer> event){
        event.register(BASE_LARGE);
        event.register(BASE_NORMAL);
        event.register(BASE_SMALL);
        event.register(BASE_TINY);
        event.register(SYMBOL_NORMAL);
    }
}
