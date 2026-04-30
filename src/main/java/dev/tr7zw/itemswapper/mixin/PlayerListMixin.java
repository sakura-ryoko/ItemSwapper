package dev.tr7zw.itemswapper.mixin;

import dev.tr7zw.itemswapper.config.*;
import dev.tr7zw.itemswapper.packets.clientbound.*;
import dev.tr7zw.transition.loader.networking.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

//? if >= 1.20.2 {

import net.minecraft.server.network.CommonListenerCookie;
//? }

@Mixin(PlayerList.class)
public class PlayerListMixin {

    @Inject(method = "placeNewPlayer", at = @At("TAIL"))
    //? if >= 1.20.2 {

    public void placeNewPlayer(Connection connection, ServerPlayer serverPlayer,
            CommonListenerCookie commonListenerCookie, CallbackInfo ci) {
        //? } else {
        /*
            public void placeNewPlayer(Connection connection, ServerPlayer serverPlayer, CallbackInfo ci) {
        *///? }
        if (ConfigHolder.getInstance().getGeneral().getConfig().serverPreventModUsage) {
            ServerNetworkUtil.sendPacket(serverPlayer, new DisableModPayload(true));
        } else {
            ServerNetworkUtil.sendPacket(serverPlayer, new ShulkerSupportPayload(true));
            ServerNetworkUtil.sendPacket(serverPlayer, new RefillSupportPayload(true));
        }
    }

}
