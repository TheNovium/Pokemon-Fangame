package space.novium.utils.math;

public class Vector2i {
    public int x;
    public int y;

    public Vector2i(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vector2i vec){
        this.x += vec.getX();
        this.y += vec.getY();
    }
}
