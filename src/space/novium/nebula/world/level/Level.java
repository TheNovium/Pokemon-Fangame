package space.novium.nebula.world.level;

import java.util.Random;
import java.util.UUID;

public class Level implements Runnable {
    private final Thread thread; //This thread will not render anything, it'll only be used for calculations
    private final UUID levelUUID = UUID.randomUUID();

    private final ILevelScene scene;

    public final Random random = new Random();

    public Level(ILevelScene scene){
        this.scene = scene;
        this.thread = new Thread(this, levelUUID.toString());
        this.thread.start();
    }

    public void tick(){

    }

    @Override
    public void run() {
    }
}
