package space.novium.nebula.world.level;

import space.novium.nebula.KeyInput;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.world.ChunkLoader;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Level implements Runnable {
    private final Thread thread; //This thread will not render anything, it'll only be used for calculations
    private final UUID levelUUID = UUID.randomUUID();
    private String region = "";
    private ChunkLoader loader;
    private Player player;

    private List<Tile> tileList;

    private final ILevelScene scene;

    public final Random random = new Random();

    public Level(ILevelScene scene){
        this.scene = scene;
        this.thread = new Thread(this, levelUUID.toString());
        this.thread.start();
        player = new Player();
        tileList = new ArrayList<>();
        loader = new ChunkLoader(new ResourceLocation("chunks/test"), scene, this);
    }

    public Player getPlayer(){
        return player;
    }

    public void setRegion(String region) {
        this.region = region;
        scene.setRegion(region);
    }

    public void addTile(Tile tile){
        tileList.add(tile);
        scene.addTile(tile);
    }

    public String getRegion(){
        return region;
    }

    public void tick(){
        Camera camera = scene.getCamera();
        float maxSpeed = player.getSpeed();
        if(KeyInput.isHeld(KeyInput.MOVE_UP)){
            camera.move(0.0f, maxSpeed * -1.0f);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_DOWN)){
            camera.move(0.0f, maxSpeed);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_RIGHT)){
            camera.move(maxSpeed * -1.0f, 0.0f);
        }
        if(KeyInput.isHeld(KeyInput.MOVE_LEFT)){
            camera.move(maxSpeed, 0.0f);
        }
    }

    @Override
    public void run() {
    }

    public void cleanup(){
        try{
            thread.join();
        } catch(Exception e){
            System.err.println("Failed to stop thread! Fatal error, cannot continue.");
            System.exit(-13731);
        }
    }
}
