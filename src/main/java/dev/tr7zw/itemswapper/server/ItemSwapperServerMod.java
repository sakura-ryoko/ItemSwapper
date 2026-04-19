package dev.tr7zw.itemswapper.server;

import net.fabricmc.api.DedicatedServerModInitializer;

public class ItemSwapperServerMod extends ItemSwapperSharedServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        onLoad();
    }

}
