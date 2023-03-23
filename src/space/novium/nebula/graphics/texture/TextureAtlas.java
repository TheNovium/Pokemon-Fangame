package space.novium.nebula.graphics.texture;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.IOUtils;
import space.novium.utils.math.Vector2i;
import space.novium.utils.math.Vector4f;
import space.novium.utils.math.Vector4i;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextureAtlas {
    public static final ResourceLocation NO_TEXTURE = new ResourceLocation("no_texture");

    private final TextureAtlasType type;
    //Need to eventually add the code to get the image based off entity data
    private final Map<ResourceLocation, Vector4i> imageLocs;
    private Texture texture;

    private TextureAtlas(TextureAtlasType type){
        this.type = type;
        this.imageLocs = new HashMap<>();
    }

    private void setLocation(ResourceLocation loc, Vector4i vec){
        imageLocs.put(loc, vec);
    }

    public boolean hasResource(ResourceLocation loc){
        return imageLocs.containsKey(loc);
    }

    public Vector4i getImageLocation(ResourceLocation loc){
        return imageLocs.getOrDefault(loc, getNullLocation());
    }

    public Vector4f getRelativeImageLocation(ResourceLocation loc){
        Vector4i standard = getImageLocation(loc);
        Vector4f ret = new Vector4f();
        ret.setX(((float) standard.x) / ((float) texture.getWidth()));
        ret.setY(((float) standard.y) / ((float) texture.getHeight()));
        ret.setW(((float) standard.w) / ((float) texture.getWidth()));
        ret.setH(((float) standard.h) / ((float) texture.getHeight()));
        return ret;
    }

    public Vector4i getNullLocation(){
        return imageLocs.getOrDefault(NO_TEXTURE, new Vector4i(0, 0, 0, 0));
    }

    public Texture getTexture() {
        return texture;
    }

    private void setTexture(Texture texture){
        this.texture = texture;
    }

    public TextureAtlasType getType() {
        return type;
    }

    public static class Builder{
        private Map<ResourceLocation, BufferedImage> images;
        private Vector2i largest;
        private TextureAtlas atlas;

        public Builder(TextureAtlasType type){
            this.images = new HashMap<>();
            this.largest = new Vector2i(0, 0);
            this.atlas = new TextureAtlas(type);
            addImage(NO_TEXTURE);
        }

        public Builder addImage(ResourceLocation loc){
            return addImage(loc, loc);
        }

        public Builder addImage(ResourceLocation loc, ResourceLocation home){
            BufferedImage img = IOUtils.loadImage(loc);
            return addImage(home, img);
        }

        public Builder addImage(ResourceLocation home, BufferedImage image){
            largest.x = Math.max(largest.x, image.getWidth());
            largest.y = Math.max(largest.y, image.getHeight());
            images.put(home, image);
            return this;
        }

        public TextureAtlas build(){
            BufferedImage atImg = null;
            switch(atlas.getType()){
                case TEXT -> {
                    int maxWidth = 0;
                    int height = 0;
                    for(ResourceLocation rLoc : images.keySet()){
                        BufferedImage temp = images.get(rLoc);
                        height += temp.getHeight();
                        maxWidth = Math.max(maxWidth, temp.getWidth());
                    }
                    atImg = new BufferedImage(maxWidth, height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = atImg.createGraphics();
                    int y = 0;
                    for(ResourceLocation rLoc : images.keySet()){
                        BufferedImage temp = images.get(rLoc);
                        graphics.drawImage(temp, 0, y, null);
                        atlas.setLocation(rLoc, new Vector4i(0, y, temp.getWidth(), temp.getHeight()));
                        y += temp.getHeight();
                    }
                }
                case NONE -> {
                    atImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = atImg.createGraphics();
                    atlas.setLocation(NO_TEXTURE, new Vector4i(0, 0, 16, 16));
                    graphics.drawImage(IOUtils.loadImage(NO_TEXTURE), 0, 0, null);
                }
                case BLANK -> {
                    atImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                    atImg.setRGB(0, 0, 0xFFFFFFFF);
                }
                default -> {
                    int x = 0;
                    int y = 0;
                    int width = 0;
                    int height = 0;
                    int maxWidth = (int)(Math.max(largest.y, largest.x) * Math.ceil(Math.sqrt(images.size())));
                    List<ResourceLocation> imagesByShelf = new ArrayList<>();
                    for(ResourceLocation rLoc : images.keySet()){
                        BufferedImage temp = images.get(rLoc);
                        width = temp.getWidth();
                        if(x + width > maxWidth){
                            x = 0;
                            y += height;
                            height = 0;
                        }
                        height = Math.max(height, temp.getHeight());
                        atlas.setLocation(rLoc, new Vector4i(x, y, width, temp.getHeight()));
                        x += width;
                        imagesByShelf.add(rLoc);
                    }
                    atImg = new BufferedImage(maxWidth, y + height, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = atImg.createGraphics();
                    for(ResourceLocation rLoc : imagesByShelf){
                        Vector4i imageLoc = atlas.getImageLocation(rLoc);
                        graphics.drawImage(images.get(rLoc), imageLoc.x, imageLoc.y, null);
                    }
                }
            }
            try {
                File tempFile = new File(atlas.getType().getDescription() + ".png");
                ImageIO.write(atImg, "png", tempFile);
            } catch(Exception e){
                System.out.println("Failed to compile texture atlas for " + atlas.getType().getDescription());
            }
            Texture txtr = new Texture(atImg);
            atlas.setTexture(txtr);
            return atlas;
        }
    }
}
