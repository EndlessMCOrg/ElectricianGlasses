package org.endlessmc.electricianglasses;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;

import java.lang.reflect.Method;
import java.util.Optional;

public final class GlassesEquipment {
    private static Method getCuriosInventory;
    private static Method findFirstCurio;
    private static boolean curiosLookupFailed;

    private GlassesEquipment() {
    }

    public static boolean isWearing(Player player) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(Electricianglasses.ELECTRICIAN_GLASSES.get())) {
            return true;
        }
        return ModList.get().isLoaded("curios") && isWearingCurios(player);
    }

    private static boolean isWearingCurios(Player player) {
        if (curiosLookupFailed) {
            return false;
        }
        try {
            if (getCuriosInventory == null) {
                Class<?> api = Class.forName("top.theillusivec4.curios.api.CuriosApi");
                getCuriosInventory = api.getMethod("getCuriosInventory", LivingEntity.class);
            }
            Optional<?> inventory = (Optional<?>) getCuriosInventory.invoke(null, player);
            if (inventory.isEmpty()) {
                return false;
            }
            Object handler = inventory.get();
            if (findFirstCurio == null) {
                findFirstCurio = handler.getClass().getMethod("findFirstCurio", Item.class);
            }
            return ((Optional<?>) findFirstCurio.invoke(handler, Electricianglasses.ELECTRICIAN_GLASSES.get())).isPresent();
        } catch (ReflectiveOperationException | RuntimeException ignored) {
            curiosLookupFailed = true;
            return false;
        }
    }
}
