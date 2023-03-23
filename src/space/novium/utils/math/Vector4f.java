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

    public Vector4f setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public Vector4f setY(float y) {
        this.y = y;
        return this;
    }

    public float getW() {
        return w;
    }

    public Vector4f setW(float w) {
        this.w = w;
        return this;
    }

    public float getH() {
        return h;
    }

    public Vector4f setH(float h) {
        this.h = h;
        return this;
    }

    @Override
    public String toString() {
        return "Vector4f: (" + x + ", " + y + ", " + w + ", " + h + ")";
    }
}
