package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NonnullDefault;

public class BloomingGrapeVines extends GrapeVines {
    public BloomingGrapeVines() {
        super(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().randomTicks().strength(0.2F).sound(SoundType.VINE));
    }

    @Override
    @NotNull
    @NonnullDefault
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
        if (!level.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem() == Items.SHEARS) {
                Block.popResource(level, pos, new ItemStack(ModItems.GRAPE.get(), 1));
                float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, f);
                level.setBlock(pos, ModBlocks.GRAPE_VINES.get()
                        .defaultBlockState()
                        .setValue(UP, state.getValue(UP))
                        .setValue(NORTH, state.getValue(NORTH))
                        .setValue(EAST, state.getValue(EAST))
                        .setValue(SOUTH, state.getValue(SOUTH))
                        .setValue(WEST, state.getValue(WEST)), 2);
                stack.hurtAndBreak(1, player, shears -> {});
                System.out.println(stack.isDamageableItem());
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
