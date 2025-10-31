package net.dolfirobots.manager;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public enum PermissionManager {

    RELOAD("admin"),
    CHECK_VERSION("version"),
    COMMAND_ADMIN("command.admin");

    private final String permission;

    PermissionManager(String permission) {
        this.permission = "playersyncer." + permission;
    }
    public String toString() {
        return permission;
    }
    public boolean checkPlayer(Player player) {
        return player.hasPermission(permission);
    }
}
