package space.novium.nebula.world.level;

import java.util.Random;
import java.util.UUID;

public class Level implements Runnable {
    private final Thread thread; //This thread will not render anything, it'll only be used for calculations
    private boolean running;
    private final UUID levelUUID = UUID.randomUUID();
    private String region = "";

    private final ILevelScene scene;

    public final Random random = new Random();

    public Level(ILevelScene scene){
        this.scene = scene;
        this.thread = new Thread(this, levelUUID.toString());
        this.thread.start();
        running = true;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion(){
        return region;
    }

    public void tick(){

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
