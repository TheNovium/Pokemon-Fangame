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

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
        this.camera.adjustProjection();
    }

    public void init(){
        pokeballTest = new ItemPart(GameItems.POKEBALL.getKey().getLocation(), new Vector2f(0, 0), 0.2f);
        new FontPart("AB", new Vector2f(-0.8f, -0.5f), 1.6f, TextAlign.CENTER, GameFonts.BASE_WHITE);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {



        Renderer.get().render();
    }
}
