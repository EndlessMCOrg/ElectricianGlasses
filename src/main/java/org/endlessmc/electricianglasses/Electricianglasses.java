package org.endlessmc.electricianglasses;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Electricianglasses.MODID)
public final class Electricianglasses {
    public static final String MODID = "electricianglasses";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<ArmorItem> ELECTRICIAN_GLASSES = ITEMS.register(
            "electrician_glasses",
            () -> new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))
    );

    public Electricianglasses(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
