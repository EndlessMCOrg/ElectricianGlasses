package org.endlessmc.electricianglasses;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.endlessmc.electricianglasses.mixin.ChunkMapAccessor;
import org.endlessmc.electricianglasses.mixin.TrackedEntityAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = Electricianglasses.MODID)
public final class PowerGridWireVisibility {
    private static final Map<UUID, Boolean> LAST_STATE = new HashMap<>();

    private PowerGridWireVisibility() {
    }

    public static boolean isTarget(Entity entity) {
        ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        return id.getNamespace().equals("powergrid") && (id.getPath().equals("block_wire")
                || id.getPath().equals("hanging_wire") || id.getPath().equals("cord"));
    }

    public static boolean canSee(ServerPlayer player) {
        return GlassesEquipment.isWearing(player);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        boolean visible = canSee(player);
        Boolean previous = LAST_STATE.put(player.getUUID(), visible);
        if (previous != null && previous != visible) {
            refreshTrackedWires(player, visible);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        LAST_STATE.remove(event.getEntity().getUUID());
    }

    private static void refreshTrackedWires(ServerPlayer player, boolean visible) {
        if (!(player.serverLevel().getChunkSource() instanceof ServerChunkCache chunks)) {
            return;
        }
        ChunkMap map = chunks.chunkMap;
        Int2ObjectMap<TrackedEntityAccessor> entities = ((ChunkMapAccessor) map).electricianGlasses$getEntityMap();
        for (TrackedEntityAccessor accessor : entities.values()) {
            if (!isTarget(accessor.electricianGlasses$getEntity())) {
                continue;
            }
            if (visible) {
                accessor.electricianGlasses$updatePlayer(player);
            } else if (accessor.electricianGlasses$getSeenBy().remove(player.connection)) {
                player.connection.send(new ClientboundRemoveEntitiesPacket(accessor.electricianGlasses$getEntity().getId()));
            }
        }
    }
}
