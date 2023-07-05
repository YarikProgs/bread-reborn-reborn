package net.aros.breadreborn.init;

import net.aros.breadreborn.BreadReborn;
import net.aros.breadreborn.items.CustomBread;
import net.aros.breadreborn.items.Item64;
import net.aros.breadreborn.items.WineItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.aros.breadreborn.BreadReborn.BREAD_REBORN_TAB;
import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModItems {
    public static final Item FLOUR = register("flour", new Item64());
    public static final Item CRIMSON_FLOUR = register("crimson_flour", new Item64());
    public static final Item WARPED_FLOUR = register("warped_flour", new Item64());
    public static final Item CHORUS_FLOUR = register("chorus_flour", new Item64());
    public static final Item SCULK_FLOUR = register("sculk_flour", new Item64());
    public static final Item GOLDEN_FLOUR = register("golden_flour", new Item64(Rarity.RARE));

    public static final Item BREAD_WITH_RAISINS = register("bread_with_raisins", new CustomBread(6, 0.8F));
    public static final Item CRIMSON_BREAD = register("crimson_bread", new CustomBread(4, 0.4F));
    public static final Item WARPED_BREAD = register("warped_bread", new CustomBread(4, 0.4F));
    public static final Item CHORUS_BREAD = register("chorus_bread", new CustomBread(5, 0.6F));
    public static final Item SCULK_BREAD = register("sculk_bread", new CustomBread(5, 0.6F));
    public static final Item GOLDEN_BREAD = register("golden_bread", new CustomBread(Rarity.RARE, (new FoodComponent.Builder()).hunger(5).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 1), 1.0F).alwaysEdible()));
    public static final Item ENCHANTED_GOLDEN_BREAD = register("enchanted_golden_bread", new CustomBread(Rarity.EPIC, (new FoodComponent.Builder()).hunger(5).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4), 1.0F).snack()) { @Override public boolean hasGlint(ItemStack stack) { return true; } });

    public static final Item WINE = register("wine", new WineItem());

    public static final Item MILLSTONE = register("millstone", new BlockItem(ModBlocks.MILLSTONE, new FabricItemSettings().group(BREAD_REBORN_TAB).maxCount(64)) { @Override public void appendTooltip(ItemStack p_41421_, @Nullable World p_41422_, List<Text> list, TooltipContext p_41424_) { list.add(Text.translatable("desc." + MOD_ID + ".millstone_pt1").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY))); list.add(Text.translatable("desc." + MOD_ID + ".millstone_pt2").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY)));; super.appendTooltip(p_41421_, p_41422_, list, p_41424_); }} );
    public static final Item GRAPE_VINES = register("grape_vines", new BlockItem(ModBlocks.GRAPE_VINES, new FabricItemSettings().group(BREAD_REBORN_TAB).maxCount(64)));
    public static final Item BLOOMING_GRAPE_VINES = register("blooming_grape_vines", new BlockItem(ModBlocks.BLOOMING_GRAPE_VINES, new FabricItemSettings().group(BREAD_REBORN_TAB).maxCount(64)));

    public static final Item GRAPE = register("grape", new Item(new FabricItemSettings().group(BreadReborn.BREAD_REBORN_TAB).maxCount(64).food(new FoodComponent.Builder().snack().hunger(4).saturationModifier(0.1F).build())) { @Override public void appendTooltip(ItemStack p_41421_, @Nullable World p_41422_, List<Text> list, TooltipContext p_41424_) { list.add(Text.translatable("desc." + MOD_ID + ".grape").setStyle(Style.EMPTY.withItalic(true).withColor(Formatting.GRAY))); super.appendTooltip(p_41421_, p_41422_, list, p_41424_); }});
    public static final Item RAISINS = register("raisins", new Item(new FabricItemSettings().group(BreadReborn.BREAD_REBORN_TAB).food(new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.8F).build()).maxCount(64)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), item);
    }
    
    public static void init() {}
}
