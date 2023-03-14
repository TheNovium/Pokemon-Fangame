package space.novium.nebula.core.components;

import space.novium.nebula.core.Component;
import space.novium.nebula.core.GameObject;
import space.novium.nebula.graphics.texture.Sprite;
import space.novium.nebula.graphics.texture.TextureAtlasType;

public class SpriteRenderer extends Component {
    private Sprite sprite;

    public SpriteRenderer(TextureAtlasType type){
        sprite = new Sprite(type);
    }

    @Override
    public void setGameObject(GameObject obj) {
        super.setGameObject(obj);
    }

    @Override
    public GameObject getGameObject() {
        return super.getGameObject();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {

    }
}
