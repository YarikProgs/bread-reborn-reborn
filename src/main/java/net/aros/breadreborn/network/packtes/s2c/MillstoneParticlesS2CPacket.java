package net.aros.breadreborn.network.packtes.s2c;

import net.aros.breadreborn.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class MillstoneParticlesS2CPacket {
    private final BlockPos pos;

    public MillstoneParticlesS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
    }

    public MillstoneParticlesS2CPacket(BlockPos pos) {
        this.pos = pos;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;
        if (level == null) return false;

        context.enqueueWork(() -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                level.addParticle(ModParticles.FLOUR_PARTICLE.get(), pos.getX()+.5F+random.nextFloat(-0.5F, 0.5F), pos.getY()+1.2F+random.nextFloat(-0.2F, 0F), pos.getZ()+.5F+random.nextFloat(-0.5F, 0.5F), 0, 0, 0);
            }
        });
        return true;
    }
}
