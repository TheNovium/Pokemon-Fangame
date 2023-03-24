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

    public SpriteRenderer(TextureAtlasType type){
        this.sprite = new Sprite(type);
        this.frame = 0;
        this.color = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.type = type;
    }

    public TextureAtlasType getType() {
        return type;
    }

    public Vector4f getColor() {
        return color;
    }

    public Vector4f getFrame(){
        return sprite.getFrame(frame);
    }

    public Vector4f nextFrame(){
        if(sprite.getFrame(frame + 1) != null){
            frame++;
            setDirty();
        }
        return getFrame();
    }

    public Vector4f previousFrame(){
        if(frame > 0){
            frame--;
            setDirty();
        }
        return getFrame();
    }

    public void setFrame(int f){
        if(sprite.getFrame(f) != null){
            frame = f;
        }
    }

    public void addDrawLocation(Vector4f drawLoc){
        sprite.addDrawLocation(drawLoc);
    }

    public int getGlId(){
        return getType().getTexId();
    }

    public SpriteRenderer setColor(float r, float g, float b, float a){
        this.color = new Vector4f(r, g, b, a);
        setDirty();
        return this;
    }

    public SpriteRenderer setColor(Vector4f vec4){
        return setColor(vec4.getX(), vec4.getY(), vec4.getW(), vec4.getH());
    }

    public SpriteRenderer setColor(Color color){
        return setColor(ColorUtils.processColor(color));
    }

    @Override
    public void setGameObject(GameObject obj) {
        super.setGameObject(obj);
    }

    @Override
    public GameObject getGameObject() {
        return super.getGameObject();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {

    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(){
        this.isDirty = true;
    }

    public void setClean(){
        this.isDirty = false;
    }
}
