package space.novium.nebula.graphics.gui.parts.actions;

import space.novium.level.Scene;
import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.graphics.gui.transitions.ITransition;

import java.util.function.Supplier;

public class FadeToSceneAction implements IButtonAction, Runnable {
    private final ITransition transition;
    private final Supplier<Scene> scene;
    private Thread thread;

    public FadeToSceneAction(ITransition transition, Supplier<Scene> scene){
        this.transition = transition;
        this.scene = scene;
    }

    @Override
    public void act(){
        thread = new Thread(this, "transitionThread");
        thread.start();
    }

    @Override
    public void run() {
        transition.fadeOut();
        try {
            Thread.sleep((long)(transition.getTime() * 1000.0f));
            thread.join();
        } catch(Exception e){
            System.err.println("Failed to sleep thread for transition!");
        }
        Window.setScene(scene);
    }
}
