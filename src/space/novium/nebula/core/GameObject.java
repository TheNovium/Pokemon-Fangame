package space.novium.nebula.core;

import space.novium.nebula.core.components.SpriteRenderer;
import space.novium.nebula.graphics.renderer.Transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameObject {
    private String name;
    private List<Component> components;
    private Transform transform;

    public GameObject(String name){
        this(name, new Transform());
    }

    public GameObject(String name, Transform transform){
        this.name = name;
        this.components = Collections.synchronizedList(new ArrayList<>());
        this.transform = transform;
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try {
                    return componentClass.cast(c);
                } catch(Exception e){
                    System.out.println("Failed to cast class!");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c){
        this.components.add(c);
        c.setGameObject(this);
    }

    public void tick(){
        for (Component component : components) {
            component.tick();
        }
    }

    public void start(){
        for(Component component : components){
            component.start();
        }
    }

    public void translate(float dx, float dy){
        transform.move(dx, dy);
        setSprDirty();
    }

    public void setPosition(float x, float y){
        transform.setPosition(x, y);
        setSprDirty();
    }

    private void setSprDirty(){
        if(getComponent(SpriteRenderer.class) != null){
            SpriteRenderer spr = getComponent(SpriteRenderer.class);
            spr.setDirty();
        }
    }

    public Transform getTransform() {
        return transform;
    }
}
