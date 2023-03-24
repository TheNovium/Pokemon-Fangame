package space.novium.nebula.graphics.renderer;

import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.utils.IOUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class FontRenderer {
    private final Font font;
    private final BufferedImage texImage;
    private final Map<Character, Glyph> glyphs;
    private int totalWidth;
    private String location;

    public FontRenderer(ResourceLocation loc, Color color, float size, boolean antiAlias){
        this.glyphs = new HashMap<>();
        this.font = IOUtils.loadFont(loc).deriveFont(Font.PLAIN, size);
        this.texImage = createFontTexture(color, antiAlias);
    }

    public BufferedImage createFontTexture(Color clr, boolean antiAlias){
        int imgW = 0;
        int imgH = 0;
        for(int i = 32; i < 256; i++){
            if(i == 127) continue;
            char c = (char) i;
            BufferedImage chars = createCharImage(c, antiAlias, clr);
            if(chars == null) continue;
            imgW += chars.getWidth();
            imgH = Math.max(imgH, chars.getHeight());
        }

        BufferedImage img = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = img.createGraphics();

        int x = 0;

        for(int i = 32; i < 256; i++){
            if(i == 127) continue;
            char c = (char) i;
            BufferedImage charImage = createCharImage(c, antiAlias, clr);
            if(charImage == null) continue;
            int charW = charImage.getWidth();
            int charH = charImage.getHeight();

            Glyph charG = new Glyph(x, img.getHeight() - charH, charW, charH);
            graphics.drawImage(charImage, x, 0, null);
            x += charG.width;
            glyphs.put(c, charG);
        }
        totalWidth = x;

        return img;
    }

    private BufferedImage createCharImage(char c, boolean antiAlias, Color clr){
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = img.createGraphics();
        if(antiAlias){
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics();
        graphics.dispose();

        int charW = metrics.charWidth(c);
        int charH = metrics.getHeight();
        if(charW == 0){
            return null;
        }

        img = new BufferedImage(charW, charH, BufferedImage.TYPE_INT_ARGB);
        graphics = img.createGraphics();
        if(antiAlias){
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        graphics.setFont(font);
        graphics.setPaint(clr);
        graphics.drawString(String.valueOf(c), 0, metrics.getMaxAscent());
        graphics.dispose();
        return img;
    }

    public BufferedImage getTexImage() {
        return texImage;
    }

    public int getTotalWidth(){
        return totalWidth;
    }

    public int getWidth(CharSequence str){
        int width = 0;
        int lineWidth = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '\n'){
                width = Math.max(width, lineWidth);
                lineWidth = 0;
                continue;
            }
            if(c == '\r') continue;
            lineWidth += glyphs.get(c).width;
        }
        return Math.max(width, lineWidth);
    }

    public int getHeight(CharSequence str){
        int height = glyphs.get('A').height;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '\n'){
                height += glyphs.get('A').height;
            }
        }
        return height;
    }

    public String getRegistryName(){
        return location;
    }

    public void setRegistryName(String str){
        if(getRegistryName() != null){
            System.err.println("Unable to set registry name to " + str + " because font is already registered as " + location);
        } else {
            this.location = str;
        }
    }

    public void setRegistryName(ResourceLocation loc){
        setRegistryName(loc.toString());
    }

    public Glyph getGlyph(char c){
        return glyphs.getOrDefault(c, glyphs.get(' '));
    }

    public class Glyph {
        public final int x;
        public final int y;
        public final int width;
        public final int height;

        public Glyph(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
}
