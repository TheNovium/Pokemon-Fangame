package space.novium.level;

import space.novium.nebula.graphics.gui.Theme;
import space.novium.nebula.graphics.gui.parts.ButtonPart;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.parts.VerticalMenu;
import space.novium.nebula.graphics.gui.parts.actions.FadeToSceneAction;
import space.novium.nebula.graphics.gui.parts.actions.SetSceneAction;
import space.novium.nebula.graphics.gui.parts.interfaces.IMenu;
import space.novium.nebula.graphics.gui.transitions.FadeTransition;
import space.novium.nebula.graphics.gui.transitions.ITransition;
import space.novium.nebula.graphics.gui.transitions.enums.TransitionType;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.math.Vector2f;

import java.awt.print.Pageable;

public class Homepage extends Scene{
    private ITransition fade;
    private IMenu menu;

    public Homepage(){
        camera = new Camera();
    }

    @Override
    public void init(){
        Theme theme = Theme.get();
        new ImagePart(GameResourceLocations.PLANET);
        menu = new VerticalMenu(
            new Transform(
                    new Vector2f(0.4f, -0.4f),
                    Renderer.GUI_Z
            )
        ).add(
                new ButtonPart()
                        .onClick(
                                () -> new SetSceneAction(Homepage::new)
                        ).withText("Reload page")
                        .withFont(theme.getSecondaryFont().get())
        ).add(
                new ButtonPart()
                        .onClick(
                                () -> new SetSceneAction(IntroScene::new)
                        ).withText("Go to the intro")
                        .withFont(theme.getSecondaryFont().get())
        ).add(
                new ButtonPart()
                        .onClick(
                                () -> new FadeToSceneAction(fade, FunctionTestPage::new)
                        ).withText("Test Page")
                        .withFont(theme.getSecondaryFont().get())
        ).withSelectImage(GameResourceLocations.LIGHT_GREATER_ARROW);
        menu.enable();
        fade = new FadeTransition(1.5f, TransitionType.QUICK_FADE);
        fade.fadeIn();
    }

    @Override
    public void update(float dt){
        fade.tick(dt);
        menu.tick();
    }

    @Override
    public void render(){

    }
}
