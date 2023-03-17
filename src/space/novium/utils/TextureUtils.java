package space.novium.utils;

import space.novium.gui.Window;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.math.Matrix4f;
import space.novium.utils.math.Vector4f;

public final class TextureUtils {
    public static final Matrix4f ORTHO_MATRIX = Matrix4f.orthographic(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
    public static final ResourceLocation NO_TEXTURE = new ResourceLocation("no_texture");


    public static final byte[] INDICES = {0, 1, 2, 2, 3, 0};

    public static float[] getDrawLocation(float x, float y, float z, float w, float h, boolean squared){
        if(squared){
            float rat = Window.get().getAspectRatio();
            y *= rat;
            h *= rat;
        }
        return new float[]{
                x, y, z,
                x, y + h, z,
                x + w, y + h, z,
                x + w, y, z
        };
    }

    public static float[] getDrawLocation(float x, float y, float z, float w, float h){
        return getDrawLocation(x, y, z, w, h, false);
    }

    public static float[] getTextureDraw(float x, float y, float w, float h){
        return new float[] {
                x, y + w,
                x, y,
                x + w, y,
                x + w, y + h
        };
    }

    public static float[] getTextureDrawCoordinates(Vector4f vec4){
        return new float[] {
                vec4.getX(), vec4.getY() + vec4.getH(),
                vec4.getX(), vec4.getY(),
                vec4.getX() + vec4.getW(), vec4.getY(),
                vec4.getX() + vec4.getW(), vec4.getY() + vec4.getH()
        };
    }
}
