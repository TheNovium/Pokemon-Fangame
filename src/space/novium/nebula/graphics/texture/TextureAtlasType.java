package space.novium.nebula.graphics.texture;

import static org.lwjgl.opengl.GL13.*;

public enum TextureAtlasType {
    BLANK("blank", GL_TEXTURE0),
    BACKGROUND("background", GL_TEXTURE1),
    ITEM("items", GL_TEXTURE2),
    TILE("tiles", GL_TEXTURE3),
    TEXT("text", GL_TEXTURE4),
    UI("user interface", GL_TEXTURE5),
    NONE("no atlas", GL_TEXTURE6);

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

    public int getTexId(){
        return glTexture - GL_TEXTURE0;
    }
}
