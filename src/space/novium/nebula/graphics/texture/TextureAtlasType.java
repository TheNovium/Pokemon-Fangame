package space.novium.nebula.graphics.texture;

import static org.lwjgl.opengl.GL13.*;

public enum TextureAtlasType {
    BACKGROUND("background", GL_TEXTURE0),
    STATIC_IMAGE("static image", GL_TEXTURE1),
    DYNAMIC_IMAGE("dynamic image", GL_TEXTURE2),
    TEXT("text", GL_TEXTURE3),
    UI("user interface", GL_TEXTURE4),
    NONE("no atlas", GL_TEXTURE5);

    private final String description;
    private final int glTexture;

    TextureAtlasType(String description, int glTexture){
        this.description = description;
        this.glTexture = glTexture;
    }

    public String getDescription() {
        return description;
    }

    public int getGlTexture() {
        return glTexture;
    }

    public int getTextureValue(){
        return glTexture - GL_TEXTURE0;
    }
}
