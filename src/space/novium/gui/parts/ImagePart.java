package space.novium.gui.parts;

import space.novium.gui.Window;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.math.Vector2f;

public class ImagePart {
    private GameObject gameObject;
    private SpriteRenderer spr;

    public ImagePart(ResourceLocation loc){
        this(loc, new Vector2f(-1.0f, -1.0f), new Vector2f(2.0f, 2.0f), Renderer.BACKGROUND_Z);
    }

    public ImagePart(ResourceLocation loc, Vector2f drawLocation, float size, int z){
        this(loc, drawLocation, new Vector2f(size, size * Window.get().getAspectRatio()), z);
    }

    public ImagePart(ResourceLocation loc, Vector2f drawLocation, Vector2f size, int z){
        spr = Renderer.get().getHandler().getRendererForResourceLocation(loc);
        gameObject = new GameObject(loc.toString(), new Transform(drawLocation, size, z));
        gameObject.addComponent(spr);
        Renderer.get().add(gameObject);
    }

    public void setAlpha(float a){
        spr.setColor(spr.getColor().setH(a));
    }

    public void setColor(float r, float g, float b, float a){
        spr.setColor(r, g, b, a);
    }
}
