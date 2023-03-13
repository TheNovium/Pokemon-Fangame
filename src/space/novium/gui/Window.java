package space.novium.gui;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import space.novium.utils.math.Vector2i;

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

    private Window(){}

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
                update();
            }
            render();
            sync(prev);
            if(glfwWindowShouldClose(window)){
                running = false;
            }
        }
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public int getWidth(){
        return windowSize.getX();
    }

    public int getHeight(){
        return windowSize.getY();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

    private void update(){
        glfwPollEvents();
        //TODO update the game logic
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

        //glfwSetKeyCallback(window, new KeyInput());

        glfwShowWindow(window);
        GL.createCapabilities();

        //TODO init and enable renderer

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        //TODO load shaders

        //TODO load texture atlas and initialize atlases

        running = true;
    }

    public void setTitle(CharSequence title){
        glfwSetWindowTitle(window, title);
    }
}