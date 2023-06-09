package space.novium.nebula.graphics.texture;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.BufferUtils;
import space.novium.utils.IOUtils;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    private int width, height;
    private int texture;

    public Texture(ResourceLocation loc){
        texture = processImage(IOUtils.loadImage(loc));
    }

    public Texture(BufferedImage image){
        texture = processImage(image);
    }

    private int processImage(BufferedImage image){
        int[] pixels = null;
        width = image.getWidth();
        height = image.getHeight();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        int[] data = new int[width * height];
        for(int i = 0; i < width * height; i++){
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = pixels[i] & 0xff;
            data[i] = a << 24 | b << 16 | g << 8 | r;
        }
        int ret = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, ret);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
        return ret;
    }

    /**
     * @return The GL location of the texture
     * **/
    public int getTexture(){
        return texture;
    }

    /**
     * @return the width in pixels of the texture
     * **/
    public int getWidth() {
        return width;
    }

    /**
     * @return the height in pixels of the texture
     * **/
    public int getHeight() {
        return height;
    }

    /**
     * Binds the texture for use in the GL program
     * **/
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    /**
     * Unbinds texture from the GL program
     * **/
    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
