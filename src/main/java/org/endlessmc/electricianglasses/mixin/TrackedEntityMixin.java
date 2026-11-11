package org.endlessmc.electricianglasses.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.endlessmc.electricianglasses.PowerGridWireVisibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.server.level.ChunkMap$TrackedEntity")
public final class TrackedEntityMixin {
    @Inject(method = "updatePlayer", at = @At("HEAD"), cancellable = true)
    private void electricianGlasses$hidePowerGridWires(ServerPlayer player, CallbackInfo ci) {
        TrackedEntityAccessor accessor = (TrackedEntityAccessor) (Object) this;
        if (PowerGridWireVisibility.isTarget(accessor.electricianGlasses$getEntity())
                && !PowerGridWireVisibility.canSee(player)) {
            ci.cancel();
        }
    }
}
