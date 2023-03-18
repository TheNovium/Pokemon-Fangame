package space.novium.utils.math;

public class Vector4f {
    private float x, y, w, h;

    public Vector4f(){
        this(0);
    }

    public Vector4f(float d){
        this(d, d, d, d);
    }
    public Vector4f(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "Vector4f: (" + x + ", " + y + ", " + w + ", " + h + ")";
    }
}
