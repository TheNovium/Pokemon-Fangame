package space.novium.nebula.graphics.renderer;

import space.novium.Game;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.registration.FontEventRegister;
import space.novium.nebula.core.resources.registration.ItemEventRegister;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1024;
    private List<RenderBatch> batches;
    private static Shader boundShader;
    private TextureAtlasHandler handler;
    private static Renderer renderer;

    private Renderer(){
        this.batches = Collections.synchronizedList(new ArrayList<>());
        TextureAtlasHandler.Builder builder = new TextureAtlasHandler.Builder(Game.ID);

        //Add all the textures the renderer will use to the texture atlas
        ItemEventRegister ier = new ItemEventRegister(builder);
        ier.registerAll();
        FontEventRegister fer = new FontEventRegister(builder);
        fer.registerAll();

        handler = builder.build();
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
            RenderBatch batch = new RenderBatch(MAX_BATCH_SIZE, spr.getGameObject().getTransform().getZ());
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

    public void render(){
        boundShader.enable();
        for(RenderBatch batch : batches){
            batch.render(handler);
        }
    }

    public void clearRenderer(){
        for(RenderBatch batch : batches){
            batch.clear();
        }
        batches.clear();
    }
}
