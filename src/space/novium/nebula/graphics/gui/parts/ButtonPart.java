package space.novium.nebula.graphics.gui.parts;

import space.novium.nebula.graphics.gui.Theme;
import space.novium.nebula.graphics.gui.parts.actions.IButtonAction;
import space.novium.nebula.graphics.gui.parts.enums.TextAlign;
import space.novium.nebula.graphics.gui.parts.interfaces.IMenuPart;
import space.novium.nebula.graphics.renderer.FontRenderer;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.ColorUtils;
import space.novium.utils.math.Vector2f;
import space.novium.utils.math.Vector4f;

import java.util.function.Supplier;

public class ButtonPart implements IMenuPart {
    private TextPart text;
    private RectPart backgroundPart;
    private Transform transform;
    private Supplier<IButtonAction> onClick;

    //Allows customization of button
    private Vector4f backgroundColor;

    public ButtonPart(){
        this("");
    }

    public ButtonPart(String text){
        this(text, new Transform(new Vector2f(0, 0), new Vector2f(1.0f, 0.1f), Renderer.GUI_Z));
    }

    public ButtonPart(String text, Transform transform){
        Theme theme = Theme.get();
        this.backgroundColor = ColorUtils.CLEAR;
        this.transform = transform;
        this.text = new TextPart(text, transform.getPosition(), transform.getScale().getX(), TextAlign.LEFT, theme.getPrimaryFont());
        this.backgroundPart = new RectPart(transform.getPosition(), transform.getScale(), transform.getZ());
        this.onClick = null;

        withBackgroundColor(backgroundColor);
        setInactive();
    }

    public ButtonPart withBackgroundColor(Vector4f clr){
        this.backgroundColor = clr;
        backgroundPart.setColor(backgroundColor);
        return this;
    }

    public ButtonPart withText(String text){
        this.text.setMessage(text);
        reload();
        return this;
    }

    public ButtonPart withFont(FontRenderer font){
        this.text.setFont(font);
        reload();
        return this;
    }

    private void reload(){
        transform.setScale(text.getWidth(), text.getHeight());
    }

    public ButtonPart withPosition(Vector2f pos){
        transform.setPosition(pos);
        text.setPosition(pos);
        return this;
    }

    public ButtonPart withScale(Vector2f scale){
        transform.scale(scale);
        return this;
    }

    public ButtonPart onClick(Supplier<IButtonAction> supplier){
        this.onClick = supplier;
        return this;
    }

    @Override
    public void click(){
        if(onClick != null){
            onClick.get().act();
        }
    }

    //TODO set up a way to define what to do on active

    @Override
    public void setActive() {
        text.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void setInactive() {
        text.setColor(0.7f, 0.7f, 0.7f, 1.0f);
    }

    @Override
    public void setPosition(Vector2f pos) {
        transform.setPosition(pos);
        resetFromTransform();
    }

    private void resetFromTransform(){
        text.setPosition(transform.getPosition());
    }

    @Override
    public float getWidth() {
        return transform.getScale().getX();
    }

    @Override
    public float getHeight() {
        return transform.getScale().getY();
    }
}
