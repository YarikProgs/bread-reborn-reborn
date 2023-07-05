package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.network.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MillstoneBlock extends Block {
    private static final VoxelShape shape = Block.createCuboidShape(0, 0, 0, 16, 14, 16);

    public MillstoneBlock() {
        super(FabricBlockSettings.copy(Blocks.STONE).nonOpaque());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ActionResult onUse(@NotNull BlockState state, @NotNull World world, @NotNull BlockPos pos,
                                     @NotNull PlayerEntity player, @NotNull Hand hand, @NotNull BlockHitResult hitRes)
    {
        Map<Item, Item> RECIPES = Map.of(
                Items.WHEAT,          ModItems.FLOUR,
                Items.CRIMSON_FUNGUS, ModItems.CRIMSON_FLOUR,
                Items.WARPED_FUNGUS,  ModItems.WARPED_FLOUR,
                Items.CHORUS_FRUIT,   ModItems.CHORUS_FLOUR,
                Items.SCULK,          ModItems.SCULK_FLOUR
        );

        ItemStack mainStack = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack offStack = player.getStackInHand(Hand.OFF_HAND);

        if (!world.isClient && offStack.getItem() == Items.STICK && hand == Hand.MAIN_HAND) {
            for (Map.Entry<Item, Item> entry : RECIPES.entrySet()) {
                Item ingredient = entry.getKey();
                Item result = entry.getValue();

                if (mainStack.getItem() == ingredient) {
                    mainStack.decrement(1);
                    player.giveItemStack(new ItemStack(result, 1));
                    world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.MILLSTONE_PARTICLE_SYNC, PacketByteBufs.create().writeBlockPos(pos));
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        }

        return ActionResult.PASS;
    }
}
