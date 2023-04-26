package space.novium.nebula.core.resources;

import space.novium.Game;
import space.novium.utils.StringUtils;

import java.util.Objects;

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

    /**
     * Checks if the provided string is a valid namespace
     *
     * @param space The namespace to check
     *
     * @return true if the namespace is valid
     * **/
    public boolean isValidNamespace(String space){
        for(int i = 0; i < space.length(); i ++){
            if(!isValidNamespaceChar(space.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for valid characters in the namespace
     *
     * @param c The character to check
     *
     * @return true if the character is valid
     * **/
    public static boolean isValidNamespaceChar(char c){
        return c == '_' || c == '-' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.';
    }

    /**
     * Checks if the provided string is a valid path
     *
     * @param p The path to check
     *
     * @return true if the path is valid
     * **/
    public boolean isValidPath(String p){
        for(int i = 0; i < p.length(); i++){
            if(!isValidPathChar(p.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for valid characters in the path
     *
     * @param c The character to check
     *
     * @return true if the character is valid
     * **/
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

    /**
     * @return the namespace of the location
     * **/
    public String getNamespace() {
        return namespace;
    }

    /**
     * @return the path of the location
     * **/
    public String getPath() {
        return path;
    }

    /**
     * Creates a ResourceLocation based off a string
     *
     * @param loc The string with the associated data
     * **/
    public ResourceLocation(String loc){
        this(decompose(loc, NAMESPACE_SEPARATOR));
    }

    /**
     * Creates a ResourceLocation
     *
     * @param name The mod ID that the location is associated with
     * @param part The name of the object the location is associated with
     * **/
    public ResourceLocation(String name, String part){
        this(new String[]{name, part});
    }

    /**
     * Compares two ResourceLocations
     *
     * @param o The location to compare
     *
     * @return 0 if they are the same, -1 if the namespace is different, 1 if the namespace is the same but the path is different
     * **/
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
    public boolean equals(Object obj) {
        if(obj instanceof ResourceLocation loc){
            return compareTo(loc) == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return getNamespace() + NAMESPACE_SEPARATOR + getPath();
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }
}
