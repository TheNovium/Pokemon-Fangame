package space.novium.nebula.graphics.renderer;

import space.novium.utils.math.Vector2f;

public class Transform {
    private Vector2f position;
    private Vector2f scale;

    public Transform(){
        this(new Vector2f());
    }

    public Transform(Vector2f position){
        this(position, new Vector2f(1.0f));
    }

    public Transform(Vector2f position, Vector2f scale){
        this.position = position;
        this.scale = scale;
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

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }
}
