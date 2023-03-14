package space.novium.nebula.item;

import java.util.ArrayList;
import java.util.List;

public class ItemTabLocation {
    public static List<ItemTabLocation> TABS = new ArrayList<>();
    public static final ItemTabLocation HEAL_ITEMS = new ItemTabLocation("Healing Items");
    public static final ItemTabLocation POKEBALLS = new ItemTabLocation("Pokeballs");

    private final int id;
    private final String desc;

    public ItemTabLocation(String desc){
        this.id = TABS.size();
        this.desc = desc;
        TABS.add(this);
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
