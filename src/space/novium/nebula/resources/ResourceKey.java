package space.novium.nebula.resources;

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


    public static <T> ResourceKey<T> create(ResourceKey<? extends Registry<T>> registry, ResourceLocation loc){
        return create(registry.getLocation(), loc);
    }

    public static <T> ResourceKey<Registry<T>> createRegistryKey(ResourceLocation loc){
        return create(Registry.ROOT_REGISTRY_NAME, loc);
    }

    public static <T> ResourceKey<T> create(ResourceLocation root, ResourceLocation loc){
        String s = (root + ":" + loc).intern();
        return (ResourceKey<T>) VALUES.computeIfAbsent(s, (r) -> {
            return new ResourceKey<T>(root, loc);
        });
    }

    public ResourceLocation getRegistryName(){
        return registryName;
    }

    public ResourceLocation getLocation(){
        return location;
    }

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
