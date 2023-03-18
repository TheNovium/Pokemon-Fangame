package space.novium.gui.parts;

import space.novium.gui.Window;
import space.novium.gui.parts.enums.TextAlign;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.RegistryObject;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.renderer.FontRenderer;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector2i;
import space.novium.utils.math.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FontPart{
    private FontRenderer font;
    private Vector2f position;
    private TextAlign alignment;
    private float width;
    private List<CharSequence> lines;
    private CharSequence message;
    private List<SpriteRenderer> spriteRenderers;

    public FontPart(CharSequence chars, Vector2f position, float width, TextAlign alignment, RegistryObject<FontRenderer> font){
        this.spriteRenderers = new ArrayList<>();
        this.message = chars;
        this.lines = new ArrayList<>();
        this.position = position;
        this.width = width;
        this.alignment = alignment;
        this.font = font.get();
        loadMessage(chars);
        updateRender(font.getKey().getLocation());
    }

    private void loadMessage(CharSequence chars){
        List<CharSequence> words = Arrays.asList(chars.toString().split(" "));
        int spaceWidth = font.getWidth(" ");
        int maxWidth = (int)((float)(Window.get().getWidth() / 2) * width);
        int currentWidth = 0;
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < words.size(); i++){
            CharSequence t = words.get(i);
            int w = font.getWidth(t);
            if(currentWidth + w <= maxWidth){
                str.append(t).append(' ');
                currentWidth += w + spaceWidth;
            } else {
                str.delete(str.length() - 1, str.length());
                lines.add(str.toString());
                str = new StringBuilder();
                currentWidth = 0;
                i--;
            }
        }
        str.delete(str.length() - 1, str.length());
        lines.add(str.toString());
    }

    private void setMessage(CharSequence message){
        this.message = message;
        this.lines = new ArrayList<>();
        loadMessage(message);
    }

    private void updateRender(ResourceLocation loc){
        Vector2i windowDimensions = Window.get().getWindowSize();
        float textHeight = ((float) font.getHeight("A") / windowDimensions.getY());
        float drawY = position.y - (textHeight * lines.size());
        Vector4f fullAtlasLoc = Renderer.get().getHandler().getDrawLocationForResourceLocation(loc);
        for(int i = 0; i < lines.size(); i++){
            CharSequence s = lines.get(i);
            float wordWidth = ((float) font.getWidth(s)) / windowDimensions.getX() * 2.0f;
            float drawX;
            switch(alignment){
                case CENTER -> drawX = position.x + ((width - wordWidth) / 2.0f);
                case RIGHT -> drawX = position.x + width - wordWidth;
                default -> drawX = position.x;
            }
            for(int j = 0; j < s.length(); j++){
                char c = s.charAt(j);
                if(c != '\n' && c != '\r'){
                    FontRenderer.Glyph glyph = font.getGlyph(c);
                    float dispWidth = ((float)font.getWidth(String.valueOf(c))) / windowDimensions.getX();
                    float dispHeight = ((float)font.getHeight(String.valueOf(c)) / windowDimensions.getY());
                    SpriteRenderer spr = new SpriteRenderer(TextureAtlasType.TEXT);
                    spr.addDrawLocation(new Vector4f(
                            (((float) glyph.x) / ((float) font.getTotalWidth())) * fullAtlasLoc.getW(),
                            fullAtlasLoc.getY(),
                            ((float) glyph.width) / ((float) font.getTotalWidth()),
                            fullAtlasLoc.getH()
                    ));
                    GameObject gameObject = new GameObject(String.valueOf(c), new Transform(new Vector2f(drawX, drawY), new Vector2f(dispWidth, dispHeight), 64));
                    gameObject.addComponent(spr);
                    spriteRenderers.add(spr);
                    Renderer.get().add(gameObject);
                    drawX += dispWidth;
                }
            }
            drawY -= textHeight;
        }
    }
}
