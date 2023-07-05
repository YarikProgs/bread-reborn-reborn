package net.aros.breadreborn.network;

import net.aros.breadreborn.network.packets.s2c.MillstoneParticleS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModMessages {
    public static final Identifier MILLSTONE_PARTICLE_SYNC = new Identifier(MOD_ID, "millstone_particle");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(MILLSTONE_PARTICLE_SYNC, MillstoneParticleS2CPacket::receive);
    }
}
