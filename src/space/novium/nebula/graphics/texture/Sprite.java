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

    public void addDrawLocation(Vector4f drawLoc){
        textureLocations.add(drawLoc);
    }

    public TextureAtlasType getAtlasType() {
        return atlasType;
    }

    public boolean animated(){
        return textureLocations.size() > 1;
    }

    public Vector4f getFrame(int frame){
        if(frame >= 0 && frame < textureLocations.size()){
            return textureLocations.get(frame);
        }
        return new Vector4f(1.0f);
    }
}
