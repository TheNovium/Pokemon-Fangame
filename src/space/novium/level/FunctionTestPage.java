package space.novium.level;

import space.novium.nebula.graphics.Camera;
import space.novium.nebula.world.level.ILevelScene;
import space.novium.nebula.world.level.Level;

public class FunctionTestPage extends Scene implements ILevelScene {
    private Level level;

    public FunctionTestPage(){
        camera = new Camera();
    }

    @Override
    public void init() {
        level = new Level(this);
    }

    @Override
    public void update(float dt) {
        level.tick();
    }

    @Override
    public void render() {

    }
}
