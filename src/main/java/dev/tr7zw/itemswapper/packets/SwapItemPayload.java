package dev.tr7zw.itemswapper.packets;

import dev.tr7zw.itemswapper.ItemSwapperMod;
import dev.tr7zw.itemswapper.util.ServerUtil;
import dev.tr7zw.transition.loader.networking.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.*;

//? if >= 1.20.2 {

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//? } else {
/*
import dev.tr7zw.itemswapper.legacy.CustomPacketPayload;
*///? }

public record SwapItemPayload(int inventorySlot, int slot) implements CustomPacketPayload, CustomPacketPayloadSupport {

    public static final SwapItemPayload INSTANCE = new SwapItemPayload(0, 0);
    public static final Identifier ID = ServerUtil.getResourceLocation(ItemSwapperMod.MODID, "swap");

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf paramFriendlyByteBuf) {
        paramFriendlyByteBuf.writeInt(inventorySlot);
        paramFriendlyByteBuf.writeInt(slot);
    }

    @Override
    public CustomPacketPayloadSupport read(FriendlyByteBuf friendlyByteBuf) {
        return new SwapItemPayload(friendlyByteBuf);
    }

    public SwapItemPayload(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt());
    }

}
