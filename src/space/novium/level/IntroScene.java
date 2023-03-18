package space.novium.level;

import space.novium.gui.parts.FontPart;
import space.novium.gui.parts.ItemPart;
import space.novium.gui.parts.enums.TextAlign;
import space.novium.level.registration.GameFonts;
import space.novium.level.registration.GameItems;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.VertexArray;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

public class IntroScene extends Scene {
    private VertexArray vertexArray;
    private float temp = 0.0f;
    private ItemPart pokeballTest;
    private FontPart fontTest;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.camera.adjustProjection();
    }

    public void init(){
        pokeballTest = new ItemPart(GameItems.POKEBALL.getKey().getLocation(), new Vector2f(0, 0), 0.2f);
        fontTest = new FontPart("ab", new Vector2f(-0.8f, -0.5f), 1.6f, TextAlign.CENTER, GameFonts.SYMBOL_NORMAL);
    }

    @Override
    public void update(float dt) {
        temp += dt / 10.0f;
        fontTest.setColor((float)Math.sin(temp), (float)Math.cos(temp), (float)-Math.sin(temp), 1.0f);
    }

    @Override
    public void render() {



        Renderer.get().render();
    }
}
