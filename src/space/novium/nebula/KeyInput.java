package space.novium.nebula;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class KeyInput {
    private static KeyInput instance;
    private static Map<Integer, Boolean> keyPressed = new HashMap<>();
    private static Map<Integer, Boolean> keyHeld = new HashMap<>();

    //Custom defined keys, can reassign them
    public static int SELECT_DOWN = GLFW_KEY_DOWN; //Menu navigation towards -y
    public static int SELECT_UP = GLFW_KEY_UP; //Menu navigation towards +y
    public static int SELECT = GLFW_KEY_ENTER; //Menu select current objet

    //HUD keys
    public static int HUD_LEFT = GLFW_KEY_Z;
    public static int HUD_RIGHT = GLFW_KEY_C;
    public static int HUD_ACTION_LEFT = GLFW_KEY_Q;
    public static int HUD_ACTION_RIGHT = GLFW_KEY_E;
    public static int HUD_SELECT = GLFW_KEY_SPACE;

    //Movement Keys
    public static int MOVE_UP = GLFW_KEY_W;
    public static int MOVE_LEFT = GLFW_KEY_A;
    public static int MOVE_DOWN = GLFW_KEY_S;
    public static int MOVE_RIGHT = GLFW_KEY_D;

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
        if(action == GLFW_PRESS || action == GLFW_REPEAT){
            keyHeld.put(key, true);
        } else {
            keyHeld.put(key, false);
        }
    }

    public static boolean isPressed(int keyCode){
        boolean ret = keyPressed.getOrDefault(keyCode, false);
        keyPressed.put(keyCode, false);
        return ret;
    }

    public static boolean isHeld(int keyCode){
        return keyHeld.getOrDefault(keyCode, false);
    }
}
