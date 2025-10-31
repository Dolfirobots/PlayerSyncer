/*
 * Utils Package
 * Created by Dolfirobots
 */
package net.dolfirobots.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static net.dolfirobots.utils.LogsManager.newError;

/**
 * Github checker by Dolfirobots
 * </br>
 * TODO: Make it asyncron
 */
public class GitHub {

    private static final String SERVICE = "GitHub";

    /**
     * Get the newest release version from GitHub
     * @param user Github user
     * @param repo Github repository
     * @return last plugin version (e.g. "1.6.5") or when there was an error: "unknown"
     */
    public static String getLastVersion(String user, String repo) {
        try {
            URL url = new URL("https://api.github.com/repos/" + user + "/" + repo + "/releases/latest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream();
                     Scanner s = new Scanner(is).useDelimiter("\\A")) {
                    String response = s.hasNext() ? s.next() : "";
                    int tagIndex = response.indexOf("\"tag_name\":\"");
                    if (tagIndex != -1) {
                        int start = tagIndex + 12;
                        int end = response.indexOf("\"", start);
                        return response.substring(start, end);
                    }
                }
            }
            return "unknown";
        } catch (Exception e) {
            newError(SERVICE, "Error by fetching GitHub version: " + e.getMessage());
            return "unknown";
        }
    }
    public static ArrayList<String> getAllVersions(String user, String repo) {
        ArrayList<String> versions = new ArrayList<>();
        try {
            URL url = new URL("https://api.github.com/repos/" + user + "/" + repo + "/releases");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream();
                     Scanner s = new Scanner(is).useDelimiter("\\A")) {
                    String response = s.hasNext() ? s.next() : "";
                    int index = 0;
                    while ((index = response.indexOf("\"tag_name\":\"", index)) != -1) {
                        int start = index + 12;
                        int end = response.indexOf("\"", start);
                        if (end != -1) {
                            versions.add(response.substring(start, end));
                            index = end;
                        } else {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            newError(SERVICE, "Error by fetching GitHub versions: " + e.getMessage());
        }
        return versions;
    }

}
