package space.novium.nebula.core;

public abstract class Component {
    private GameObject gameObject = null;

    public void setGameObject(GameObject obj){
        this.gameObject = obj;
    }

    public GameObject getGameObject(){
        return gameObject;
    }

    public void start(){}

    public abstract void tick();
}
