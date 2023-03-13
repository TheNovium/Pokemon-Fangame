package space.novium.nebula.resources;

import space.novium.Game;
import space.novium.utils.StringUtils;

public class ResourceLocation implements Comparable<ResourceLocation> {
    public static final char NAMESPACE_SEPARATOR = ':';
    public static final String DEFAULT_NAMESPACE = Game.ID;
    protected final String namespace;
    protected final String path;

    protected ResourceLocation(String[] locations){
        this.namespace = StringUtils.isEmpty(locations[0]) ? DEFAULT_NAMESPACE : locations[0];
        this.path = locations[1];
        if(!isValidNamespace(namespace)){
            System.err.println("Unable to parse namespace " + namespace + NAMESPACE_SEPARATOR + path);
        } else if (!isValidPath(path)){
            System.err.println("Unable to parse path " + namespace + NAMESPACE_SEPARATOR + path);
        }
    }

    public boolean isValidNamespace(String space){
        for(int i = 0; i < space.length(); i ++){
            if(!isValidNamespaceChar(space.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNamespaceChar(char c){
        return c == '_' || c == '-' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.';
    }

    public boolean isValidPath(String p){
        for(int i = 0; i < p.length(); i++){
            if(!isValidPathChar(p.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPathChar(char c){
        return c == '_' || c == '-' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.' || c == '/';
    }

    protected static String[] decompose(String s, char c){
        String[] temp = new String[]{DEFAULT_NAMESPACE, s};
        int i = s.indexOf(c);
        if(i > 0){
            temp[1] = s.substring(i + 1);
            if(i > 1){
                temp[0] = s.substring(0, i);
            }
        }
        return temp;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    public ResourceLocation(String loc){
        this(decompose(loc, NAMESPACE_SEPARATOR));
    }

    public ResourceLocation(String name, String part){
        this(new String[]{name, part});
    }

    @Override
    public int compareTo(ResourceLocation o) {
        if(this.namespace.equals(o.getNamespace())){
            if(this.path.equals(o.getPath())){
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return getNamespace() + NAMESPACE_SEPARATOR + getPath();
    }
}
