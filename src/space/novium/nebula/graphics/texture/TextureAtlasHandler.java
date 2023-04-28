package space.novium.nebula.graphics.texture;

import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.math.Vector4f;

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

    /**
     * Gets a texture atlas
     *
     * @param type The atlas to look for
     *
     * @return The texture atlas associated with the atlas type
     * **/
    public TextureAtlas getAtlas(TextureAtlasType type){
        return atlases.getOrDefault(type, null);
    }

    /**
     * Finds the SpriteRenderer associated with a resource location
     *
     * @param loc The resource location to search for
     *
     * @return a sprite renderer of the sprite if it is found
     * **/
    public SpriteRenderer getRendererForResourceLocation(ResourceLocation loc){
        for(TextureAtlasType type : TextureAtlasType.values()){
            if(atlases.get(type).hasResource(loc)){
                SpriteRenderer spr = new SpriteRenderer(type);
                spr.addDrawLocation(atlases.get(type).getRelativeImageLocation(loc));
                return spr;
            }
        }
        SpriteRenderer spr = new SpriteRenderer(TextureAtlasType.NONE);
        spr.addDrawLocation(new Vector4f(0, 0, 1, 1));
        return spr;
    }

    /**
     * Returns draw information for a sprite
     *
     * @param loc The location of the sprite
     *
     * @return the x, y, w, h of the information of the location
     * **/
    public Vector4f getDrawLocationForResourceLocation(ResourceLocation loc){
        for(TextureAtlasType type : TextureAtlasType.values()){
            if(atlases.get(type).hasResource(loc)){
                return atlases.get(type).getRelativeImageLocation(loc);
            }
        }
        return new Vector4f(0, 0, 1, 1);
    }

    public static class Builder {
        private String id;
        private Map<TextureAtlasType, TextureAtlas.Builder> atlasBuilders;

        public Builder(String id){
            this.id = id;
            atlasBuilders = new HashMap<>();
        }

        /**
         * Loads a texture into the specified atlas
         *
         * @param loc The location of the image in the resources folder
         * @param type The atlas type to load the image into
         *
         * @return itself
         * **/
        public Builder loadTexture(ResourceLocation loc, TextureAtlasType type){
            return loadTexture(loc, loc, type);
        }

        /**
         * Loads a texture into the specified atlas
         *
         * @param loc The location of the image in the resources folder
         * @param home  The location where the image should be stored in the texture atlas
         * @param type The atlas type to load the image into
         *
         * @return itself
         * **/
        public Builder loadTexture(ResourceLocation loc, ResourceLocation home, TextureAtlasType type){
            if(!atlasBuilders.containsKey(type)){
                atlasBuilders.put(type, new TextureAtlas.Builder(type));
            }
            TextureAtlas.Builder build = atlasBuilders.get(type);
            build.addImage(loc, home);
            return this;
        }

        /**
         * Loads a texture into the specified atlas
         *
         * @param loc The location that the image should be stored in the texture atlas
         * @param type The atlas type to load the image into
         * @param img Image data to be processed
         *
         * @return itself
         * **/
        public Builder loadTexture(ResourceLocation loc, TextureAtlasType type, BufferedImage img){
            if(!atlasBuilders.containsKey(type)){
                atlasBuilders.put(type, new TextureAtlas.Builder(type));
            }
            TextureAtlas.Builder build = atlasBuilders.get(type);
            build.addImage(loc, img);
            return this;
        }

        /**
         * @return A compiled TextureAtlasHandler
         * **/
        public TextureAtlasHandler build(){
            TextureAtlasHandler handler = new TextureAtlasHandler(id);
            for(TextureAtlasType type : TextureAtlasType.values()){
                if(atlasBuilders.containsKey(type)){
                    handler.atlases.put(type, atlasBuilders.get(type).build());
                } else if(type == TextureAtlasType.BLANK){
                    handler.atlases.put(type, new TextureAtlas.Builder(TextureAtlasType.BLANK).build());
                } else {
                    handler.atlases.put(type, new TextureAtlas.Builder(TextureAtlasType.NONE).build());
                }
            }
            return handler;
        }
    }
}
