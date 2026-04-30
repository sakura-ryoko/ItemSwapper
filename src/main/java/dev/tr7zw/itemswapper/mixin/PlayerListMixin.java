package dev.tr7zw.itemswapper.mixin;

import dev.tr7zw.itemswapper.server.*;
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
        ItemSwapperServerMod.INSTANCE.getPlayerManager().onJoin(serverPlayer);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    public void remove(ServerPlayer player, CallbackInfo ci) {
        ItemSwapperServerMod.INSTANCE.getPlayerManager().onLeave(player);
    }

}
