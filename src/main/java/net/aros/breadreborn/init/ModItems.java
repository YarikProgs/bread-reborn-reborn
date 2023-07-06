package net.aros.breadreborn.init;

import net.aros.breadreborn.BreadReborn;
import net.aros.breadreborn.items.CustomBread;
import net.aros.breadreborn.items.Item64;
import net.aros.breadreborn.items.WineItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.aros.breadreborn.BreadReborn.BREAD_REBORN_TAB;
import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModItems {
    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", Item64::new);
    public static final RegistryObject<Item> CRIMSON_FLOUR = ITEMS.register("crimson_flour", Item64::new);
    public static final RegistryObject<Item> WARPED_FLOUR = ITEMS.register("warped_flour", Item64::new);
    public static final RegistryObject<Item> CHORUS_FLOUR = ITEMS.register("chorus_flour", Item64::new);
    public static final RegistryObject<Item> SCULK_FLOUR = ITEMS.register("sculk_flour", Item64::new);
    public static final RegistryObject<Item> GOLDEN_FLOUR = ITEMS.register("golden_flour", () -> new Item64(Rarity.RARE));

    public static final RegistryObject<Item> BREAD_WITH_RAISINS = ITEMS.register("bread_with_raisins", () -> new CustomBread(6, 0.8F));
    public static final RegistryObject<Item> CRIMSON_BREAD = ITEMS.register("crimson_bread", () -> new CustomBread(4, 0.4F));
    public static final RegistryObject<Item> WARPED_BREAD = ITEMS.register("warped_bread", ()   -> new CustomBread(4, 0.4F));
    public static final RegistryObject<Item> CHORUS_BREAD = ITEMS.register("chorus_bread", ()   -> new CustomBread(5, 0.6F));
    public static final RegistryObject<Item> SCULK_BREAD = ITEMS.register("sculk_bread", ()     -> new CustomBread(5, 0.6F));
    public static final RegistryObject<Item> GOLDEN_BREAD = ITEMS.register("golden_bread", ()   -> new CustomBread(Rarity.RARE, (new FoodProperties.Builder()).nutrition(5).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F).alwaysEat()));
    public static final RegistryObject<Item> ENCHANTED_GOLDEN_BREAD = ITEMS.register("enchanted_golden_bread", ()   -> new CustomBread(Rarity.EPIC, (new FoodProperties.Builder()).nutrition(5).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 2), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 4), 1.0F).alwaysEat()) { @Override public boolean isFoil(ItemStack stack) { return true; } });

    public static final RegistryObject<Item> WINE = ITEMS.register("wine", WineItem::new);

    public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone", () -> new BlockItem(ModBlocks.MILLSTONE.get(), new Item.Properties().tab(BREAD_REBORN_TAB).stacksTo(64)) { @Override public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> list, TooltipFlag p_41424_) { list.add(Component.translatable("desc." + MOD_ID + ".millstone").setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY))); super.appendHoverText(p_41421_, p_41422_, list, p_41424_); }} );
    public static final RegistryObject<Item> GRAPE_VINES = ITEMS.register("grape_vines", () -> new BlockItem(ModBlocks.GRAPE_VINES.get(), new Item.Properties().tab(BREAD_REBORN_TAB).stacksTo(64)));
    public static final RegistryObject<Item> BLOOMING_GRAPE_VINES = ITEMS.register("blooming_grape_vines", () -> new BlockItem(ModBlocks.BLOOMING_GRAPE_VINES.get(), new Item.Properties().tab(BREAD_REBORN_TAB).stacksTo(64)));

    public static final RegistryObject<Item> GRAPE = ITEMS.register("grape", () -> new Item(new Item.Properties().tab(BreadReborn.BREAD_REBORN_TAB).stacksTo(64).food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.1F).build())) { @Override public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> list, TooltipFlag p_41424_) { list.add(Component.translatable("desc." + MOD_ID + ".grape").setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY))); super.appendHoverText(p_41421_, p_41422_, list, p_41424_); }});
    public static final RegistryObject<Item> RAISINS = ITEMS.register("raisins", () -> new Item(new Item.Properties().tab(BreadReborn.BREAD_REBORN_TAB).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.8F).build()).stacksTo(64)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
