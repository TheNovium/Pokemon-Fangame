package space.novium.nebula.graphics.gui.parts.actions;

import space.novium.level.Scene;
import space.novium.nebula.graphics.gui.Window;

import java.util.function.Supplier;

public class SetSceneAction implements IButtonAction {
    private final Supplier<Scene> scene;

    public SetSceneAction(Supplier<Scene> scene){
        this.scene = scene;
    }

    @Override
    public void act() {
        Window.setScene(scene);
    }
}
