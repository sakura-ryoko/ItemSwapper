package dev.tr7zw.itemswapper.server.manger;

import dev.tr7zw.itemswapper.config.*;
import dev.tr7zw.itemswapper.packets.clientbound.*;
import dev.tr7zw.transition.loader.networking.*;
import net.minecraft.server.level.*;

public class ServerPlayerManager {

    public void onJoin(ServerPlayer serverPlayer) {
        if (ConfigHolder.getInstance().getGeneral().getConfig().serverPreventModUsage) {
            ServerNetworkUtil.sendPacket(serverPlayer, new DisableModPayload(true));
        } else {
            ServerNetworkUtil.sendPacket(serverPlayer, new ShulkerSupportPayload(true));
            ServerNetworkUtil.sendPacket(serverPlayer, new RefillSupportPayload(true));
        }
    }

    public void onLeave(ServerPlayer player) {
    }
}
