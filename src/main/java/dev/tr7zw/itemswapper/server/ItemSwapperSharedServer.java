package dev.tr7zw.itemswapper.server;

import dev.tr7zw.itemswapper.packets.clientbound.*;
import dev.tr7zw.itemswapper.packets.serverbound.*;
import dev.tr7zw.itemswapper.server.manger.*;
import dev.tr7zw.transition.loader.networking.*;
import lombok.*;
import org.apache.logging.log4j.*;

public abstract class ItemSwapperSharedServer {

    public static final Logger LOGGER = LogManager.getLogger("ItemSwapper");
    public static ItemSwapperSharedServer INSTANCE;
    private final ServerItemHandler itemHandler = new ServerItemHandler();
    @Getter
    private final ServerPlayerManager playerManager = new ServerPlayerManager();

    public void onLoad() {
        INSTANCE = this;
        LOGGER.info("Loading ItemSwapper server support.");
        ServerNetworkUtil.registerPackets(handler -> {
            // Client packets
            handler.registerClientCustomPacket(ShulkerSupportPayload.INSTANCE);
            handler.registerClientCustomPacket(RefillSupportPayload.INSTANCE);
            handler.registerClientCustomPacket(DisableModPayload.INSTANCE);
            // Server packets
            handler.registerServerCustomPacket(SwapItemPayload.INSTANCE,
                    (payload, player) -> getItemHandler().swapItem(player, payload));
            handler.registerServerCustomPacket(RefillItemPayload.INSTANCE,
                    (payload, player) -> getItemHandler().refillSlot(player, payload));
        });
    }

    public ServerItemHandler getItemHandler() {
        return itemHandler;
    }

}
