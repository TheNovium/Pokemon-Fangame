package space.novium.nebula.graphics.gui.parts.interfaces;

public interface IMenu {
    void enable();

    void disable();

    void tick();

    IMenu add(IMenuPart part);
}
