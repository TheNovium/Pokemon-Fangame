package space.novium.utils.math;

public class IntRange {
    private int min;
    private int max;

    public IntRange(int min, int max){
        this.min = min;
        this.max = max;
    }

    public boolean inRange(int test){
        return min <= test && max >= test;
    }
}
