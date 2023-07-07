package net.aros.breadreborn.init;

import net.aros.breadreborn.effects.ProtectiveShellEffect;
import net.aros.breadreborn.effects.RageEffect;
import net.aros.breadreborn.effects.RootSolesEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModEffects {
    private static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    public static final RegistryObject<MobEffect> ROOT_SOLES = MOD_EFFECTS.register("root_soles",
            () -> new RootSolesEffect(MobEffectCategory.BENEFICIAL, ChatFormatting.DARK_AQUA.getColor()));

    public static final RegistryObject<MobEffect> RAGE = MOD_EFFECTS.register("rage",
            () -> new RageEffect(MobEffectCategory.BENEFICIAL, ChatFormatting.DARK_RED.getColor()));

    public static final RegistryObject<MobEffect> PROTECTIVE_SHELL = MOD_EFFECTS.register("protective_shell",
            () -> new ProtectiveShellEffect(MobEffectCategory.BENEFICIAL, ChatFormatting.LIGHT_PURPLE.getColor()));

    public static void register(IEventBus bus) {
        MOD_EFFECTS.register(bus);
    }
}
