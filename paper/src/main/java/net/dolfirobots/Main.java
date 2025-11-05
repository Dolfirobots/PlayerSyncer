package net.dolfirobots;

import net.dolfirobots.utils.GitHub;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static net.dolfirobots.utils.Messanger.centerMessage;
import static net.dolfirobots.utils.Messanger.sendConsole;

public final class Main extends JavaPlugin {
    // final values
    public static final String GITHUB_USER = "Dolfirobots";
    public static final String GITHUB_REPO = "PlayerSyncer";

    public static final List<String> SUPPORTED_VERSIONS = List.of("1.21");

    public static final int CONSOLE_LENGTH = 29;
    public static final int CHAT_MESSAGE_LENGTH = 41;
    public static final String PREFIX = "§e[§bPlayerSyncer§e] §7";

    private static Main main;
    private static String PLUGIN_VERSION = "unknown";


    @Override
    public void onEnable() {
        main = this;
        PLUGIN_VERSION = getDescription().getVersion();

        // Checks the plugin versions
        String lastVersion = GitHub.getLastVersion(GITHUB_USER, GITHUB_REPO); // TODO: Make it asyncron

        // Loading message
        sendConsole("-".repeat(CONSOLE_LENGTH));
        sendConsole(centerMessage("§ePlayerSyncer §7is now §aenabled§7!", CONSOLE_LENGTH));
        sendConsole(centerMessage("MC Version: " + (isServerVersionSupported() ? "§aCompatible" : "§cNot Compatible"), CONSOLE_LENGTH));
        sendConsole(centerMessage("Version: " + (PLUGIN_VERSION.equalsIgnoreCase(lastVersion) ? "§a" : "§c") + PLUGIN_VERSION, CONSOLE_LENGTH));
        if (!PLUGIN_VERSION.equalsIgnoreCase(lastVersion)) {
            sendConsole(centerMessage("There is a newer version outside!", CONSOLE_LENGTH));
        }
        sendConsole("-".repeat(CONSOLE_LENGTH));

        // Commands
        MainCommand.register();

    }

    @Override
    public void onDisable() {
        // Checks the plugin versions
        String lastVersion = GitHub.getLastVersion(GITHUB_USER, GITHUB_REPO); // TODO: Make this method asyncron as well

        // Disable message
        sendConsole("-".repeat(CONSOLE_LENGTH));
        sendConsole(centerMessage("§ePlayerSyncer §7is now §cdisabled§7!", CONSOLE_LENGTH));
        sendConsole(centerMessage("MC Version: " + (isServerVersionSupported() ? "§aCompatible" : "§cNot Compatible"), CONSOLE_LENGTH));
        sendConsole(centerMessage("Version: " + (PLUGIN_VERSION.equalsIgnoreCase(lastVersion) ? "§a" : "§c") + PLUGIN_VERSION, CONSOLE_LENGTH));
        if (!PLUGIN_VERSION.equalsIgnoreCase(lastVersion)) {
            sendConsole(centerMessage("There is a newer version outside!", CONSOLE_LENGTH));
        }
        sendConsole("-".repeat(CONSOLE_LENGTH));
    }

    /**
     * Checks if the Minecraft Server version is equal to the supported plugin versions.
     * @return boolean
     */
    private boolean isServerVersionSupported() {
        return SUPPORTED_VERSIONS.stream().anyMatch(getServer().getVersion()::contains);
    }

    public static Main getInstance() {
        return main;
    }

    public static String getPluginVersion() {
        return PLUGIN_VERSION;
    }
}
