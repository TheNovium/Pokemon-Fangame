package space.novium.level;

import space.novium.nebula.graphics.Camera;

public abstract class Scene {
    protected Camera camera;

    public Scene(){}

    /**
     * Initialized the scene, including assets
     * **/
    public void init(){}

    /**
     * This is where the logic for the scene should be
     *
     * @param dt seconds since last update
     * **/
    public abstract void update(float dt);


    /**
     * Render logic
     * **/
    public abstract void render();

    /**
     * @return the current camera for the scene
     * **/
    public Camera getCamera(){
        return camera;
    }

    /**
     * Removes the assets taking up memory
     * **/
    public abstract void cleanup();
}
