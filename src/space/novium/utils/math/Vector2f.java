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

    public float getDifference(Vector2f other){
        return (float)Math.sqrt(Math.pow(getX() - other.getX(), 2) + Math.pow(getY() - other.getY(), 2));
    }

    public Vector2f getNormalVector(){
        Vector2f ret = new Vector2f();
        float total = (float)Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
        ret.setX(getX() / total);
        ret.setY(getY() / total);
        return ret;
    }

    @Override
    public String toString() {
        return "Vector2f@" + getX() + "," + getY();
    }
}
