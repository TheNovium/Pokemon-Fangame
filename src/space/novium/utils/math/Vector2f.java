package space.novium.utils.math;

public class Vector2f {
    public float x, y;

    public Vector2f(){
        this(0);
    }

    public Vector2f(float d){
        this(d, d);
    }

    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
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

    public void add(Vector2f add){
        add(add.x, add.y);
    }

    public void add(float dx, float dy){
        this.x += dx;
        this.y += dy;
    }

    public void mult(Vector2f mult){
        mult(mult.x, mult.y);
    }

    public void mult(float dx, float dy){
        this.x *= dx;
        this.y *= dy;
    }
}
