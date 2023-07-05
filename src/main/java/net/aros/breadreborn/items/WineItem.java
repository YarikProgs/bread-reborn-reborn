package net.aros.breadreborn.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

import static net.aros.breadreborn.BreadReborn.BREAD_REBORN_TAB;
import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class WineItem extends Item {
    public WineItem() {
        super(new FabricItemSettings().maxCount(16).group(BREAD_REBORN_TAB).recipeRemainder(Items.GLASS_BOTTLE));
    }
    
    public @NotNull UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    
    public ItemStack finishUsing(ItemStack stack, World level, LivingEntity player) {
        if (player instanceof ServerPlayerEntity serverplayer) {
            Criteria.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (player instanceof PlayerEntity && !((PlayerEntity)player).getAbilities().creativeMode) {
            if (!level.isClient) {
                if (stack.getCount() > 1) ((PlayerEntity) player).giveItemStack(new ItemStack(getRecipeRemainder(), 1));
            }
            stack.decrement(1);
            if (!level.isClient) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 2, 1));

                int percent = new Random().nextInt(1, 100);

                if (percent <= 50) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10 * 20, 0));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10 * 20, 0));
                } else if (percent == 100) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 15 * 20, 0));
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 15 * 20, 0));
                }
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    public int getMaxUseTime(ItemStack p_42933_) {
        return 32;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        for (StatusEffectInstance effect : player.getActiveStatusEffects().values()) {
            if (effect.getEffectType() == StatusEffects.NAUSEA || effect.getEffectType() == StatusEffects.POISON)
                return TypedActionResult.pass(player.getStackInHand(hand));
        }
        return ItemUsage.consumeHeldItem(world, player, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        list.add(Text.translatable("desc." + MOD_ID + ".wine").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY)));
        super.appendTooltip(stack, world, list, context);
    }
}
