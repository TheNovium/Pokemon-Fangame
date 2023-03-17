package space.novium.level;

import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.VertexArray;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class IntroScene extends Scene {
    private List<SpriteRenderer> gameObjects;
    private VertexArray vertexArray;
    private float temp = 0.0f;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.camera.adjustProjection();
        this.gameObjects = new ArrayList<>();
    }

    public void init(){
        GameObject gameObject = new GameObject("Test", new Transform(new Vector2f(0, 0), new Vector2f(0.4f), 1));
        SpriteRenderer spriteRenderer = new SpriteRenderer(TextureAtlasType.NONE);
        spriteRenderer.addDrawLocation(new Vector4f(0, 0, 1, 1));
        gameObjects.add(spriteRenderer);
        gameObject.addComponent(spriteRenderer);
        Renderer.get().add(gameObject);
    }

    @Override
    public void update(float dt) {
        temp += dt;
        for(SpriteRenderer spr : gameObjects){
            spr.getGameObject().getTransform().setScale((float) Math.sin(temp));
            spr.setDirty();
        }
    }

    @Override
    public void render() {



        Renderer.get().render();
    }
}
