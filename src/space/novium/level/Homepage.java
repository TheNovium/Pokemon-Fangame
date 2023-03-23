package space.novium.level;

import space.novium.gui.parts.ImagePart;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.renderer.Renderer;

public class Homepage extends Scene{
    public Homepage(){
        camera = new Camera();
    }

    @Override
    public void init(){
        Renderer.get().clearRenderer();
        new ImagePart(GameResourceLocations.PLANET);
    }

    @Override
    public void update(float dt){}

    @Override
    public void render(){}
}
