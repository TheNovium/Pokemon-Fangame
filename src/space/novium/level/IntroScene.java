package space.novium.level;

import space.novium.nebula.graphics.gui.Theme;
import space.novium.nebula.graphics.gui.Window;
import space.novium.nebula.graphics.gui.parts.TextPart;
import space.novium.nebula.graphics.gui.parts.ImagePart;
import space.novium.nebula.graphics.gui.parts.enums.TextAlign;
import space.novium.nebula.graphics.gui.transitions.FadeTransition;
import space.novium.nebula.graphics.gui.transitions.enums.TransitionType;
import space.novium.level.registration.GameFonts;
import space.novium.nebula.core.resources.registration.GameResourceLocations;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

public class IntroScene extends Scene {
    private FadeTransition fade;
    private float time = 0.0f;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
    }

    @Override
    public void init(){
        Theme theme = Theme.get();
        Window.get().setTitle("Pokemon Falling Star | Alpha 0.0.1");
        new ImagePart(GameResourceLocations.WARNING_SYMBOL, new Vector2f(-0.94f, 0.56f), 0.16f, Renderer.HUD_Z + 1);
        new ImagePart(GameResourceLocations.PIXEL_BACKGROUND).setAlpha(0.5f);
        new ImagePart(GameResourceLocations.LIGHT_GREY_BANNER, new Vector2f(-1.0f, 0.5f), new Vector2f(2.0f, 0.4f), Renderer.HUD_Z).setAlpha(0.6f);
        new TextPart("Important! Please read.", new Vector2f(-0.9f, 0.75f), 1.8f, TextAlign.CENTER, theme.getTitleFont()).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new TextPart("This is a non-profit fan game. Assets and concepts may come from GameFreak or other related entities. Please support the official games!", new Vector2f(-0.95f, 0.65f), 1.9f, TextAlign.CENTER, theme.getPrimaryFont());
        new TextPart("Pokemon Falling Star is currently in an alpha state. Bugs will likely be present, but I'm working on keeping this game as up to date as possible.", new Vector2f(-0.9f, 0.15f), 1.8f, TextAlign.CENTER, theme.getSecondaryFont());
        new TextPart("Save your game often!", new Vector2f(-0.9f, -0.2f), 1.8f, TextAlign.CENTER, GameFonts.BASE_NORMAL).setColor(1.0f, 0.1f, 0.1f, 1.0f);
        new TextPart("Thank you for your support! Bugs can be reported to the github repo.", new Vector2f(-0.9f, -0.35f), 1.8f, TextAlign.CENTER, theme.getPrimaryFont());
        new TextPart("good luck", new Vector2f(-0.8f, -0.75f), 1.6f, TextAlign.CENTER, GameFonts.SYMBOL_NORMAL).setColor(0.4f, 0.4f, 0.4f, 1.0f);
        new TextPart("https://novium.space", new Vector2f(-0.99f, -0.96f), 1.98f, TextAlign.LEFT, GameFonts.BASE_TINY).setColor(theme.getHighlightColor());
        fade = new FadeTransition(1.0f, TransitionType.QUICK_FADE);
        fade.fadeIn();
    }

    @Override
    public void update(float dt) {
        time += dt;
        fade.tick(dt);
        if(time > 9.0f){
            Window.setScene(Homepage::new);
        }
    }

    @Override
    public void render() {
        if(time >= 8.0f && !fade.isFadingOut()){
            fade.fadeOut();
        }
    }

    @Override
    public void cleanup() {
        //Nothing to clean up
        return;
    }
}
