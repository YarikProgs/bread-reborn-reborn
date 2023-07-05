package net.aros.breadreborn.init;

import net.aros.breadreborn.blocks.BloomingGrapeVines;
import net.aros.breadreborn.blocks.GrapeVines;
import net.aros.breadreborn.blocks.MillstoneBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModBlocks {
    public static final Block MILLSTONE = register("millstone", new MillstoneBlock());
    public static final Block GRAPE_VINES = register("grape_vines", new GrapeVines());
    public static final Block BLOOMING_GRAPE_VINES = register("blooming_grape_vines", new BloomingGrapeVines());
    
    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
    }

    public static void init() {}
}
