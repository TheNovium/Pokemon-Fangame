package space.novium.level;

import space.novium.nebula.core.GameObject;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.VertexArray;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class IntroScene extends Scene {
    private List<GameObject> gameObjects;
    private VertexArray vertexArray;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.gameObjects = new ArrayList<>();
    }

    public void init(){
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
        for(GameObject go : gameObjects){
            go.tick();
        }


        Renderer.get().render();
    }
}
