package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class Item64 extends Item {
    public Item64() {
        super(new FabricItemSettings().maxCount(64).group(BreadReborn.BREAD_REBORN_TAB));
    }

    public Item64(Rarity rarity) {
        super(new FabricItemSettings().maxCount(64).group(BreadReborn.BREAD_REBORN_TAB).rarity(rarity));
    }
}
