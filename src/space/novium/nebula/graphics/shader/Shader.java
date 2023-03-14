package space.novium.nebula.graphics.shader;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.ShaderUtils;
import space.novium.utils.math.Matrix4f;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class Shader {
    public static final int VERTEX_ATTRIB = 0;
    public static final int TEX_COORD_ATTRIB = 1;
    private final int ID;

    public static Shader DEFAULT;
    public static Shader TEST;

    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(ResourceLocation vertex, ResourceLocation fragment){
        ID = ShaderUtils.load(vertex, fragment);
    }

    public static void loadShaders(){
        DEFAULT = new Shader(DEFAULT_VERTEX, DEFAULT_FRAGMENT);
        TEST = new Shader(TEST_VERTEX, TEST_FRAGMENT);
    }

    public int getUniform(String name){
        if(locationCache.containsKey(name)){
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(ID, name);
        if(result == -1){
            System.err.println("Unable to locate shader uniform with name " + name);
        } else {
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniform1i(String name, int value){
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value){
        glUniform1f(getUniform(name), value);
    }

    public void setUniform3f(String name, float x, float y, float z){
        glUniform3f(getUniform(name), x, y, z);
    }

    public void setUniformMat4(String name, Matrix4f matrix){
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable(){
        glUseProgram(ID);
    }

    public void disable(){
        glUseProgram(0);
    }

    public static final ResourceLocation DEFAULT_VERTEX;
    public static final ResourceLocation DEFAULT_FRAGMENT;
    public static final ResourceLocation TEST_VERTEX;
    public static final ResourceLocation TEST_FRAGMENT;

    static {
        DEFAULT_VERTEX = new ResourceLocation("shaders/default.vert");
        DEFAULT_FRAGMENT = new ResourceLocation("shaders/default.frag");
        TEST_VERTEX = new ResourceLocation("shaders/test.vert");
        TEST_FRAGMENT = new ResourceLocation("shaders/test.frag");
    }
}
