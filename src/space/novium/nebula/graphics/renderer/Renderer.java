package space.novium.nebula.graphics.renderer;

import space.novium.Game;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.core.resources.registration.FontEventRegister;
import space.novium.nebula.core.resources.registration.ItemEventRegister;
import space.novium.nebula.core.resources.registration.ResourceEventRegister;
import space.novium.nebula.core.resources.registration.TileEventRegister;
import space.novium.nebula.graphics.shader.Shader;
import space.novium.nebula.graphics.texture.TextureAtlasHandler;
import space.novium.nebula.world.tiles.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1024;
    private List<RenderBatch> batches;
    private static Shader boundShader;
    private TextureAtlasHandler handler;
    private static Renderer renderer;

    //These are the standards as to the locations of where to draw things. While these are suggestions, the renderer is built with these values in mind
    public static final int BACKGROUND_Z = 0;
    public static final int WORLD_START_Z = 1;
    public static final int HUD_Z = 16;
    public static final int GUI_Z = 32;
    public static final int TEXT_Z = 62;
    public static final int OVERLAY_Z = 63;

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
        ResourceEventRegister rer = new ResourceEventRegister(builder);
        rer.registerAll();
        TileEventRegister ter = new TileEventRegister(builder);
        ter.registerAll();

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
            batch.addSprite(spr);
            batches.add(batch);
            Collections.sort(batches);
        }
    }

    public void remove(GameObject gameObject){
        if(gameObject.getComponent(SpriteRenderer.class) != null){
            for(RenderBatch batch : batches){
                batch.remove(gameObject.getComponent(SpriteRenderer.class));
            }
        }
    }

    public void remove(SpriteRenderer spr){
        for(RenderBatch batch : batches){
            batch.remove(spr);
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
