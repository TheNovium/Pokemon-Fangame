package space.novium.nebula.graphics.gui.parts.interfaces;

import space.novium.utils.math.Vector2f;

public interface IMenuPart {
    /**
     * Do the action of the menu part
     * **/
    void click();

    /**
     * Enables the part
     * **/
    void setActive();

    /**
     * Disables the part
     * **/
    void setInactive();

    /**
     * Sets the position of the menu part on the screen
     *
     * @param pos A vector of the locaiton information
     * **/
    void setPosition(Vector2f pos);

    /**
     * @return the width of the part
     * **/
    float getWidth();

    /**
     * @return the height of the part
     * **/
    float getHeight();
}
