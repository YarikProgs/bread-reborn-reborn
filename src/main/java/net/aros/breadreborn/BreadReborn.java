package net.aros.breadreborn;

import net.aros.breadreborn.events.LootTableModifyEvent;
import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModEffects;
import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.init.ModParticles;
import net.aros.breadreborn.network.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
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
        ModEffects.init();

        LootTableEvents.MODIFY.register(new LootTableModifyEvent());

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(new TradeOffers.SellItemFactory(ModItems.GRAPE, 1, 3, 10, 2));
            factories.add(new TradeOffers.SellItemFactory(ModItems.RAISINS, 1, 2, 10, 3));
            factories.add(new TradeOffers.SellItemFactory(ModItems.GRAPE_VINES, 10, 1, 10, 5));
            factories.add(new TradeOffers.SellItemFactory(ModItems.WINE, 5, 1,6,  7));
        });

        ModMessages.registerC2SPackets();
    }
}
