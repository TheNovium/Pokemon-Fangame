package space.novium.nebula.world.tiles;

import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;

import java.util.Random;

public class TallGrass extends Tile{
    private boolean walked;
    private int cooldown;

    public TallGrass(){
        super();
        walked = false;
        cooldown = 60;
    }

    @Override
    public void tick(Level level, Player player, Random random) {
        if(player.getPos().equals(getPosition())){
            if(random.nextInt(100) == 0){
                walked = true;
                System.out.println("A random encounter would start now");
                cooldown = 60;
            }
        } else {
            cooldown--;
            walked = cooldown < 0;
        }
    }
}
