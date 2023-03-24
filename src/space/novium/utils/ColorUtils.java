package space.novium.utils;

import space.novium.utils.math.Vector4f;

import java.awt.*;

public class ColorUtils {
    public static final Vector4f WHITE = new Vector4f(1.0f);
    public static final Vector4f BLACK = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    public static final Vector4f CLEAR = new Vector4f(0.0f);

    public static Vector4f processColor(Color color){
        Vector4f ret = new Vector4f();
        ret.setX(processRGB(color.getRed()));
        ret.setY(processRGB(color.getGreen()));
        ret.setW(processRGB(color.getBlue()));
        ret.setH(processRGB(color.getAlpha()));
        return ret;
    }

    private static float processRGB(int clr){
        return ((float) clr) / 255.0f;
    }
}
