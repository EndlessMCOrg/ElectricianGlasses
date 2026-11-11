package org.endlessmc.electricianglasses.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(targets = "net.minecraft.server.level.ChunkMap$TrackedEntity")
public interface TrackedEntityAccessor {
    @Accessor("entity")
    Entity electricianGlasses$getEntity();

    @Accessor("seenBy")
    Set<ServerPlayerConnection> electricianGlasses$getSeenBy();

    @Invoker("updatePlayer")
    void electricianGlasses$updatePlayer(ServerPlayer player);
}
