package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NonnullDefault;

public class GrapeVines extends VineBlock {
    public GrapeVines() {
        super(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().randomTicks().strength(0.2F).sound(SoundType.VINE));
    }

    public GrapeVines(Properties properties) {
        super(properties);
    }

    @Override
    @NonnullDefault
    @NotNull
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem() == Items.BONE_MEAL) {
                stack.shrink(1);
                world.setBlock(pos, ModBlocks.BLOOMING_GRAPE_VINES.get()
                        .defaultBlockState()
                        .setValue(UP, state.getValue(UP))
                        .setValue(NORTH, state.getValue(NORTH))
                        .setValue(EAST, state.getValue(EAST))
                        .setValue(SOUTH, state.getValue(SOUTH))
                        .setValue(WEST, state.getValue(WEST)), 2);
                world.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    @NonnullDefault
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        super.randomTick(state, level, pos, source);

        if (source.nextInt(10) == 0) {
            level.setBlock(pos, ModBlocks.BLOOMING_GRAPE_VINES.get()
                    .defaultBlockState()
                    .setValue(UP, state.getValue(UP))
                    .setValue(NORTH, state.getValue(NORTH))
                    .setValue(EAST, state.getValue(EAST))
                    .setValue(SOUTH, state.getValue(SOUTH))
                    .setValue(WEST, state.getValue(WEST)), 2);
        }

    }
}
