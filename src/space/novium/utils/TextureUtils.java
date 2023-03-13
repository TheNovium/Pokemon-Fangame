package space.novium.utils;

import space.novium.gui.Window;

public class TextureUtils {
    public static final byte[] INDICES = {0, 1, 2, 2, 3, 0};

    public static float[] getDrawLocation(float x, float y, float z, float w, float h, boolean squared){
        if(squared){
            float rat = Window.get().getAspectRatio();
            h *= rat;
            y -= h / 2;
        }
        return new float[]{
                x, y, z,
                x, y + h, z,
                x + w, y + h, z,
                x + w, y, z
        };
    }

    public float[] getDrawLocation(float x, float y, float z, float w, float h){
        return getDrawLocation(x, y, z, w, h, false);
    }

    public float[] getTextureDraw(float x, float y, float w, float h){
        return new float[] {
                x, y + w,
                x, y,
                x + w, y,
                x + w, y + h
        };
    }
}
