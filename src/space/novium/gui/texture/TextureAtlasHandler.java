package space.novium.gui.texture;

import space.novium.nebula.resources.ResourceLocation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class TextureAtlasHandler {
    private Map<TextureAtlasType, TextureAtlas> atlases;
    private final String id;

    private TextureAtlasHandler(String id){
        this.id = id;
        this.atlases = new HashMap<>();
    }

    public TextureAtlas getAtlas(TextureAtlasType type){
        return atlases.getOrDefault(type, null);
    }

    public static class Builder {
        private String id;
        private Map<TextureAtlasType, TextureAtlas.Builder> atlasBuilders;

        public Builder(String id){
            this.id = id;
            atlasBuilders = new HashMap<>();
        }


        public Builder loadTexture(ResourceLocation loc, ResourceLocation home, TextureAtlasType type){
            if(!atlasBuilders.containsKey(type)){
                atlasBuilders.put(type, new TextureAtlas.Builder(type));
            }
            TextureAtlas.Builder build = atlasBuilders.get(type);
            build.addImage(loc, home);
            return this;
        }

        public Builder loadTexture(ResourceLocation loc, TextureAtlasType type, BufferedImage img){
            if(!atlasBuilders.containsKey(type)){
                atlasBuilders.put(type, new TextureAtlas.Builder(type));
            }
            TextureAtlas.Builder build = atlasBuilders.get(type);
            build.addImage(loc, img);
            return this;
        }

        public TextureAtlasHandler build(){
            TextureAtlasHandler handler = new TextureAtlasHandler(id);
            for(TextureAtlasType type : TextureAtlasType.values()){
                if(atlasBuilders.containsKey(type)){
                    handler.atlases.put(type, atlasBuilders.get(type).build());
                } else {
                    handler.atlases.put(type, new TextureAtlas.Builder(TextureAtlasType.NONE).build());
                }
            }
            return handler;
        }
    }
}
