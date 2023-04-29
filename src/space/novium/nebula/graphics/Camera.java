package space.novium.nebula.graphics;

import space.novium.utils.TextureUtils;
import space.novium.utils.math.Matrix4f;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;
    private Vector2f scale;

    public Camera(){
        this(new Vector2f());
    }

    public Camera(Vector2f position){
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        this.scale = new Vector2f(1.0f);
        adjustProjection();
    }

    /**
     * Resets the projection matrix
     * **/
    public void adjustProjection(){
        projectionMatrix = TextureUtils.ORTHO_MATRIX;
    }

    /**
     * @return The projection matrix
     * **/
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    /**
     * @return the view matrix
     * **/
    public Matrix4f getViewMatrix(){
        Vector3f cameraFront = new Vector3f(position.x, position.y, 0.0f);
        viewMatrix = Matrix4f.identity();
        viewMatrix.translate(cameraFront);
        viewMatrix.scale(scale.x, scale.y);
        return viewMatrix;
    }

    /**
     * @return The static orthographic matrix
     * **/
    public Matrix4f getStaticViewMatrix(){
        return Matrix4f.identity();
    }

    /**
     * Moves the camera
     *
     * @param dx x axis camera movement
     * @param dy y axis camera movement
     * **/
    public void move(float dx, float dy){
        position.x += dx;
        position.y += dy;
    }
    
    /**
     * Moves the camera
     *
     * @param vec moves the camera with the x and y information stored in the vector
     * **/
    public void move(Vector2f vec){
        move(vec.getX(), vec.getY());
    }

    /**
     * Sets the position of the camera
     *
     * @param x the x position
     * @param y the y position
     * **/
    public void setPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    /**
     * Sets the position of the camera from a vector2f
     *
     * @param vec The vector with the x, y information
     * **/
    public void setPosition(Vector2f vec){
        this.position = vec;
    }

    /**
     * @return A vector2f of the position
     * **/
    public Vector2f getPosition(){
        return position;
    }

    /**
     * Scales the camera by a set amount
     *
     * @param ds The amount to zoom
     * **/
    public void scale(float ds){
        scale.x += ds;
        scale.y += ds;
    }

    /**
     * Scales the camera by a set amount
     *
     * @param dx Horizontal zoom
     * @param dy Vertical zoom
     * **/
    public void scale(float dx, float dy){
        scale.x += dx;
        scale.y += dy;
    }

    /**
     * Sets the scale of a camera
     *
     * @param s The scale
     * **/
    public void setScale(float s){
        scale.x = scale.y = s;
    }

    /**
     * Sets the scale of the camera
     *
     * @param sx The horizontal scale
     * @param sy The vertical scale
     * **/
    public void setScale(float sx, float sy){
        scale.x = sx;
        scale.y = sy;
    }
}
