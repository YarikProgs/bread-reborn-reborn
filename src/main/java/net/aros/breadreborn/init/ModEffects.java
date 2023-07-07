package net.aros.breadreborn.init;

import net.aros.breadreborn.effects.ProtectiveShellEffect;
import net.aros.breadreborn.effects.RageEffect;
import net.aros.breadreborn.effects.RootSolesEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModEffects {
    public static final StatusEffect ROOT_SOLES = register("root_soles",
            new RootSolesEffect(StatusEffectCategory.BENEFICIAL, Formatting.DARK_AQUA.getColorValue()));

    public static final StatusEffect RAGE = register("rage",
            new RageEffect(StatusEffectCategory.BENEFICIAL, Formatting.DARK_RED.getColorValue()));

    public static final StatusEffect PROTECTIVE_SHELL = register("protective_shell",
            new ProtectiveShellEffect(StatusEffectCategory.BENEFICIAL, Formatting.LIGHT_PURPLE.getColorValue()));

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, name), effect);
    }

    public static void init() {}
}
