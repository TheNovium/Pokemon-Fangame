package space.novium.nebula.graphics.gui.parts.hud;

public interface ITabAction {
    /**
     * Called every update frame
     * **/
    void tick();

    /**
     * Called when the tab is active and the tab left button is pressed
     * **/
    void leftButton();

    /**
     * Called when the tab is active and the tab right button is pressed
     * **/
    void rightButton();

    /**
     * Called when the tab is active and the tab select button is pressed
     * **/
    void select();

    /**
     * Enables the tab
     * **/
    void enable();

    /**
     * Disables the tab
     * **/
    void disable();
}
