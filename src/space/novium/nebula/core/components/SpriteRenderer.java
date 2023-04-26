package space.novium.nebula.core.components;

import space.novium.nebula.core.Component;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.graphics.texture.Sprite;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.ColorUtils;
import space.novium.utils.math.Vector4f;

import java.awt.*;

public class SpriteRenderer extends Component {
    private Sprite sprite;
    private int frame;
    private Vector4f color;
    private TextureAtlasType type;
    private boolean isDirty = true;

    /**
     * Create a new sprite renderer object
     *
     * @param type The TextureAtlas that the sprites are bound to
     * **/
    public SpriteRenderer(TextureAtlasType type){
        this.sprite = new Sprite(type);
        this.frame = 0;
        this.color = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.type = type;
    }

    /**
     * @return The TextureAtlasType the the SpriteRenderer is associated with
     * **/
    public TextureAtlasType getType() {
        return type;
    }

    /**
     * @return The Vector4f of color data
     * **/
    public Vector4f getColor() {
        return color;
    }

    /**
     * @return The Vector4f with position and size data of the current frame
     * **/
    public Vector4f getFrame(){
        return sprite.getFrame(frame);
    }

    /**
     * @return The Vector4f with position and size data of the next frame and advances the current frame to the next frame
     * **/
    public Vector4f nextFrame(){
        if(sprite.getFrame(frame + 1) != null){
            frame++;
            setDirty();
        }
        return getFrame();
    }

    /**
     * @return the Vector4f with position and size data of the previous frame and sets the current frame to the previous frame
     * **/
    public Vector4f previousFrame(){
        if(frame > 0){
            frame--;
            setDirty();
        }
        return getFrame();
    }

    /**
     * Sets the current frame to the frame provided
     *
     * @param f The index of the frame you'd like to set to
     * **/
    public void setFrame(int f){
        if(sprite.getFrame(f) != null){
            frame = f;
        }
        setDirty();
    }

    /**
     * Adds another frame to the SpriteRenderer
     *
     * @param drawLoc A location on an atlas of where the frame is stored
     * **/
    public void addDrawLocation(Vector4f drawLoc){
        sprite.addDrawLocation(drawLoc);
    }

    /**
     * @return the OpenGL ID of the TextureAtlas
     * **/
    public int getGlId(){
        return getType().getTexId();
    }

    /**
     * Changes the color of the current sprite and returns this
     *
     * @param r Red channel from 0.0f - 1.0f
     * @param g Green channel from 0.0f - 1.0f
     * @param b Blue channel from 0.0f - 1.0f
     * @param a Alpha channel from 0.0f - 1.0f
     *
     * @return itself to be used as a builder
     * **/
    public SpriteRenderer setColor(float r, float g, float b, float a){
        this.color = new Vector4f(r, g, b, a);
        setDirty();
        return this;
    }

    /**
     * Changes the color of the current sprite and returns this
     *
     * @param vec4 Vector4f of the color data
     *
     * @return itself to be used as a builder
     * **/
    public SpriteRenderer setColor(Vector4f vec4){
        return setColor(vec4.getX(), vec4.getY(), vec4.getW(), vec4.getH());
    }


    /**
     * Changes the color of the current sprite and returns this
     *
     * @param color Java Color
     *
     * @return itself to be used as a builder
     * **/
    public SpriteRenderer setColor(Color color){
        return setColor(ColorUtils.processColor(color));
    }

    /**
     * Sets the parent game object
     *
     * @param obj The Game Object the parent should be
     * **/
    @Override
    public void setGameObject(GameObject obj) {
        super.setGameObject(obj);
    }


    /**
     * @return the parent game object
     * **/
    @Override
    public GameObject getGameObject() {
        return super.getGameObject();
    }

    /**
     * Starts the component
     * **/
    @Override
    public void start() {
        super.start();
    }

    /**
     * Ticks the component
     * **/
    @Override
    public void tick() {

    }

    /**
     * @return Whether the object has been changed
     * **/
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * Sets the object to notify it has been changed
     * **/
    public void setDirty(){
        this.isDirty = true;
    }

    /**
     * Sets the object to notify it hasn't been changed
     * **/
    public void setClean(){
        this.isDirty = false;
    }
}
