package space.novium.nebula.graphics.renderer;

import space.novium.Game;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.registration.FontEventRegister;
import space.novium.nebula.core.resources.registration.ItemEventRegister;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.nebula.graphics.texture.Texture;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.graphics.texture.TextureAtlasType;
import space.novium.utils.BufferUtils;
import space.novium.utils.TextureUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1024;
    private List<RenderBatch> batches;
    private static Shader boundShader;
    private TextureAtlasHandler handler;
    private static Renderer renderer;
    private int vao, vbo, ebo;
    private float[] floats = {
        -1.0f, -1.0f,       1.0f, 1.0f, 1.0f, 1.0f,     0.0f, 1.0f,     5.0f,
        -1.0f, 1.0f,        1.0f, 1.0f, 0.0f, 1.0f,     0.0f, 0.0f,     5.0f,
        1.0f, 1.0f,         1.0f, 0.0f, 1.0f, 1.0f,     1.0f, 0.0f,     5.0f,
        1.0f, -1.0f,        0.0f, 1.0f, 1.0f, 1.0f,     1.0f, 1.0f,     5.0f
    };

    private Renderer(){
        this.batches = Collections.synchronizedList(new ArrayList<>());

        //Initialize the shaders
        Shader.loadShaders();
        boundShader = Shader.DEFAULT;

        //Create the texture atlas
        TextureAtlasHandler.Builder builder = new TextureAtlasHandler.Builder(Game.ID);

        //Add all the textures the renderer will use to the texture atlas
        ItemEventRegister ier = new ItemEventRegister(builder);
        ier.registerAll();
        FontEventRegister fer = new FontEventRegister(builder);
        fer.registerAll();

        handler = builder.build();

        for(TextureAtlasType atlasType : TextureAtlasType.values()){
            glActiveTexture(atlasType.getGlTexture());
            Texture t = handler.getAtlas(atlasType).getTexture();
            t.bind();
        }

        //Test drawing
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(floats), GL_STATIC_DRAW);

        ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, new int[]{0, 1, 2, 2, 3, 0}, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 36, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 4, GL_FLOAT, false, 36, 8);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 36, 24);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, 1, GL_FLOAT, false, 36, 32);
        glEnableVertexAttribArray(3);
    }

    public static Renderer get(){
        if(renderer == null){
            renderer = new Renderer();
        }
        return renderer;
    }

    public void add(GameObject gameObject){
        SpriteRenderer spr = gameObject.getComponent(SpriteRenderer.class);
        if(spr != null){
            add(spr);
        }
    }

    public void add(SpriteRenderer spr){
        boolean added = false;
        for(RenderBatch batch : batches){
            if(batch.hasRoom() && batch.getZIndex() == spr.getGameObject().getTransform().getZ()){
                batch.addSprite(spr);
                added = true;
                break;
            }
        }

        if(!added){
            RenderBatch batch = new RenderBatch(1, spr.getGameObject().getTransform().getZ());
            batch.start();
            batches.add(batch);
            Collections.sort(batches);
        }
    }

    public static void setShader(Shader shader){
        boundShader = shader;
    }

    public static Shader getBoundShader() {
        return boundShader;
    }

    public TextureAtlasHandler getHandler(){
        return handler;
    }

    public void render(){
        boundShader.enable();
        for(RenderBatch batch : batches){
            batch.render(handler);
        }

        boundShader.setUniformMat4("ortho_matrix", TextureUtils.ORTHO_MATRIX);
        boundShader.setUniformMat4("view_matrix", TextureUtils.ORTHO_MATRIX);

        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);

        boundShader.disable();
    }

    public void clearRenderer(){
        for(RenderBatch batch : batches){
            batch.clear();
        }
        batches.clear();
    }

    public void cleanup(){

    }
}
