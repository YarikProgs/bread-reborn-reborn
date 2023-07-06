package net.aros.breadreborn.network.packets.s2c;

import net.aros.breadreborn.init.ModParticles;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class MillstoneParticleS2CPacket {
    @SuppressWarnings("unused")
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        Random random = new Random();
        BlockPos pos = buf.readBlockPos();

        if (client.world == null || pos == null) return;
        for (int i = 0; i < 10; i++) {
            client.world.addParticle(ModParticles.FLOUR_PARTICLE, pos.getX() + .5F + random.nextFloat(-0.5F, 0.5F), pos.getY() + 1.2F + random.nextFloat(-0.2F, 0F), pos.getZ() + .5F + random.nextFloat(-0.5F, 0.5F), 0, 0, 0);
        }
    }
}
