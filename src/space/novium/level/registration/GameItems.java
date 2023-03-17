package space.novium.level.registration;

import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.registration.IEventRegister;
import space.novium.nebula.item.Item;
import space.novium.nebula.item.pokeball.PokeballItem;

public class GameItems {
    private GameItems(){}

    public static final RegistryObject<Item> POKEBALL = Registry.ITEM_REGISTRY.register("pokeball", PokeballItem::new);
    public static final RegistryObject<Item> RADIOBALL = Registry.ITEM_REGISTRY.register("radioball", PokeballItem::new);

    @EventListener(event = EventType.ITEM_REGISTRATION)
    public static void register(IEventRegister<Item> register){
        register.register(POKEBALL);
        register.register(RADIOBALL);
    }
}
