package space.novium.nebula.graphics.gui.parts.interfaces;

import space.novium.utils.math.Vector2f;

public interface IMenuPart {
    void click();

    void setActive();

    void setInactive();

    void setPosition(Vector2f pos);

    float getWidth();

    float getHeight();
}
