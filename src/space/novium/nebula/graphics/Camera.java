package space.novium.nebula.graphics;

import space.novium.utils.TextureUtils;
import space.novium.utils.math.Matrix4f;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        projectionMatrix = TextureUtils.ORTHO_MATRIX;
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(position.x, position.y, -1.0f);
        viewMatrix = Matrix4f.identity();
        viewMatrix.translate(cameraFront);
        return viewMatrix;
    }

    public void move(float dx, float dy){
        position.x += dx;
        position.y += dy;
    }
}
