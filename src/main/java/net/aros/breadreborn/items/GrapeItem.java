package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

import java.util.List;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class GrapeItem extends Item {
    public GrapeItem() {
        super(new Item.Properties().tab(BreadReborn.BREAD_REBORN_TAB).stacksTo(64).food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.1F).build()));
    }

    @Override
    @NonnullDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("desc." + MOD_ID + ".grape").setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)));
        super.appendHoverText(stack, level, list, flag);
    }
}
