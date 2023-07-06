package net.aros.breadreborn.blocks;

import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.network.ModMessages;
import net.aros.breadreborn.network.packtes.s2c.MillstoneParticlesS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class MillstoneBlock extends Block {
    private static final VoxelShape shape = Block.box(0, 0, 0, 16, 14, 16);

    public MillstoneBlock() {
        super(Properties.copy(Blocks.STONE).noOcclusion());
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return shape;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos,
                                          @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitRes)
    {
        Map<Item, Item> RECIPES = Map.of(
                Items.WHEAT,          ModItems.FLOUR.get(),
                Items.CRIMSON_FUNGUS, ModItems.CRIMSON_FLOUR.get(),
                Items.WARPED_FUNGUS,  ModItems.WARPED_FLOUR.get(),
                Items.CHORUS_FRUIT,   ModItems.CHORUS_FLOUR.get(),
                Items.SCULK,          ModItems.SCULK_FLOUR.get()
        );

        ItemStack mainStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offStack = player.getItemInHand(InteractionHand.OFF_HAND);

        if (!world.isClientSide && offStack.getItem() == Items.STICK && hand == InteractionHand.MAIN_HAND) {
            for (Map.Entry<Item, Item> entry : RECIPES.entrySet()) {
                Item ingredient = entry.getKey();
                Item result = entry.getValue();

                if (mainStack.getItem() == ingredient) {
                    mainStack.shrink(1);
                    player.addItem(new ItemStack(result, 1));
                    world.playSound(null, player.getOnPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
                    ModMessages.sendToPlayer(new MillstoneParticlesS2CPacket(pos), (ServerPlayer) player);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.FAIL;
        }

        return InteractionResult.PASS;
    }
}
