package space.novium.nebula.core.resources;

import space.novium.Game;
import space.novium.nebula.graphics.renderer.FontRenderer;
import space.novium.nebula.item.Item;
import space.novium.nebula.world.entity.Entity;
import space.novium.nebula.world.tiles.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Registry<T> {
    private static final Map<ResourceLocation, Supplier<?>> LOADERS = new HashMap<>();
    private static final Map<ResourceLocation, Registry<?>> REGISTRIES = new HashMap<>();
    public static final ResourceLocation ROOT_REGISTRY_NAME = new ResourceLocation("root");
    public static final ResourceKey<Registry<Item>> ITEMS = createRegistryKey("items");
    public static final ResourceKey<Registry<FontRenderer>> FONTS = createRegistryKey("fonts");
    public static final ResourceKey<Registry<Entity>> ENTITIES = createRegistryKey("entities");
    public static final ResourceKey<Registry<Tile>> TILES = createRegistryKey("tiles");

    public static final Registry<Item> ITEM_REGISTRY = new Registry<>(ITEMS);
    public static final Registry<FontRenderer> FONT_REGISTRY = new Registry<>(FONTS);
    public static final Registry<Entity> ENTITY_REGISTRY = new Registry<>(ENTITIES);
    public static final Registry<Tile> TILE_REGISTRY = new Registry<>(TILES);

    /**
     * Creates a key for the register
     *
     * @param key The name of the register
     * @param <T> The type of the register
     *
     * @return A resource key that points to the registry
     * **/
    private static <T> ResourceKey<Registry<T>> createRegistryKey(String key){
        return ResourceKey.createRegistryKey(new ResourceLocation(key));

    }

    /**
     * Returns the registery at the specified resource location
     *
     * @param loc The location of the registry
     *
     * @return The registry at the location if it exists
     * **/
    public static Registry<?> getRegistry(ResourceLocation loc){
        return REGISTRIES.getOrDefault(loc, null);
    }

    private final ResourceKey<? extends Registry<T>> key;
    private final Map<ResourceKey<?>, Supplier<T>> createMap;
    private final Map<ResourceLocation, Supplier<T>> locationMap;

    /**
     * Creates the registry
     *
     * @param key The ResourceKey to create the registry at
     * **/
    public Registry(ResourceKey<Registry<T>> key){
        this.key = key;
        this.createMap = new HashMap<>();
        this.locationMap = new HashMap<>();
        REGISTRIES.put(key.getLocation(), this);
    }

    /**
     * @return The ResourceKey of the Registry
     * **/
    public ResourceKey<? extends Registry<T>> getKey(){
        return key;
    }

    /**
     * Creates a RegistryObject for the register
     *
     * @param name The name of the object to register
     * @param supp Supplier to create a new object
     * @param <I> Extends the registry type
     *
     * @return A registry object matching the registry type
     * **/
    public <I extends T> RegistryObject<I> register(final String name, final Supplier<? extends I> supp){
        final ResourceLocation loc = new ResourceLocation(name);
        RegistryObject<I> ret;
        if(key != null){
            ret = RegistryObject.create(loc, key, Game.ID);
        } else {
            throw new IllegalStateException("Cannot process a registry without a key!");
        }
        Supplier<? extends T> val = locationMap.putIfAbsent(ret.getKey().getLocation(), supp::get);
        if(val != null) throw new IllegalArgumentException("Unable to add duplicate registration " + name);
        createMap.put(ret.getKey(), supp::get);
        ret.updateRegisterReference();
        return ret;
    }

    /**
     * @return An object of the registry type at the resource location
     * **/
    public T getValue(ResourceLocation loc){
        Supplier<T> temp = locationMap.getOrDefault(loc, null);
        if(temp == null){
            System.out.println("Failed to load from " + loc.toString());
        }
        return temp != null ? temp.get() : null;
    }

    /**
     * Checks if the registry contains the object specified
     *
     * @param cont ResourceKey of the object to check for
     *
     * @return true if the object is found
     * **/
    public boolean containsKey(ResourceKey<T> cont){
        return createMap.containsKey(cont);
    }


    /**
     * Checks if the registry contains the object specified
     *
     * @param cont ResourceLocation of the object to check for
     *
     * @return true if the object is found
     * **/
    public boolean containsKey(ResourceLocation cont){
        return locationMap.containsKey(cont);
    }

    /**
     * Finds an object using the specified key
     *
     * @param supp the key to search for
     *
     * @return A supplier of the object if it is found
     * **/
    public Supplier<T> get(ResourceKey<T> supp){
        return containsKey(supp) ? createMap.get(supp) : null;
    }

    /**
     * Finds an object using the specified ResourceLocation
     *
     * @param loc the resource to search for
     *
     * @return A supplier of the object if it is found
     * **/
    public Supplier<T> get(ResourceLocation loc){
        return containsKey(loc) ? locationMap.get(loc) : null;
    }

    /**
     * Finds an object using the specified string
     *
     * @param loc the string to search for
     *
     * @return A supplier of the object if it is found
     * **/
    public Supplier<T> get(String loc){
        return get(new ResourceLocation(loc));
    }
}
