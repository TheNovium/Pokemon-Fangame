package space.novium.nebula.graphics.renderer;

import space.novium.utils.math.Vector2f;

public class Transform {
    private Vector2f position;
    private Vector2f scale;
    private int z;

    public Transform(){
        this(new Vector2f());
    }

    public Transform(int z){
        this(new Vector2f(), z);
    }

    public Transform(Vector2f position){
        this(position, new Vector2f(1.0f));
    }

    public Transform(Vector2f position, int z){
        this(position, new Vector2f(1.0f), z);
    }

    public Transform(Vector2f position, Vector2f scale){
        this(position, scale, 1);
    }

    public Transform(Vector2f position, Vector2f scale, int z){
        this.position = position;
        this.scale = scale;
        this.z = z;
    }

    public void move(float dx, float dy){
        position.add(dx, dy);
    }

    public void move(Vector2f df){
        position.add(df);
    }

    public void scale(float dx, float dy){
        scale.mult(dx, dy);
    }

    public void scale(Vector2f ds){
        scale.mult(ds);
    }

    public void scale(float ds){
        scale.mult(ds, ds);
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getZ(){
        return z;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }
}
