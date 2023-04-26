package space.novium.nebula.core.resources;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public class ResourceKey<T> implements Comparable<ResourceKey<T>> {
    private static final Map<String, ResourceKey<?>> VALUES = Collections.synchronizedMap(new IdentityHashMap<>());
    private final ResourceLocation registryName;
    private final ResourceLocation location;

    private ResourceKey(ResourceLocation registryName, ResourceLocation loc){
        this.registryName = registryName;
        this.location = loc;
    }

    /**
     * Creates a new ResourceKey
     *
     * @param registry The registry associated with the key
     * @param loc The location of the key
     * @param <T> The registry type
     *
     * @return a new resource key with the specified information
     * **/
    public static <T> ResourceKey<T> create(ResourceKey<? extends Registry<T>> registry, ResourceLocation loc){
        return create(registry.getLocation(), loc);
    }

    /**
     * Creates a new ResourceKey
     *
     * @param loc A resource location of the key
     *
     * @return A resource key associated with the register
     * **/
    public static <T> ResourceKey<Registry<T>> createRegistryKey(ResourceLocation loc){
        return create(Registry.ROOT_REGISTRY_NAME, loc);
    }

    /**
     * Creates a new ResourceKey
     *
     * @param root The mod ID
     * @param loc The name of the key
     *
     * @return A resource key associated with a mod
     * **/
    public static <T> ResourceKey<T> create(ResourceLocation root, ResourceLocation loc){
        String s = (root + ":" + loc).intern();
        return (ResourceKey<T>) VALUES.computeIfAbsent(s, (r) -> {
            return new ResourceKey<T>(root, loc);
        });
    }

    /**
     * @return the ResourceLocation of the registry the key is associated with
     * **/
    public ResourceLocation getRegistryName(){
        return registryName;
    }

    /**
     * @return the ResourceLocation that the key is associated with
     * **/
    public ResourceLocation getLocation(){
        return location;
    }

    /**
     * @return true if the objects are equivalent
     * **/
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        return registryName.equals(((ResourceKey<?>) obj).getRegistryName()) && location.equals(((ResourceKey<?>) obj).getLocation());
    }

    @Override
    public int compareTo(ResourceKey<T> o) {
        int temp = getRegistryName().compareTo(o.getRegistryName());
        if(temp == 0){
            temp = getLocation().compareTo(o.getLocation());
        }
        return temp;
    }
}
