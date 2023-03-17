package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.RegistryObject;

public interface IEventRegister<V> {
    void registerAll();

    boolean register(RegistryObject<V> value);

    EventType event();
}
