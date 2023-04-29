package space.novium.level.registration;

import space.novium.nebula.core.event.EventListener;
import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.Registry;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.registration.IEventRegister;
import space.novium.nebula.world.tiles.RadiationBarrel;
import space.novium.nebula.world.tiles.TallGrass;
import space.novium.nebula.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GameTiles {
    private static final List<RegistryObject<Tile>> TILES = new ArrayList<>();

    public static final RegistryObject<Tile> RED_BRICK = register("red_brick", Tile::new);
    public static final RegistryObject<Tile> RADIATION_BARREL = register("radiation_barrel", RadiationBarrel::new);
    public static final RegistryObject<Tile> TALL_GRASS = register("tall_grass", TallGrass::new);
    public static final RegistryObject<Tile> TEST_TILE = register("test_tile", Tile::new);

    @EventListener(event = EventType.TILE_REGISTRATION)
    public static void registerTiles(IEventRegister<Tile> register){
        for(RegistryObject<Tile> loc : TILES){
            register.register(loc);
        }
    }

    private static RegistryObject<Tile> register(String name, Supplier<Tile> tile){
        RegistryObject<Tile> ret = Registry.TILE_REGISTRY.register(name, tile);
        TILES.add(ret);
        return ret;
    }
}
