package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class CustomBread extends Item {
    public CustomBread(int nutrition, float saturationMod) {
        super(new Properties()
                .tab(BreadReborn.BREAD_REBORN_TAB)
                .stacksTo(64)
                .food(new FoodProperties.Builder()
                        .alwaysEat()
                        .nutrition(nutrition)
                        .saturationMod(saturationMod)
                        .build()
                )
        );
    }

    public CustomBread(int nutrition, float saturationMod, MobEffectInstance effect) {
        this(Rarity.COMMON, new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod).effect(() -> effect, 1.0F).alwaysEat());
    }

    public CustomBread(int nutrition, float saturationMod, MobEffectInstance effect1, MobEffectInstance effect2) {
        this(Rarity.COMMON, new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod).effect(() -> effect1, 1.0F).effect(() -> effect2, 1.0F).alwaysEat());
    }

    public CustomBread(Rarity rarity, FoodProperties.@NotNull Builder builder) {
        super(new Properties()
                .tab(BreadReborn.BREAD_REBORN_TAB)
                .rarity(rarity)
                .stacksTo(64)
                .food(builder.build())
        );
    }


}
