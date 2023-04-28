package space.novium.nebula.world.item;

import space.novium.nebula.core.GameObject;
import space.novium.nebula.core.resources.ResourceLocation;

public class Item {
    protected final ItemTabLocation category;
    private final Rarity rarity;
    private final int stackSize;
    private final boolean infinite;
    private String name;
    protected GameObject gameObject;

    public Item(Properties properties){
        this.category = properties.category;
        this.rarity = properties.rarity;
        this.stackSize = properties.stack;
        this.infinite = properties.infinite;
        this.gameObject = new GameObject(getRegistryName());
    }

    public Item asItem(){
        return this;
    }

    public int getUseDuration(){
        return 1;
    }

    public String getRegistryName(){
        return name;
    }

    public void setRegistryName(ResourceLocation loc){
        setRegistryName(loc.toString());
    }

    public void setRegistryName(String name){
        if(getRegistryName() != null){
            System.err.println("Unable to set registry name to " + name + " because item is already registered as " + getRegistryName());
        } else {
            this.name = name;
        }
    }

    public Rarity getRarity() {
        return rarity;
    }

    protected GameObject getGameObject() {
        return gameObject;
    }

    public static class Properties {
        int stack = 999;
        ItemTabLocation category;
        Rarity rarity;
        private boolean infinite = false;

        public Properties infinite(){
            infinite = true;
            stack = 1;
            return this;
        }

        public Properties setStackSize(int stack){
            this.stack = Math.max(1, stack);
            infinite = false;
            return this;
        }

        public Properties setTab(ItemTabLocation tab){
            this.category = tab;
            return this;
        }
    }
}
