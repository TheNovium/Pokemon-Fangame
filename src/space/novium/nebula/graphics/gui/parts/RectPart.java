package space.novium.nebula.graphics.gui.parts;

import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

public class RectPart {
    private SpriteRenderer spr;
    private GameObject obj;

    public RectPart(Vector2f loc, Vector2f size, int z){
        spr = new SpriteRenderer(TextureAtlasType.BLANK);
        spr.addDrawLocation(new Vector4f(0, 0, 1, 1));
        obj = new GameObject("rect:" + loc.x + "," + loc.y + "," + size.x + "," + size.y, new Transform(loc, size, z));
        obj.addComponent(spr);
        Renderer.get().add(obj);
    }

    public RectPart setColor(float r, float g, float b, float a){
        spr.setColor(r, g, b, a);
        return this;
    }

    public RectPart setColor(Vector4f clr){
        return setColor(clr.getX(), clr.getY(), clr.getW(), clr.getH());
    }

    public RectPart setAlpha(float a){
        spr.setColor(spr.getColor().setH(a));
        return this;
    }

    public RectPart translate(float dx, float dy){
        obj.translate(dx, dy);
        return this;
    }

    public RectPart setPos(float x, float y){
        obj.setPosition(x, y);
        return this;
    }
}
