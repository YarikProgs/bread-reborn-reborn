package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class Item64 extends Item {
    public Item64() {
        this(false);
    }

    public Item64(boolean hasDescription) {
        super(new Properties().stacksTo(64).tab(BreadReborn.BREAD_REBORN_TAB));
    }

    public Item64(Rarity rarity) {
        super(new Properties().stacksTo(64).tab(BreadReborn.BREAD_REBORN_TAB).rarity(rarity));

    }
}
