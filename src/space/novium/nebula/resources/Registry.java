package space.novium.nebula.resources;

import space.novium.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Registry<T> {
    private static final Map<ResourceLocation, Supplier<?>> LOADERS = new HashMap<>();
    private static final Map<ResourceLocation, Registry<?>> REGISTRIES = new HashMap<>();
    public static final ResourceLocation ROOT_REGISTRY_NAME = new ResourceLocation("root");

    private static <T> ResourceKey<Registry<T>> createRegistryKey(String key){
        return ResourceKey.createRegistryKey(new ResourceLocation(key));

    }

    public static Registry<?> getRegistry(ResourceLocation loc){
        return REGISTRIES.getOrDefault(loc, null);
    }

    private final ResourceKey<? extends Registry<T>> key;
    private final Map<ResourceKey<?>, Supplier<T>> createMap;
    private final Map<ResourceLocation, Supplier<T>> locationMap;

    public Registry(ResourceKey<Registry<T>> key){
        this.key = key;
        this.createMap = new HashMap<>();
        this.locationMap = new HashMap<>();
        REGISTRIES.put(key.getLocation(), this);
    }

    public ResourceKey<? extends Registry<T>> getKey(){
        return key;
    }

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

    public T getValue(ResourceLocation loc){
        Supplier<T> temp = locationMap.getOrDefault(loc, null);
        return temp != null ? temp.get() : null;
    }

    public boolean containsKey(ResourceKey<T> cont){
        return createMap.containsKey(cont);
    }

    public boolean containsKey(ResourceLocation cont){
        return locationMap.containsKey(cont);
    }

    public Supplier<T> get(ResourceKey<T> supp){
        return containsKey(supp) ? createMap.get(supp) : null;
    }

    public Supplier<T> get(ResourceLocation loc){
        return containsKey(loc) ? locationMap.get(loc) : null;
    }
}
