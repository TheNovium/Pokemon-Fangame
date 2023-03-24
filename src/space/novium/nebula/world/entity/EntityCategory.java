package space.novium.nebula.world.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum EntityCategory {
    PASSIVE_POKEMON("pokemon", true, false),
    HOSTILE_POKEMON("hostile_pokemon", false, false),
    PERSISTENT_POKEMON("persistent_pokemon", true, true),
    BOSS_POKEMON("boss_pokemon", false, true),
    NPC("NPC", true, true);

    private static final Map<String, EntityCategory> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(EntityCategory::getName, (name) -> {return name;}));

    private final String name;
    private final boolean friendly;
    private final boolean persistent;

    EntityCategory(String name, boolean isFriendly, boolean isPersistent){
        this.name = name;
        this.friendly = isFriendly;
        this.persistent = isPersistent;
    }

    public String getName() {
        return name;
    }

    public static EntityCategory byName(String name){
        return BY_NAME.get(name);
    }

    public boolean isFriendly() {
        return friendly;
    }

    public boolean isPersistent() {
        return persistent;
    }
}
