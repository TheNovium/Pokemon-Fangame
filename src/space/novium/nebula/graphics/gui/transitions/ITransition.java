package space.novium.nebula.graphics.gui.transitions;

public interface ITransition {
    void setTime(float t);

    float getTime();

    void fadeIn();

    void fadeOut();

    void tick(float dt);
}
