package space.novium.nebula.core;

import space.novium.nebula.graphics.renderer.Transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameObject {
    private String name;
    private List<Component> components;
    private Transform transform;

    public GameObject(String name){
        this.name = name;
        this.components = Collections.synchronizedList(new ArrayList<>());
        this.transform = new Transform();
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

    public Transform getTransform() {
        return transform;
    }
}
