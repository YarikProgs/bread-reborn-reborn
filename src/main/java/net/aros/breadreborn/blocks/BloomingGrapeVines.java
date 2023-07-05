package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NonnullDefault;

public class BloomingGrapeVines extends GrapeVines {
    public BloomingGrapeVines() {
        super(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().ticksRandomly().strength(0.2F).sounds(BlockSoundGroup.VINE));
    }

    @Override
    @NotNull
    @NonnullDefault
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitRes) {
        if (!world.isClient) {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == Items.SHEARS) {
                Block.dropStack(world, pos, new ItemStack(ModItems.GRAPE, 1));

                world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                world.setBlockState(pos, ModBlocks.GRAPE_VINES
                        .getDefaultState()
                        .with(UP, state.get(UP))
                        .with(NORTH, state.get(NORTH))
                        .with(EAST, state.get(EAST))
                        .with(SOUTH, state.get(SOUTH))
                        .with(WEST, state.get(WEST)), 2);
                stack.damage(1, player, shears -> {});
                System.out.println(stack.isDamageable());
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
