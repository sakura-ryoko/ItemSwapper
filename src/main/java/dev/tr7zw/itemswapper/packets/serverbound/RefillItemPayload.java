package dev.tr7zw.itemswapper.packets.serverbound;

import dev.tr7zw.itemswapper.ItemSwapperMod;
import dev.tr7zw.transition.loader.networking.*;
import dev.tr7zw.transition.mc.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.*;

/**
 * Payload for refilling an item in a specific slot.
 * 
 * @param slot Inventory slot id
 */
public record RefillItemPayload(int slot) implements CustomPacketPayloadSupport {

    public static final RefillItemPayload INSTANCE = new RefillItemPayload(0);
    public static final Identifier ID = McId.create(ItemSwapperMod.MODID, "refill").id();

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf paramFriendlyByteBuf) {
        paramFriendlyByteBuf.writeInt(slot);
    }

    @Override
    public CustomPacketPayloadSupport read(FriendlyByteBuf friendlyByteBuf) {
        return new RefillItemPayload(friendlyByteBuf);
    }

    public RefillItemPayload(FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

}
