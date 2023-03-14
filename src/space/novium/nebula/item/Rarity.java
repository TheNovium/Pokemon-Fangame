package space.novium.nebula.item;

import java.awt.*;

public enum Rarity {
    COMMON(Color.WHITE, "Common"),
    UNCOMMON(Color.BLUE, "Uncommon"),
    RARE(Color.CYAN, "Rare"),
    EPIC(Color.GREEN, "Epic"),
    LEGENDARY(Color.PINK, "Legendary"),
    SINGULARITY(new Color(73, 23, 138), "Singularity");
    private final Color color;
    private final String desc;

    Rarity(Color color, String desc){
        this.color = color;
        this.desc = desc;
    }

    public Color getColor() {
        return color;
    }

    public String getDesc() {
        return desc;
    }
}
