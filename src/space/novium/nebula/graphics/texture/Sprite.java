package space.novium.nebula.graphics.texture;

import space.novium.utils.math.Vector4f;

import java.util.List;

public class Sprite {
    private List<Vector4f> textureLocations;
    private TextureAtlasType atlasType;

    public Sprite(TextureAtlasType type){
        this.atlasType = type;
    }

    public TextureAtlasType getAtlasType() {
        return atlasType;
    }

    public boolean animated(){
        return textureLocations.size() > 1;
    }
}
