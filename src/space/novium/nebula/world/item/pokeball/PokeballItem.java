package space.novium.nebula.world.item.pokeball;

import space.novium.nebula.world.item.Item;
import space.novium.nebula.world.item.ItemTabLocation;

public class PokeballItem extends Item {
    public PokeballItem(){
        super(new Item.Properties()
                .setTab(ItemTabLocation.POKEBALLS));
    }
}
