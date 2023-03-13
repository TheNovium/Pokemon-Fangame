package space.novium.level;

import space.novium.nebula.graphics.shader.Shaders;

public class IntroScene extends Scene {
    public IntroScene(){}

    public void init(){
    }

    @Override
    public void update(float dt) {
        Shaders.DEFAULT.enable();
    }
}
