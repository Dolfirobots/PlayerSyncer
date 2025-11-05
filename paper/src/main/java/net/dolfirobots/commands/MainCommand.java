package net.dolfirobots.commands;

import net.dolfirobots.Main;
import net.dolfirobots.manager.MainConfig;
import net.dolfirobots.manager.PermissionManager;
import net.dolfirobots.utils.GitHub;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.dolfirobots.Main.CHAT_MESSAGE_LENGTH;
import static net.dolfirobots.utils.Messanger.*;

public class MainCommand implements CommandExecutor, TabCompleter {

    private static final String SERVICE = "Main Command";

    /**
     * Format the message and sends to the sender
     * @param message Message for the sender
     * @param sender The sender
     */
    public static void sendPlayerMessage(String message, CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MainConfig.prefix()) + "§7" + message);
    }

    /**
     * Main command actions
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            // Some information about the plugin when you do /playersyncer
            sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);
            sendPlayerMessage(centerMessage("§ePlayerSyncer §7Plugin by §bDolfirobots", CHAT_MESSAGE_LENGTH), sender);
            sendPlayerMessage(centerMessage("Running version: §e" + Main.getPluginVersion(), CHAT_MESSAGE_LENGTH), sender);
            sendPlayerMessage(centerMessage("Download it here:", CHAT_MESSAGE_LENGTH), sender);
            sendPlayerMessage(centerMessage("https://github.com/" + Main.GITHUB_USER + "/" + Main.GITHUB_REPO, CHAT_MESSAGE_LENGTH), sender);
            sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);
            return true;
        }

        if (strings.length == 1) {
            // Reload the configs with /playersyncer reload
            if (strings[0].equalsIgnoreCase("reload")) {
                // Check the permission
                if (!PermissionManager.RELOAD.checkPlayer((Player) sender)) {
                    sendNoPermission((Player) sender);
                    return true;
                }
                // TODO: Implement the reload feature

                // Checks the GitHub version with /playersyncer version
            } else if (strings[0].equalsIgnoreCase("version")) {
                if (!PermissionManager.CHECK_VERSION.checkPlayer((Player) sender)) {
                    sendNoPermission((Player) sender);
                    return true;
                }
                // Fetching version from GitHub
                sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);
                sendPlayerMessage(centerMessage("§eFetching last version...", CHAT_MESSAGE_LENGTH), sender); // TODO: Try some layouts
                sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);

                String lastVersion = GitHub.getLastVersion(Main.GITHUB_USER, Main.GITHUB_REPO); // TODO: Make it asyncron

                sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);

                if (lastVersion.equalsIgnoreCase(Main.getPluginVersion())) {
                    sendPlayerMessage(centerMessage("Your version: §a" + Main.getPluginVersion() + "§7 == §a" + lastVersion, CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("§aPlayerSyncer is up to date! (:", CHAT_MESSAGE_LENGTH), sender);
                } else if (!lastVersion.equalsIgnoreCase("unknown")) {
                    sendPlayerMessage(centerMessage("Your version: §c" + Main.getPluginVersion() + "§7 => §a" + lastVersion, CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage("§c" + centerMessage("§cPlayerSyncer is not up to date! ):", CHAT_MESSAGE_LENGTH), sender);
                    List<String> versions = GitHub.getAllVersions(Main.GITHUB_USER, Main.GITHUB_REPO);

                    int currentIndex = versions.indexOf(Main.getPluginVersion());

                    int behindCount = 0;
                    if (currentIndex != -1) {
                        behindCount = versions.size() - (currentIndex + 1);
                    } else {
                        sendError(SERVICE, "currentIndex is -1 wich means there was no right version found");
                    }
                    // Example:
                    // versions => "1.0.0", "1.1.2", "1.3.5", "1.2.4", "1.4.8", "3.12.20"
                    // plugin version => "1.2.4"
                    // output (behindCount) => 2

                    sendPlayerMessage(centerMessage("You are §e" + behindCount + "§7 versions behind!", CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("Please update it here:", CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("https://github.com/" + Main.GITHUB_USER + "/" + Main.GITHUB_REPO + "/", CHAT_MESSAGE_LENGTH), sender);
                } else {
                    sendPlayerMessage(centerMessage("Your version: §e" + Main.getPluginVersion(), CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("§eWe couldn't check the lasted version!", CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("Please check your", CHAT_MESSAGE_LENGTH), sender);
                    sendPlayerMessage(centerMessage("internet connection!", CHAT_MESSAGE_LENGTH), sender);
                }
                sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);

            }
        } else {
            sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);
            if (PermissionManager.COMMAND_ADMIN.checkPlayer((Player) sender)) {
                sendPlayerMessage(centerMessage("§cUsage: §e/" + s + " [reload/version]", CHAT_MESSAGE_LENGTH), sender);
            } else {
                sendPlayerMessage(centerMessage("§cUsage: §e/" + s, CHAT_MESSAGE_LENGTH), sender);
            }
            sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), sender);
        }
        return true;
    }

    /// Sends the player a no permission message
    public static void sendNoPermission(Player player) {
        sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), player);
        sendPlayerMessage(centerMessage("§cYou don't have the Permission", CHAT_MESSAGE_LENGTH), player);
        sendPlayerMessage(centerMessage("§cfor this command!", CHAT_MESSAGE_LENGTH), player);
        sendPlayerMessage("-".repeat(CHAT_MESSAGE_LENGTH), player);
    }

    /// Tab completer for the main command
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        if (PermissionManager.COMMAND_ADMIN.checkPlayer((Player) sender)) {
            if (strings.length == 1) {
                list.add("reload");
                list.add("version");
            }
        }
        return list;
    }
}
