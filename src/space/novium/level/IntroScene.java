package space.novium.level;

import space.novium.gui.Window;
import space.novium.gui.parts.TextPart;
import space.novium.gui.parts.ImagePart;
import space.novium.gui.parts.enums.RectPart;
import space.novium.gui.parts.enums.TextAlign;
import space.novium.level.registration.GameFonts;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

import java.awt.*;

public class IntroScene extends Scene {
    private RectPart fadePart;
    private float time = 0.0f;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.camera.adjustProjection();
    }

    @Override
    public void init(){
        Window.get().setTitle("Pokemon Space | Alpha 0.0.1");
        new ImagePart(GameResourceLocations.PIXEL_BACKGROUND).setAlpha(0.5f);
        new ImagePart(GameResourceLocations.LIGHT_GREY_BANNER, new Vector2f(-1.0f, 0.1f), new Vector2f(2.0f, 0.8f), Renderer.HUD_Z).setAlpha(0.8f);
        new TextPart("Important! Please read.", new Vector2f(-0.9f, 0.75f), 1.8f, TextAlign.CENTER, GameFonts.BASE_LARGE).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new TextPart("This is a non-profit fan game. Assets and concepts may come from GameFreak or other related entities. Please support the official games!", new Vector2f(-0.95f, 0.65f), 1.9f, TextAlign.CENTER, GameFonts.BASE_NORMAL);
        new TextPart("Pokemon Space is currently in an alpha state. Bugs will likely be present, but I'm working on keeping this game as up to date as possible.", new Vector2f(-0.9f, 0.2f), 1.8f, TextAlign.CENTER, GameFonts.BASE_SMALL);
        new TextPart("Save your game often!", new Vector2f(-0.9f, -0.2f), 1.8f, TextAlign.CENTER, GameFonts.BASE_NORMAL).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new TextPart("Thank you for your support! Bugs can be reported to the github repo.", new Vector2f(-0.9f, -0.35f), 1.8f, TextAlign.CENTER, GameFonts.BASE_NORMAL);
        new TextPart("good luck", new Vector2f(-0.8f, -0.75f), 1.6f, TextAlign.CENTER, GameFonts.SYMBOL_NORMAL).setColor(0.4f, 0.4f, 0.4f, 1.0f);
        new TextPart("https://novium.space", new Vector2f(-0.99f, -0.96f), 1.98f, TextAlign.LEFT, GameFonts.BASE_TINY).setColor(0.9f, 0.9f, 1.0f, 1.0f);
        fadePart = new RectPart(new Vector2f(-1.0f, -1.0f), new Vector2f(2.0f, 2.0f), Renderer.OVERLAY_Z);
        fadePart.setColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void update(float dt) {
        time += dt;
    }

    @Override
    public void render() {
        if(time <= 1.0f){
            fadePart.setColor(0.0f, 0.0f, 0.0f, 1.0f - time);
        }
        if(time >= 8.0f && time <= 9.0f){
            fadePart.setColor(0.0f, 0.0f, 0.0f, time - 8.0f);
        }
        if(time > 9.0f){
            Window.setScene(Homepage::new);
        }
        Renderer.get().render();
    }
}
