package net.aros.breadreborn.events;

import net.aros.breadreborn.init.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class LootTableModifyEvent implements LootTableEvents.Modify {
    @Override
    public void modifyLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id, LootTable.Builder tableBuilder, LootTableSource source) {
        if (!source.isBuiltin()) return;

        LootPool.Builder builder = new LootPool.Builder();

        if (id.equals(LootTables.DESERT_PYRAMID_CHEST) || id.equals(LootTables.SIMPLE_DUNGEON_CHEST) || id.equals(LootTables.END_CITY_TREASURE_CHEST)) {
            builder.with(ItemEntry.builder(ModItems.GOLDEN_FLOUR));
        }
        else if (id.equals(LootTables.ANCIENT_CITY_CHEST)) {
            builder.with(ItemEntry.builder(ModItems.SCULK_FLOUR));
            builder.with(ItemEntry.builder(ModItems.ENCHANTED_GOLDEN_BREAD));
        }


        tableBuilder.pool(builder);
    }
}
