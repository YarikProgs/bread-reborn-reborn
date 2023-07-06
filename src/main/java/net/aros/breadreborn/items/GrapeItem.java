package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class GrapeItem extends Item {
    public GrapeItem() {
        super(new FabricItemSettings().group(BreadReborn.BREAD_REBORN_TAB).maxCount(64).food(new FoodComponent.Builder().snack().hunger(4).saturationModifier(0.1F).build()));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> list, TooltipContext context) {
        list.add(Text.translatable("desc." + MOD_ID + ".grape").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY)));
        super.appendTooltip(stack, world, list, context);
    }
}
