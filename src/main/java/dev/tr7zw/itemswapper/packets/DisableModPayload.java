package dev.tr7zw.itemswapper.packets;

import dev.tr7zw.itemswapper.ItemSwapperMod;
import dev.tr7zw.transition.loader.networking.*;
import dev.tr7zw.transition.mc.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.*;

public record DisableModPayload(boolean enabled) implements CustomPacketPayloadSupport {

    public static final DisableModPayload INSTANCE = new DisableModPayload(false);
    public static final Identifier ID = McId.create(ItemSwapperMod.MODID, "disable").id();

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
