---
sidebar_position: 1
title: Getting Started
---

# Getting Started with PlayerSyncer

Welcome to the PlayerSyncer documentation! This guide will walk you through the process of installing, configuring, and using PlayerSyncer on your Minecraft servers.

## What is PlayerSyncer?

PlayerSyncer is a powerful and efficient plugin for Minecraft servers that allows you to synchronize player data across multiple servers in your network. Whether you're running a small network or a large one, PlayerSyncer ensures that your players have a seamless experience, with their inventories, experience, health, and more, always up-to-date, no matter which server they are on.

## Requirements

Before you can install PlayerSyncer, you need to make sure you have the following:

-   **Minecraft Server:** A server running Spigot, Paper, or a compatible fork (1.16.5+).
-   **Database:** A MySQL, MariaDB or PostgreSQL database server.
-   **Java:** Java 11 or higher.

## Installation

1.  **Download PlayerSyncer:** Download the latest version of PlayerSyncer from the [releases page](https://github.com/Dolfirobots/PlayerSyncer/releases).
2.  **Install the Plugin:** Place the downloaded `.jar` file into the `plugins` folder of each of your Minecraft servers.
3.  **Restart Your Servers:** Restart all your servers to generate the default configuration files.

## Configuration

PlayerSyncer's configuration is located in the `plugins/PlayerSyncer/config.yml` file. You will need to configure it on all your servers.

### Database Configuration

The most important part of the configuration is the database connection. PlayerSyncer uses a database to store and sync player data.

```yaml title="config.yml"
database:
  type: MYSQL # Or POSTGRESQL
  host: "localhost"
  port: 3306
  database: "playersyncer"
  username: "root"
  password: "password"
  pool-settings:
    maximum-pool-size: 10
    minimum-idle: 10
    maximum-lifetime: 1800000
    connection-timeout: 5000
```

### Sync Settings

You can configure what data to sync in the `sync-settings` section of the `config.yml` file.

```yaml title="config.yml"
sync-settings:
  inventory: true
  ender-chest: true
  health: true
  food-level: true
  experience: true
  potion-effects: true
  game-mode: true
  # ... and more
```

## Commands

PlayerSyncer provides a set of commands to manage the plugin and player data.

| Command                     | Description                                     | Permission                    |
| --------------------------- | ----------------------------------------------- | ----------------------------- |
| `/playersyncer reload`      | Reloads the plugin's configuration.             | `playersyncer.admin.reload`   |
| `/playersyncer sync <player>` | Manually forces a synchronization for a player. | `playersyncer.admin.sync`     |
| `/playersyncer status`      | Shows the status of the plugin and database.    | `playersyncer.admin.status`   |

Congratulations! You have successfully installed and configured PlayerSyncer. If you have any questions, feel free to join our [Discord](https://discordapp.com/invite/m4hhckJe4v) server.
