package space.novium.level;

import space.novium.nebula.graphics.Camera;
import space.novium.nebula.graphics.VertexArray;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.nebula.graphics.texture.Texture;
import space.novium.utils.TextureUtils;
import space.novium.utils.math.Vector2f;

public class IntroScene extends Scene {
    private Texture test;
    private VertexArray vert;

    public IntroScene(){
        this.camera = new Camera(new Vector2f());
    }

    public void init(){
        test = new Texture(TextureUtils.NO_TEXTURE);
        vert = new VertexArray(
            TextureUtils.getDrawLocation(-0.2f, -0.2f, 1.0f, 0.4f, 0.4f, true),
            TextureUtils.INDICES,
            TextureUtils.getTextureDraw(0, 0, 1, 1),
            1.0f
        );
    }

    @Override
    public void update(float dt) {
        
    }

    @Override
    public void render() {
        Shader.DEFAULT.enable();
        Shader.DEFAULT.setUniformMat4("ortho_matrix", camera.getProjectionMatrix());
        Shader.DEFAULT.setUniformMat4("view_matrix", camera.getViewMatrix());
        Shader.DEFAULT.setUniform1f("alpha", vert.getAlpha());
        test.bind();
        vert.render();
        vert.unbind();
        test.unbind();
        Shader.DEFAULT.disable();
    }
}
