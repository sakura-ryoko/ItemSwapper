package dev.tr7zw.itemswapper.util;

import net.minecraft.resources.*;
import net.minecraft.world.item.ItemStack;

/**
 * Contains copies from NMSHelper, because NMSHelper pulls the Minecraft class,
 * which will crash on servers
 */
public class ServerUtil {

    public static boolean isSame(ItemStack a, ItemStack b) {
        //? if < 1.17.0 {

        // return ItemStack.isSame(a, b);
        //? } else if <= 1.20.4 {
        /*
        return ItemStack.isSameItemSameTags(a, b);
        *///? } else {

        return ItemStack.isSameItemSameComponents(a, b);
        //? }
    }

    public static Identifier getResourceLocation(String namespace, String path) {
        //? if >= 1.21.11 {

        return Identifier.fromNamespaceAndPath(namespace, path);
        //? } else if >= 1.21.0 {
        /*
        return Identifier.fromNamespaceAndPath(namespace, path);
        *///? } else {
        /*
         return new Identifier(namespace, path);
        *///? }
    }

    public static Identifier getResourceLocation(String key) {
        //? if >= 1.21.11 {

        return Identifier.parse(key);
        //? } else if >= 1.21.0 {
        /*
        return Identifier.parse(key);
        *///? } else {
        /*
         return new Identifier(key);
        *///? }
    }

}
