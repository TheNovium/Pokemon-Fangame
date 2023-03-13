package space.novium.nebula.resources;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

public class RegistryObject<T> implements Supplier<T> {
    private final ResourceLocation name;
    private final ResourceLocation register;
    private ResourceKey<?> key;
    private final String mod;
    private T value;

    private RegistryObject(final ResourceLocation name, final ResourceLocation register, final String mod){
        this.name = name;
        this.register = register;
        this.mod = mod;
        this.key = ResourceKey.create(register, name);
        updateReference(register);
    }

    public void updateRegisterReference(){
        updateReference(register);
    }

    private void updateReference(ResourceLocation r){
        if(name == null) return;
        if(r == null) return;
        Registry<?> reg = Registry.getRegistry(r);
        if(reg == null) return;
        if(reg.containsKey(this.name)){
            this.key = ResourceKey.create(reg.getKey(), this.name);
            this.value = (T) reg.getValue(name);
        }
    }

    public static <T, U extends T> RegistryObject<U> create(final ResourceLocation name, final ResourceKey<? extends Registry<T>> registry, final String mod){
        return new RegistryObject<>(name, registry.getLocation(), mod);
    }

    public ResourceKey<?> getKey() {
        return key;
    }

    @Nonnull
    @Override
    public T get(){
        T temp = value;
        Objects.requireNonNull(temp, () -> "Registry object not present: " + name);
        return temp;
    }
}
