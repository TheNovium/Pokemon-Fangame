package space.novium.nebula.core.event;

import space.novium.Game;
import space.novium.nebula.core.event.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventListener {
    String modID() default Game.ID;
    EventType event();
}
