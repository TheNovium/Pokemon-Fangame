package space.novium;

import space.novium.gui.Window;

public class Game {
    public static final String ID = "space";
    private static Window window;

    public static void main(String[] args){
        window = Window.get();
        window.start();
    }
}
