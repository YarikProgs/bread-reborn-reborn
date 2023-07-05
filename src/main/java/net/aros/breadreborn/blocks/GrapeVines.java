package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NonnullDefault;

public class GrapeVines extends VineBlock {
    public GrapeVines() {
        super(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().ticksRandomly().strength(0.2F).sounds(BlockSoundGroup.VINE));
    }

    public GrapeVines(FabricBlockSettings properties) {
        super(properties);
    }

    @Override
    @NonnullDefault
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitRes) {
        if (!world.isClient) {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == Items.BONE_MEAL) {
                stack.decrement(1);
                world.setBlockState(pos, ModBlocks.BLOOMING_GRAPE_VINES
                        .getDefaultState()
                        .with(UP, state.get(UP))
                        .with(NORTH, state.get(NORTH))
                        .with(EAST, state.get(EAST))
                        .with(SOUTH, state.get(SOUTH))
                        .with(WEST, state.get(WEST)), 2);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    @NonnullDefault
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random source) {
        super.randomTick(state, level, pos, source);

        if (source.nextInt(10) == 0) {
            level.setBlockState(pos, ModBlocks.BLOOMING_GRAPE_VINES
                    .getDefaultState()
                    .with(UP, state.get(UP))
                    .with(NORTH, state.get(NORTH))
                    .with(EAST, state.get(EAST))
                    .with(SOUTH, state.get(SOUTH))
                    .with(WEST, state.get(WEST)), 2);
        }

    }
}
