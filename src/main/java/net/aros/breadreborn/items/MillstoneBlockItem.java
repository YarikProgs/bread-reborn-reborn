package net.aros.breadreborn.items;

import net.aros.breadreborn.init.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.aros.breadreborn.BreadReborn.BREAD_REBORN_TAB;
import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class MillstoneBlockItem extends BlockItem {
    public MillstoneBlockItem() {
        super(ModBlocks.MILLSTONE, new FabricItemSettings().group(BREAD_REBORN_TAB).maxCount(64));
    }

    @Override
    public void appendTooltip(ItemStack p_41421_, @Nullable World p_41422_, List<Text> list, TooltipContext p_41424_) {
        list.add(Text.translatable("desc." + MOD_ID + ".millstone_pt1").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY)));
        list.add(Text.translatable("desc." + MOD_ID + ".millstone_pt2").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY)));

        super.appendTooltip(p_41421_, p_41422_, list, p_41424_);
    }
}
