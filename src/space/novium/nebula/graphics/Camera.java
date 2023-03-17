package space.novium.nebula.graphics;

import space.novium.utils.TextureUtils;
import space.novium.utils.math.Matrix4f;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;
    private Vector2f scale;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        this.scale = new Vector2f(1.0f);
        adjustProjection();
    }

    public void adjustProjection(){
        projectionMatrix = TextureUtils.ORTHO_MATRIX;
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(position.x, position.y, -1.0f);
        viewMatrix = Matrix4f.identity();
        viewMatrix.translate(cameraFront);
        viewMatrix.scale(scale.x, scale.y);
        return viewMatrix;
    }

    public void move(float dx, float dy){
        position.x += dx;
        position.y += dy;
    }

    public void setPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    public void scale(float ds){
        scale.x += ds;
        scale.y += ds;
    }

    public void scale(float dx, float dy){
        scale.x += dx;
        scale.y += dy;
    }

    public void setScale(float s){
        scale.x = scale.y = s;
    }

    public void setScale(float sx, float sy){
        scale.x = sx;
        scale.y = sy;
    }
}
