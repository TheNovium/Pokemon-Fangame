package space.novium.level;

import space.novium.nebula.graphics.Camera;

public abstract class Scene {
    protected Camera camera;

    public Scene(){}

    public void init(){}

    public abstract void update(float dt);

    public abstract void render();
}
