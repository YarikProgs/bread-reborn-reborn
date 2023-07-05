package net.aros.breadreborn;

import net.aros.breadreborn.events.LootTableModifyEvent;
import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.init.ModParticles;
import net.aros.breadreborn.network.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BreadReborn implements ModInitializer {
    public static final String MOD_ID = "breadreborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ItemGroup BREAD_REBORN_TAB = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "breadreborn"),
            () -> new ItemStack(ModItems.WARPED_FLOUR));

    @Override
    public void onInitialize() {
        LOGGER.info("Hello bread reborn!");

        ModItems.init();
        ModBlocks.init();
        ModParticles.init();

        LootTableEvents.MODIFY.register(new LootTableModifyEvent());

        ModMessages.registerC2SPackets();
    }
}
