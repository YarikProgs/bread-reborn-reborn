package net.aros.breadreborn;

import com.mojang.logging.LogUtils;
import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.loot.ModLootModifiers;
import net.aros.breadreborn.network.ModMessages;
import net.aros.breadreborn.particle.ModParticles;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;


@Mod(BreadReborn.MOD_ID)
public class BreadReborn {
    public static final String MOD_ID = "breadreborn";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab BREAD_REBORN_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.WARPED_FLOUR.get());
        }
    };

    public BreadReborn() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);
        ModLootModifiers.register(bus);
        ModParticles.register(bus);

        bus.addListener(this::clientSetup);
        bus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("all")
    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAPE_VINES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOOMING_GRAPE_VINES.get(), RenderType.cutout());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }
}
