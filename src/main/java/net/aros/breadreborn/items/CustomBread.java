package net.aros.breadreborn.items;

import net.aros.breadreborn.BreadReborn;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.NotNull;

public class CustomBread extends Item {
    public CustomBread(int nutrition, float saturationMod) {
        super(new FabricItemSettings()
                .group(BreadReborn.BREAD_REBORN_TAB)
                .maxCount(64)
                .food(new FoodComponent.Builder()
                        .alwaysEdible()
                        .hunger(nutrition)
                        .saturationModifier(saturationMod)
                        .build()
                )
        );
    }

    public CustomBread(int nutrition, float saturationMod, StatusEffectInstance effect) {
        this(Rarity.COMMON, new FoodComponent.Builder().hunger(nutrition).saturationModifier(saturationMod).statusEffect(effect, 1.0F).alwaysEdible());
    }

    public CustomBread(int nutrition, float saturationMod, StatusEffectInstance effect, StatusEffectInstance effect2) {
        this(Rarity.COMMON, new FoodComponent.Builder().hunger(nutrition).saturationModifier(saturationMod).statusEffect(effect, 1.0F).statusEffect(effect2, 1.0F).alwaysEdible());
    }

    public CustomBread(Rarity rarity, FoodComponent.@NotNull Builder builder) {
        super(new FabricItemSettings()
                .group(BreadReborn.BREAD_REBORN_TAB)
                .rarity(rarity)
                .maxCount(64)
                .food(builder.build())
        );
    }


}
