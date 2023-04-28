package space.novium.nebula.graphics.texture;

import space.novium.utils.math.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private List<Vector4f> textureLocations;
    private TextureAtlasType atlasType;

    public Sprite(TextureAtlasType type){
        this.atlasType = type;
        this.textureLocations = new ArrayList<>();
    }

    /**
     * Adds information about the location of sprites on the texture atlas
     *
     * @param drawLoc a vector4f of the data x location, y location, width, height, all normalized
     * **/
    public void addDrawLocation(Vector4f drawLoc){
        textureLocations.add(drawLoc);
    }

    /**
     * @return the texture atlas type associated with the sprite
     * **/
    public TextureAtlasType getAtlasType() {
        return atlasType;
    }

    /**
     * @return if the texture is animated
     * **/
    public boolean animated(){
        return textureLocations.size() > 1;
    }

    /**
     * Gets the specific information about a frame
     *
     * @param frame the frame to check for
     *
     * @return a vector4f with the information about the frame
     * **/
    public Vector4f getFrame(int frame){
        if(frame >= 0 && frame < textureLocations.size()){
            return textureLocations.get(frame);
        }
        return new Vector4f(1.0f);
    }
}
