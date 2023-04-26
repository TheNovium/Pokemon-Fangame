package space.novium.nebula.world.tiles;

import space.novium.nebula.world.entity.Player;
import space.novium.nebula.world.level.Level;
import space.novium.utils.math.Vector4f;

public class RadiationBarrel extends Tile {
    public RadiationBarrel(){
        super();
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        System.out.println("Barrel created at " + getPosition().toString());
    }

    @Override
    public boolean collide() {
        return true;
    }

    @Override
    public Vector4f getHitBox() {
        return new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
    }
}
