package space.novium.gui.parts;

import space.novium.gui.Window;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.math.Vector2f;

public class ItemPart {
    private SpriteRenderer spriteRenderer;
    private GameObject gameObject;

    public ItemPart(ResourceLocation loc, Vector2f drawLocation, float size){
        spriteRenderer = Renderer.get().getHandler().getRendererForResourceLocation(loc);
        gameObject = new GameObject(loc.toString(), new Transform(drawLocation, new Vector2f(size, size * Window.get().getAspectRatio())));
        gameObject.addComponent(spriteRenderer);
        Renderer.get().add(gameObject);
    }
}
