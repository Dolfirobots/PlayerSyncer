/*
 * Utils Package
 * Created by Dolfirobots
 */
package net.dolfirobots.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static net.dolfirobots.manager.MainConfig.prefix;

public class Messanger {
    // Basic messages

    /**
     * Send a message in the console with prefix
     * @param message
     */
    public static void sendConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(prefix() + "§7 " + message);
    }

    /**
     * Send a Player a message with prefix
     * @param player the Player
     * @param message Message you wanne send
     */
    public static void sendMessage(String message, Player player) {
        player.sendMessage(prefix() + "§7" + message);
    }

    /**
     * Centers the message to make it looks better
     * @param message
     * @param length
     * @return centered String
     */
    public static String centerMessage(String message, int length) {
        int spaces = (length - ChatColor.stripColor(message).length()) / 2;
        if (spaces < 0) spaces = 0;
        return " ".repeat(spaces) + message;
    }


    /**
     * Sends an error to the console
     * @param service The class where the error is
     * @param errorMessage The error description
     */
    public static void sendError(String service, String errorMessage) {
        if (service != null && errorMessage != null) {
            // [Example Service] getMessanger was 0
            sendConsole("§8[§e" + service + "§8] " + errorMessage);
        }
    }
}
