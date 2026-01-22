package net.dolfirobots.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;

public class JarResourceFile {
    private static final String SERVICE = "JarRessourceFile";

    private final JavaPlugin plugin;
    private final String resourcePath;

    /**
     * Little utils helper to manage the resources saved in the jar
     *
     * @param plugin Plugin instance
     * @param resourcePath Path to the resource in the jar file
     */
    public JarResourceFile(JavaPlugin plugin, String resourcePath) {
        this.plugin = plugin;
        this.resourcePath = resourcePath;
    }

    /**
     * Gets stream from the resource file
     * @return {@link InputStream} from the resource or null if there was an error
     */
    public InputStream getStream() {
        if (plugin == null) {
            LogsManager.newError(SERVICE, "Plugin instance is null");
            return null;
        }
        if (resourcePath == null) {
            LogsManager.newError(SERVICE, "Resource path is null");
            return null;
        }
        InputStream in = plugin.getResource(resourcePath);
        if (in == null) {
            LogsManager.newError(SERVICE, "InputStream from " + resourcePath + " is null");
        }
        return in;
    }

    public String readContent() throws IOException {
        try (InputStream in = getStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }

    public CompletableFuture<String> readContentAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return readContent();
            } catch (IOException e) {
                throw new RuntimeException("Fehler beim asynchronen Lesen der Ressource " + resourcePath, e);
            }
        });
    }

    /**
     * Saves resource synchronously to the given path
     *
     * @param path The path where to save the resource
     * @param overwrite overwrite existing files
     * @param checkDirs create missing directories
     * @throws IOException
     */
    public void saveFileSync(String path, boolean overwrite, boolean checkDirs) throws IOException, SecurityException {
        File targetFile = new File(plugin.getDataFolder(), path); // TODO: Add more logging
        // Creating dirs if not exist
        if (!targetFile.getParentFile().exists() && checkDirs) {
            targetFile.getParentFile().mkdirs();
        }
        if (!targetFile.exists() || overwrite) {
            try (InputStream in = getStream()) {
                Files.copy(in, targetFile.toPath(), overwrite ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.COPY_ATTRIBUTES);
            }
        }
    }

    /**
     * Saves resource synchronously to the given path
     * It creates missing directories automatically
     *
     * @param path The path where to save the resource
     * @param overwrite overwrite existing files
     * @throws IOException
     */
    public void saveFileSync(String path, boolean overwrite) throws IOException {
        saveFileSync(path, overwrite, true);
    }

    /**
     * Saves resource synchronously to the given path
     * It creates missing directories automatically
     * It does not overwrite existing files
     *
     * @param path The path where to save the resource
     * @throws IOException
     */
    public void saveFileSync(String path) throws IOException {
        saveFileSync(path, false, true);
    }

    // Async saving

    /**
     * Saves resource asynchronously to the given path
     *
     * @param outputPath The path where to save the resource
     * @param overwrite overwrite existing files
     * @param checkDirs create missing directories
     * @return {@link CompletableFuture}
     */
    public CompletableFuture<Void> saveFile(String outputPath, boolean overwrite, boolean checkDirs) {
        return CompletableFuture.runAsync(() -> {
            try {
                saveFileSync(outputPath, overwrite, checkDirs);
            } catch (IOException e) {
                throw new RuntimeException("Fehler beim asynchronen Speichern der Ressource " + resourcePath, e);
            }
        });
    }

    /**
     * Saves resource asynchronously to the given path
     * It creates missing directories automatically
     *
     * @param outputPath The path where to save the resource
     * @param overwrite overwrite existing files
     * @return {@link CompletableFuture}
     */
    public CompletableFuture<Void> saveFile(String outputPath, boolean overwrite) {
        return saveFile(outputPath, overwrite, true);
    }

    /**
     * Saves resource asynchronously to the given path
     * It creates missing directories automatically
     * It does not overwrite existing files
     *
     * @param outputPath The path where to save the resource
     * @return {@link CompletableFuture}
     */
    public CompletableFuture<Void> saveFile(String outputPath) {
        return saveFile(outputPath, false, true);
    }
}