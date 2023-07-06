package net.aros.breadreborn.init;

import net.aros.breadreborn.BreadReborn;
import net.aros.breadreborn.blocks.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BreadReborn.MOD_ID);

    public static final RegistryObject<Block> MILLSTONE = register("millstone", MillstoneBlock::new);
    public static final RegistryObject<Block> GRAPE_VINES = register("grape_vines", GrapeVines::new);
    public static final RegistryObject<Block> BLOOMING_GRAPE_VINES = register("blooming_grape_vines", BloomingGrapeVines::new);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
