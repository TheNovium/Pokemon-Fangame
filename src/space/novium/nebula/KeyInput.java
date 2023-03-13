package space.novium.nebula;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class KeyInput {
    private static KeyInput instance;
    private static Map<Integer, Boolean> keyPressed = new HashMap<>();

    private KeyInput(){}

    public static KeyInput get(){
        if(instance == null){
            instance = new KeyInput();
        }
        return instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods){
        if(action == GLFW_PRESS){
            keyPressed.put(key, true);
        } else {
            keyPressed.put(key, false);
        }
    }

    public static boolean isPressed(int keyCode){
        return keyPressed.getOrDefault(keyCode, false);
    }
}
