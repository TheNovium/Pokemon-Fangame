package space.novium.nebula.item.pokeball;

import space.novium.nebula.item.Item;
import space.novium.nebula.item.ItemTabLocation;

public class PokeballItem extends Item {
    public PokeballItem(){
        super(new Item.Properties()
                .setTab(ItemTabLocation.POKEBALLS));
    }
}
