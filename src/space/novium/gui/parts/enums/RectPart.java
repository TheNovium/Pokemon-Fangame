package space.novium.gui.parts.enums;

import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

public class RectPart {
    private SpriteRenderer spr;

    public RectPart(Vector2f loc, Vector2f size, int z){
        spr = new SpriteRenderer(TextureAtlasType.BLANK);
        spr.addDrawLocation(new Vector4f(0, 0, 1, 1));
        GameObject obj = new GameObject("rect:" + loc.x + "," + loc.y + "," + size.x + "," + size.y, new Transform(loc, size, z));
        obj.addComponent(spr);
        Renderer.get().add(obj);
    }

    public void setColor(float r, float g, float b, float a){
        spr.setColor(r, g, b, a);
    }
}
