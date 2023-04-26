package space.novium.nebula.graphics.gui.parts.interfaces;

public interface IMenu {

    /**
     * Enables the menu
     * **/
    void enable();

    /**
     * Disables the menu
     * **/
    void disable();

    /**
     * Ticks the menu
     * **/
    void tick();

    /**
     * Add a part to the menu
     * **/
    IMenu add(IMenuPart part);
}
