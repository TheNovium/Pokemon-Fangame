package space.novium.level;

import space.novium.nebula.graphics.gui.parts.ButtonPart;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.transitions.FadeTransition;
import space.novium.nebula.graphics.gui.transitions.ITransition;
import space.novium.nebula.graphics.gui.transitions.enums.TransitionType;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;

public class Homepage extends Scene{
    private ITransition fade;
    private ButtonPart part;

    public Homepage(){
        camera = new Camera();
    }

    @Override
    public void init(){
        new ImagePart(GameResourceLocations.PLANET);
        fade = new FadeTransition(1.5f, TransitionType.QUICK_FADE);
        fade.fadeIn();
        part = new ButtonPart()
                .withText("Test");
    }

    @Override
    public void update(float dt){
        fade.tick(dt);
    }

    @Override
    public void render(){

    }
}
