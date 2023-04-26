package space.novium.nebula.graphics.gui.transitions;

public interface ITransition {
    /**
     * Sets the amount of time the transition should take
     *
     * @param t the time in seconds
     * **/
    void setTime(float t);

    /**
     * @return the time the transition takes
     * **/
    float getTime();

    /**
     * Starts the fade in of the transition
     * **/
    void fadeIn();

    /**
     * Starts the fade out of the transition
     * **/
    void fadeOut();

    /**
     * Ticks the transition
     *
     * @param dt the amount of time in seconds since the last update
     * **/
    void tick(float dt);
}
