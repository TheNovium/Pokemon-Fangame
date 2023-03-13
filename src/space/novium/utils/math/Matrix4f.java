package space.novium.utils.math;

import space.novium.utils.BufferUtils;

import java.nio.FloatBuffer;

public class Matrix4f {
    public static final int SIZE = 4;
    public float[] elements = new float[SIZE * SIZE];

    public Matrix4f(){}

    public static Matrix4f identity(){
        Matrix4f result = new Matrix4f();
        for(int i = 0; i < SIZE; i++){
            result.elements[i + i * SIZE] = 1.0f;
        }
        return result;
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
        Matrix4f result = identity();
        result.elements[0] = 2.0f / (right - left);
        result.elements[5] = 2.0f / (top - bottom);
        result.elements[10] = 2.0f / (near - far);

        result.elements[12] = (left + right) / (left - right);
        result.elements[13] = (bottom + top) / (bottom - top);
        result.elements[14] = (far + near) / (far - near);

        return result;
    }

    public Matrix4f translate(Vector3f vec){
        elements[12] *= vec.x;
        elements[13] *= vec.y;
        elements[14] *= vec.z;
        return this;
    }

    public Matrix4f rotate(float angle){
        float r = (float) Math.toRadians(angle);
        float sin = (float) Math.sin(r);
        float cos = (float) Math.cos(r);

        elements[0] = cos;
        elements[1] = sin;
        elements[4] = -sin;
        elements[5] = cos;
        return this;
    }

    public Matrix4f scale(float s){
        return scale(s, s);
    }

    public Matrix4f scale(float x, float y){
        elements[0] = x;
        elements[5] = y;
        return this;
    }

    public Matrix4f multiply(Matrix4f matrix){
        Matrix4f result = new Matrix4f();
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                float sum = 0.0f;
                for(int i = 0; i < SIZE; i++){
                    sum += this.elements[x + i * SIZE] * matrix.elements[i + y * SIZE];
                }
                result.elements[x + y * SIZE] = sum;
            }
        }
        return result;
    }

    public FloatBuffer toFloatBuffer(){
        return BufferUtils.createFloatBuffer(elements);
    }
}
