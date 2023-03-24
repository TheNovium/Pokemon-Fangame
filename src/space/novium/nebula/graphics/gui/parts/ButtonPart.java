package space.novium.nebula.graphics.gui.parts;

import space.novium.nebula.graphics.gui.Theme;
import space.novium.nebula.graphics.gui.parts.enums.TextAlign;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.ColorUtils;
import space.novium.utils.math.Vector4f;

public class ButtonPart {
    private TextPart text;
    private Transform transform;

    //Allows customization of button
    private Vector4f backgroundColor;

    public ButtonPart(){
        this("");
    }

    public ButtonPart(String text){
        this(text, new Transform(Renderer.GUI_Z));
    }

    public ButtonPart(String text, Transform transform){
        Theme theme = Theme.get();
        this.backgroundColor = ColorUtils.CLEAR;
        this.transform = transform;
        this.text = new TextPart(text, transform.getPosition(), transform.getScale().getX(), TextAlign.LEFT, theme.getPrimaryFont());

    }

    public ButtonPart withBackgroundColor(Vector4f clr){
        this.backgroundColor = clr;
        return this;
    }

    public ButtonPart withText(String text){
        this.text.setMessage(text);
        return this;
    }
}
