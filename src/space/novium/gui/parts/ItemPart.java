package space.novium.gui.parts;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.math.Vector2f;

public class ItemPart {
    private ImagePart itemImage;

    public ItemPart(ResourceLocation loc, Vector2f drawLocation, float size, int z){
        this.itemImage = new ImagePart(loc, drawLocation, size, z);
    }
}
