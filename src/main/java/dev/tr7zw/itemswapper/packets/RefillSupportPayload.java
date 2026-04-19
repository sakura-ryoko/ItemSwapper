package dev.tr7zw.itemswapper.packets;

import dev.tr7zw.itemswapper.ItemSwapperMod;
import dev.tr7zw.itemswapper.util.ServerUtil;
import dev.tr7zw.transition.loader.networking.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.*;

public record RefillSupportPayload(boolean enabled) implements CustomPacketPayloadSupport {

    public static final RefillSupportPayload INSTANCE = new RefillSupportPayload(true);
    public static final Identifier ID = ServerUtil.getResourceLocation(ItemSwapperMod.MODID, "enablerefill");

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
        return new RefillSupportPayload(friendlyByteBuf);
    }

    public RefillSupportPayload(FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

}
