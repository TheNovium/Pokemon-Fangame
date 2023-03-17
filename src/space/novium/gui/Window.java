package space.novium.gui;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import space.novium.level.IntroScene;
import space.novium.level.Scene;
import space.novium.nebula.KeyInput;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.utils.math.Vector2i;

import java.util.function.Supplier;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window implements Runnable {
    private static Window instance = null;
    private Thread windowThread;

    private Long window;

    private boolean running;
    private double goalFPS;
    private Vector2i windowSize;
    private static Scene currentScene;
    private Renderer renderer;

    private Window(){}

    public static void setScene(Supplier<Scene> newScene){
        currentScene = newScene.get();
        currentScene.init();
    }

    public static Window get(){
        if(instance == null){
            instance = new Window();
        }
        return instance;
    }

    public void start(){
        windowThread = new Thread(this, "renderThread");
        windowThread.start();
    }

    public void run(){
        init();
        double updateRate = 1.0 / 30.0;
        double prev = glfwGetTime();
        double step = 0.0;
        while(running){
            double loopStartTime = glfwGetTime();
            double elapsed = loopStartTime - prev;
            step += elapsed;
            prev = glfwGetTime();

            while(step >= updateRate){
                step -= updateRate;
                update((float)updateRate);
            }
            render();
            sync(prev);
            if(glfwWindowShouldClose(window)){
                running = false;
            }
        }
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public int getWidth(){
        return windowSize.getX();
    }

    public int getHeight(){
        return windowSize.getY();
    }

    public float getAspectRatio(){
        return ((float) getWidth()) / ((float) getHeight());
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        currentScene.render();
        glfwSwapBuffers(window);
    }

    private void update(float dt){
        glfwPollEvents();
        currentScene.update(dt);
    }

    private void sync(double time){
        //TODO sync the threads to allow for faster FPS
    }

    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new RuntimeException("Unable to initialize GLFW!");
        }

        windowSize = new Vector2i(1080, 720);
        goalFPS = 60.0;

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        window = glfwCreateWindow(windowSize.x, windowSize.y, "[Title]", NULL, NULL);

        final PointerBuffer monitors = glfwGetMonitors();
        if(monitors == null || !monitors.hasRemaining()){
            throw new RuntimeException("There are no monitors to draw to!");
        }

        final GLFWVidMode mode = glfwGetVideoMode(monitors.get(0));
        glfwSetWindowPos(window,
                (mode.width() - getWidth()) / 2,
                (mode.height() - getHeight()) / 2);

        glfwMakeContextCurrent(window);

        glfwSetKeyCallback(window, KeyInput::keyCallback);

        glfwShowWindow(window);
        GL.createCapabilities();

        renderer = Renderer.get();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        setScene(IntroScene::new);

        running = true;
    }

    public void setTitle(CharSequence title){
        glfwSetWindowTitle(window, title);
    }

    public Scene getScene(){
        return currentScene;
    }
}
