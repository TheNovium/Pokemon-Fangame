package space.novium.utils.data;

import java.util.LinkedList;
import java.util.List;

public class Table<T> {
    private final List<T>[] table;
    private final int width;
    private final int height;

    public Table(int width, int height){
        this.width = width;
        this.height = height;
        this.table = new List[width * height];
    }

    public void add(T value, int x, int y){
        int loc = getLocation(x, y);
        if(table[loc] == null){
            table[loc] = new LinkedList<>();
        }
        table[loc].add(value);
    }

    public List<T> get(int x, int y){
        List<T> ret = table[getLocation(x, y)];
        if(ret == null){
            return new LinkedList<>();
        }
        return ret;
    }

    private int getLocation(int x, int y){
        int loc = x * width + y;
        return loc > 0 ? Math.min(loc, table.length - 1) : 0;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
