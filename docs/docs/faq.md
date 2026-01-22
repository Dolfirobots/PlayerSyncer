---
sidebar_position: 3
title: FAQ
---

# Frequently Asked Questions

Here are some of the most frequently asked questions about PlayerSyncer.

### What databases are supported?

PlayerSyncer currently supports `MySQL`, `MariaDB`, and `PostgreSQL`. We recommend using a modern and performant database for the best experience.

### Can I sync data between different Minecraft versions?

It is generally not recommended to sync player data between different major Minecraft versions (e.g., 1.16.x and 1.17.x). This can lead to data corruption and other issues. We recommend using the same Minecraft version on all your servers.

### How does PlayerSyncer handle server crashes?

PlayerSyncer is designed to be resilient to server crashes. Player data is saved to the database at regular intervals, and upon server startup, the plugin will attempt to load the latest data from the database. This minimizes data loss in the event of a crash.

### Is it possible to sync custom plugin data?

Yes! PlayerSyncer provides a powerful API that allows developers to sync custom data from their own plugins. For more information, please refer to our [API Documentation](/docs/api/getting-started).

### What data can be synchronized?

PlayerSyncer can synchronize a wide range of player data, including:

-   **Inventory:** The player's main inventory and armor slots.
-   **Ender Chest:** The contents of the player's ender chest.
-   **Health & Food:** The player's health and food levels.
-   **Experience:** The player's experience points and level.
-   **Potion Effects:** Active potion effects on the player.
-   **Game Mode:** The player's game mode (survival, creative, etc.).
-   And much more, all configurable in the `config.yml` file.

### How can I contribute to PlayerSyncer?

PlayerSyncer is an open-source project, and we welcome contributions from the community. If you would like to contribute, you can:

-   **Report Bugs:** If you find a bug, please report it on our [GitHub Issues](https://github.com/Dolfirobots/PlayerSyncer/issues) page.
-   **Suggest Features:** If you have an idea for a new feature, you can suggest it on our [GitHub Issues](https://github.com/Dolfirobots/PlayerSyncer/issues) page.
-   **Contribute Code:** If you are a developer, you can contribute to the project by submitting a pull request on [GitHub](https://github.com/Dolfirobots/PlayerSyncer).

If you have any other questions, please feel free to join our [Discord](https://discordapp.com/invite/m4hhckJe4v) server.