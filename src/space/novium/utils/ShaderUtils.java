package space.novium.utils;

import space.novium.nebula.core.resources.ResourceLocation;

import static org.lwjgl.opengl.GL20C.*;

public class ShaderUtils {
    private ShaderUtils(){}

    public static int load(ResourceLocation vertPath, ResourceLocation fragPath){
        String vert = IOUtils.loadAsString(vertPath);
        String frag = IOUtils.loadAsString(fragPath);
        return create(vert, frag);
    }

    public static int create(String vert, String frag){
        int program = glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);
        glCompileShader(vertID);
        if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile vertex shader!");
            return -1;
        }
        glCompileShader(fragID);
        if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile fragment shader!");
            return -1;
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }
}
