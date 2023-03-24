package space.novium;

import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.core.annotations.AnnotationHandler;

public class Game {
    public static final String ID = "space";
    private static Window window;

    public static void main(String[] args){
        AnnotationHandler.loadAnnotations();

        window = Window.get();
        window.start();
    }
}
