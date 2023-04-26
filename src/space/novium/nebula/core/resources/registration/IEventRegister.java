package space.novium.nebula.core.resources.registration;

import space.novium.nebula.core.event.enums.EventType;
import space.novium.nebula.core.resources.RegistryObject;

public interface IEventRegister<V> {
    /**
     * Registers all Objects of type V to the register
     * **/
    void registerAll();

    /**
     * Registers an individual Object of type V to the register
     * **/
    boolean register(RegistryObject<V> value);

    /**
     * The event the register is associated with
     * **/
    EventType event();
}
