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

public record DisableModPayload(boolean enabled) implements CustomPacketPayload, CustomPacketPayloadSupport {

    public static final DisableModPayload INSTANCE = new DisableModPayload(false);
    public static final Identifier ID = ServerUtil.getResourceLocation(ItemSwapperMod.MODID, "disable");

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf paramFriendlyByteBuf) {
        paramFriendlyByteBuf.writeBoolean(enabled);
    }

    @Override
    public CustomPacketPayloadSupport read(FriendlyByteBuf friendlyByteBuf) {
        return new DisableModPayload(friendlyByteBuf);
    }

    public DisableModPayload(FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

}
