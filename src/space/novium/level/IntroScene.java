package space.novium.level;

import space.novium.gui.parts.FontPart;
import space.novium.gui.parts.enums.TextAlign;
import space.novium.level.registration.GameFonts;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

public class IntroScene extends Scene {

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.camera.adjustProjection();
    }

    public void init(){
        new FontPart("Important! Please read.", new Vector2f(-0.9f, 0.8f), 1.8f, TextAlign.CENTER, GameFonts.BASE_LARGE).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new FontPart("This is a non-profit fan game. Assets and concepts may come from GameFreak or other related entities. Please support the official games!", new Vector2f(-0.95f, 0.7f), 1.9f, TextAlign.CENTER, GameFonts.BASE_NORMAL);
        new FontPart("Pokemon Space is currently in an alpha state. Bugs will likely be present, but I'm working on keeping this game as up to date as possible.", new Vector2f(-0.9f, 0.2f), 1.8f, TextAlign.CENTER, GameFonts.BASE_SMALL);
        new FontPart("Save your game often!", new Vector2f(-0.9f, -0.2f), 1.8f, TextAlign.CENTER, GameFonts.BASE_NORMAL).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new FontPart("Thank you for your support! Bugs can be reported to the github repo.", new Vector2f(-0.9f, -0.35f), 1.8f, TextAlign.CENTER, GameFonts.BASE_NORMAL);
        new FontPart("good luck", new Vector2f(-0.8f, -0.85f), 1.6f, TextAlign.CENTER, GameFonts.SYMBOL_NORMAL).setColor(0.4f, 0.4f, 0.4f, 0.8f);
        new FontPart("https://novium.space", new Vector2f(-0.99f, -0.96f), 1.98f, TextAlign.LEFT, GameFonts.BASE_TINY).setColor(0.9f, 0.9f, 1.0f, 1.0f);
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render() {
        Renderer.get().render();
    }
}
