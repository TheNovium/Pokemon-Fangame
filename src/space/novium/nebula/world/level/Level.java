package space.novium.nebula.world.level;

import space.novium.nebula.KeyInput;
import space.novium.nebula.core.resources.ResourceLocation;
import space.novium.nebula.graphics.Camera;
import space.novium.nebula.world.ChunkLoader;
import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.tiles.Tile;
import space.novium.utils.math.Vector2f;

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

    public void addTile(Tile tile, int z){
        tileList.add(tile);
        scene.addTile(tile, z);
    }

    public String getRegion(){
        return region;
    }

    public void tick(){
        Camera camera = scene.getCamera();
        player.tick(this);
        float maxSpeed = player.getSpeed();
        Vector2f cameraPos = camera.getPosition();
        Vector2f playerPos = player.getPosition();
        float dx = playerPos.getX() - cameraPos.getX();
        if(dx != 0.0f){
            dx /= Math.abs(dx);
            dx *= maxSpeed;
        }
        float dy = playerPos.getY() - cameraPos.getY();
        if(dy != 0.0f){
            dy /= Math.abs(dy);
            dy *= maxSpeed;
        }
        float dp = cameraPos.getDifference(playerPos); //Eventually I'll use this to do a fancy camera

        camera.move(dx, dy);
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
