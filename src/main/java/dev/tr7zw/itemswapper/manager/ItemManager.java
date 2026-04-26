package dev.tr7zw.itemswapper.manager;

import dev.tr7zw.itemswapper.*;
import dev.tr7zw.itemswapper.api.*;
import dev.tr7zw.itemswapper.api.client.*;
import dev.tr7zw.itemswapper.manager.itemgroups.*;
import dev.tr7zw.itemswapper.packets.*;
import dev.tr7zw.itemswapper.util.*;
import dev.tr7zw.itemswapper.util.ItemUtil;
import dev.tr7zw.transition.loader.networking.*;
import dev.tr7zw.transition.mc.*;
import lombok.*;
import net.minecraft.client.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.concurrent.atomic.*;

@RequiredArgsConstructor
public class ItemManager {

    private final Minecraft minecraft = Minecraft.getInstance();
    private final ClientProviderManager providerManager;
    private final ItemSwapperClientAPI clientAPI;

    public boolean grabItem(Item item, boolean ignoreHotbar) {
        List<AvailableSlot> slots = providerManager.findSlotsMatchingItem(item, false, ignoreHotbar);
        for (AvailableSlot slot : slots) {
            ItemSwapperClientAPI.OnSwap event = clientAPI.prepareItemSwapEvent
                    .callEvent(new ItemSwapperClientAPI.OnSwap(slot, new AtomicBoolean()));
            if (event.canceled().get()) {
                // interaction canceled by some other mod
                return false;
            }
            if (slot.inventory() == -1) {
                ItemUtil.swapWithSlot(ItemUtil.inventorySlotToHudSlot(slot.slot()));
            } else {
                if (ShulkerHelper.isShulker(InventoryUtil.getSelected(minecraft.player.getInventory()).getItem())) {
                    // Can't put a shulker into a shulker, so search a different spot
                    continue;
                }
                ClientNetworkUtil.sendPacket(new SwapItemPayload(slot.inventory(), slot.slot()));
            }
            clientAPI.itemSwapSentEvent.callEvent(new ItemSwapperClientAPI.SwapSent(slot));
            return true;
        }
        return false;
    }

    public Component getDisplayname(ItemStack item) {
        if (dev.tr7zw.transition.mc.ItemUtil.hasCustomName(item)) {
            return item.getHoverName().copy();
        }
        NameProvider provider = providerManager.getNameProvider(item);
        if (provider != null) {
            return provider.getDisplayName(item).copy();
        }
        return item.getHoverName().copy();
    }

    public Component getDisplayname(ItemEntry entry) {
        if (entry == null) {
            return null;
        }
        if (entry.getNameOverwride() != null) {
            return entry.getNameOverwride();
        }
        return getDisplayname(entry.getItem().getDefaultInstance());
    }

}
