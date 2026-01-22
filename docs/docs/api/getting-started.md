---
sidebar_position: 1
title: Getting Started with the API
---

# Getting Started with the PlayerSyncer API

Welcome to the PlayerSyncer API documentation! This guide will help you get started with using the PlayerSyncer API in your own plugins.

## Setting up your Development Environment

To use the PlayerSyncer API, you need to add PlayerSyncer as a dependency to your project.

### Maven

If you are using Maven, add the following to your `pom.xml` file:

```xml title="pom.xml"
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Dolfirobots</groupId>
        <artifactId>PlayerSyncer</artifactId>
        <version>LATEST</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Gradle

If you are using Gradle, add the following to your `build.gradle` file:

```groovy title="build.gradle"
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.Dolfirobots:PlayerSyncer:LATEST'
}
```

## Listening to Sync Events

PlayerSyncer fires several events that you can listen to in your plugin.

-   `PlayerSyncEvent`: Called when a player's data is about to be synchronized.
-   `PlayerLoadEvent`: Called when a player's data has been loaded from the database.

Here is an example of how to listen to the `PlayerSyncEvent`:

```java title="MyPlugin.java"
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.github.dolfirobots.playersyncer.events.PlayerSyncEvent;

public class MyListener implements Listener {

    @EventHandler
    public void onPlayerSync(PlayerSyncEvent event) {
        // Your custom logic here
        event.getPlayer().sendMessage("Your data is being synced!");
    }
}
```

## Adding Custom Data to Sync

You can add your own custom data to be synchronized by PlayerSyncer. This is useful if you want to sync data from your own plugin.

To do this, you need to create a class that implements the `SyncData` interface and register it with PlayerSyncer.

```java title="MyCustomData.java"
import com.github.dolfirobots.playersyncer.data.SyncData;
import org.bukkit.entity.Player;

public class MyCustomData implements SyncData {

    private int myCustomValue;

    @Override
    public String getIdentifier() {
        return "my-custom-data";
    }

    @Override
    public void serialize(Player player) {
        // Save your custom data here
        this.myCustomValue = player.getLevel();
    }

    @Override
    public void deserialize(Player player) {
        // Load your custom data here
        player.setLevel(this.myCustomValue);
    }
}
```

Then, you need to register your custom data in your `onEnable` method:

```java title="MyPlugin.java"
import com.github.dolfirobots.playersyncer.PlayerSyncerAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerSyncerAPI.getInstance().registerSyncData(new MyCustomData());
    }
}
```

This is just a brief overview of the PlayerSyncer API. For more detailed information, please refer to the Javadocs.
