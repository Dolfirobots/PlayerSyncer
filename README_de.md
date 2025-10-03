# 🔄 PlayerSyncer

> [!NOTE]
> Sprachen:  
> **[[🇩🇪] *Deutsch*](./README_DE.md)**  
> **[[🇬🇧] English](./README.md)**

[![License](https://img.shields.io/github/license/Dolfirobots/MySQL-Player-Sync?style=square)](./LICENSE)
[![GitHub Dowloads](https://shields.io/github/downloads/Dolfirobots/MySQL-Player-Sync/total?label=Downloads&logoColor=Green&color=Blue&style=flat)](https://github.com/Dolfirobots/MySQL-Player-Sync/releases)
[![GitHub Release](https://img.shields.io/github/v/release/Dolfirobots/MySQL-Player-Sync?color=Green)](https://github.com/Dolfirobots/OnlyProxy/releases "OnlyProxy Releases")
[![Discord](https://img.shields.io/discord/1079052573845241877.svg?logo=discord&logoColor=Green&color=Blue&labelColor=Green)](https://discord.gg/dxZTGpPbkd "Discord")
[![Database](https://img.shields.io/badge/Database-MySQL%20%7C%20MariaDB-orange?style=flat-square)](#-datenbank-einrichtung)

**PlayerSyncer** ist ein Plugin für **Minecraft Paper (1.21.x)**, mit dem du **Spielerdaten** zuverlässig über  
mehrere Server hinweg synchronisieren kannst – vollständig NBT-kompatibel und mit API für Drittanbieter-Plugins.

---

## ✨ Features

- ✅ Kompatibel mit **Minecraft Paper 1.21.x**
- ✅ Unterstützt **MySQL** und **MariaDB**
- 🔄 Synchronisiert automatisch:
  - Inventare (mit **vollständiger Item-NBT**)
  - Leben
  - Hunger & Sättigung
  - Schaden
  - Aktive Effekte (Potions)
  - Wasser Atem
  - Erfolge
  - Freigeschaltet Rezepte
- 🧩 API zur Integration in eigene Plugins
- 📁 Asynchrone Datenverarbeitung

---

## 📥 Installation

1. Lade die neueste Version von [GitHub](https://github.com/Dolfirobots/PlayerSyncer/releases) oder Spigot (Coming Soon) herunter.
2. Lege die `.jar`-Datei in deinen Server-Ordner `/plugins/`
3. Starte den Server neu.
4. Lies das [Wiki](https://github.com/Dolfirobots/PlayerSyncer/wiki)

---

## ⚙️ Konfiguration

Nach dem ersten Start findest du die Konfigurationsdatein in `/plugins/PlayerSync/`  

`config.yml`:
COOMING SOON!

---

## 🛠️ Datenbank Einrichtung

### 🧾 SQL-Befehle:

1. Database erstellen:
```sql
CREATE DATABASE player_sync;
```
2. Login erstellen (Weil `root` nicht empfehlenswert ist):
```sql
CREATE USER 'sync_plugin'@'%' IDENTIFIED BY 'YOUR_PASSWORD';
```
3. Rechte setzten für den User
```sql
GRANT ALL PRIVILEGES ON player_sync.* TO 'sync_plugin'@'%';
FLUSH PRIVILEGES;
```

> 🔐 **Hinweis:** Beschränke Zugriffe auf vertrauenswürdige IP-Adressen und verwende sichere Passwörter!

---

## 📚 API Nutzung

`PlayerSyncer` stellt eine API für Entwickler bereit.

> 🧩 Weitere Informationen zur API findest du bald im [Wiki](https://github.com/DeinUser/PlayerSyncer/wiki) *(in Arbeit)*.

---

## 📑 Rechte (Permissions)

Aktuell werden keine speziellen Permissions benötigt.
Die API ist offen, benötigt aber keine zusätzlichen Berechtigungen.

---

## 🧪 Kompatibilität

* ✅ Minecraft 1.21.x
* ✅ Paper (Empfohlen)
* ⚠️ Andere Server-Software (z.B. Spigot, Purpur) nicht getestet

---

## 📁 Speicherort der Spieler-Daten

Alle Daten werden automatisch in deiner konfigurierten Datenbank gespeichert.

---

## 📜 Lizenz

Dieses Projekt ist unter der [MIT License](./LICENSE) lizenziert.

---

## 🤝 Mitwirken

* Fehler gefunden? → [Issue erstellen](https://github.com/DeinUser/PlayerSyncer/issues)
* Feature-Wunsch? → [Join auf Discord](https://discord.gg/dxZTGpPbkd "Discord")
* Du möchtest selbst beitragen? → Fork das Repo und sende einen Pull Request





