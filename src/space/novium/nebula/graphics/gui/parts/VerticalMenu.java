package space.novium.nebula.graphics.gui.parts;

import space.novium.nebula.KeyInput;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.gui.parts.interfaces.IMenu;
import space.novium.nebula.graphics.gui.parts.interfaces.IMenuPart;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.nebula.graphics.renderer.Transform;
import space.novium.utils.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class VerticalMenu implements IMenu {
    private List<IMenuPart> parts;
    private boolean enabled;
    private int activeIndex;
    private Transform transform;
    private ImagePart selectImage;

    public VerticalMenu(){
        this(new Transform(Renderer.GUI_Z));
    }

    public VerticalMenu(Transform transform){
        this.transform = transform;
        this.parts = new ArrayList<>();
        this.enabled = false;
        this.activeIndex = 0;
    }

    public VerticalMenu withSelectImage(ResourceLocation loc){
        //TODO add code to allow for a visual selection of an image
        return this;
    }

    @Override
    public void enable() {
        enabled = true;
        parts.get(activeIndex).setActive();
    }

    @Override
    public void disable() {
        enabled = false;
        parts.get(activeIndex).setInactive();
    }

    @Override
    public void tick(){
        if(enabled){
            if(activeIndex < parts.size() - 1 && KeyInput.isPressed(KeyInput.SELECT_DOWN)){
                parts.get(activeIndex).setInactive();
                activeIndex++;
                parts.get(activeIndex).setActive();
            }
            if(activeIndex > 0 && KeyInput.isPressed(KeyInput.SELECT_UP)){
                parts.get(activeIndex).setInactive();
                activeIndex--;
                parts.get(activeIndex).setActive();
            }
            if(KeyInput.isPressed(KeyInput.SELECT)){
                parts.get(activeIndex).click();
            }
        }
    }

    @Override
    public VerticalMenu add(IMenuPart part) {
        float yPos = transform.getPosition().getY();
        for(int i = 0; i < parts.size(); i++){
            yPos -= part.getHeight();
            yPos -= 0.01f;
        }
        System.out.println(yPos);
        part.setPosition(new Vector2f(transform.getPosition().getX(), yPos));
        parts.add(part);
        parts.get(parts.size() - 1).setInactive();
        parts.get(activeIndex).setActive();
        return this;
    }
}
