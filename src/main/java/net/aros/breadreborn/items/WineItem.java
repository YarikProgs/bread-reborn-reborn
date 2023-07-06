package net.aros.breadreborn.items;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

import java.util.List;
import java.util.Random;

import static net.aros.breadreborn.BreadReborn.BREAD_REBORN_TAB;
import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class WineItem extends Item {
    public WineItem() {
        super(new Item.Properties().stacksTo(16).tab(BREAD_REBORN_TAB).craftRemainder(Items.GLASS_BOTTLE));
    }

    @NonnullDefault
    @NotNull
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @NonnullDefault
    @NotNull
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity player) {
        if (!level.isClientSide) player.curePotionEffects(stack); // FORGE - move up so stack.shrink does not turn stack into air
        if (player instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (player instanceof Player && !((Player)player).getAbilities().instabuild) {
            if (!level.isClientSide) {
                if (stack.getCount() > 1) ((Player) player).addItem(getCraftingRemainingItem(stack));
            }
            stack.shrink(1);
            if (!level.isClientSide) {
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 2, 1));

                int percent = new Random().nextInt(1, 100);

                if (percent <= 50) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 0));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10 * 20, 0));
                } else if (percent == 100) {
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 15 * 20, 0));
                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15 * 20, 0));
                }
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }
    
    @NonnullDefault
    public int getUseDuration(ItemStack stack) {
        return 32;
    }
    
    @NonnullDefault
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        for (MobEffectInstance effect : player.getActiveEffects()) {
            if (effect.getEffect() == MobEffects.CONFUSION || effect.getEffect() == MobEffects.POISON)
                return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    @NonnullDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("desc." + MOD_ID + ".wine").setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)));
        super.appendHoverText(stack, level, list, flag);
    }
}
