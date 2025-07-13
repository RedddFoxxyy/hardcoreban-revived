<div align="center">
    <img src="./src/main/resources/assets/hardcoreban-revived/HardcoreBan-Revived-2048.png" width=512 alt="HardcoreBan-Revived">
</div>

<h1 align="center">
HardcoreBan-Revived
</h1>

[](https://www.gnu.org/licenses/gpl-3.0)

A simple, server-side Fabric mod that adds a hardcore-style death-ban mechanic to your server, built on the powerful BanHammer API.

This mod raises the stakes of survival gameplay. When a player dies, they are automatically banned from the server for a configurable duration, making every action and encounter more meaningful.

## ✨ Features

  * **Automatic Death Bans**: Players who die on your server will be automatically and immediately banned.
  * **Dynamic Ban Reasons**: The ban message reflects the actual cause of death, providing specific reasons for events like falling, drowning, burning in lava, or being slain by another player.
  * **Fully Configurable**: A configuration file (`config/HardcoreBanRevived/config.json`) is automatically generated, allowing you to tailor the experience.
      * Enable or disable the death-ban system entirely.
      * Set the exact ban duration in seconds.
  * **Custom Ban Screen**: The mod leverages the BanHammer API to provide an informative ban screen, telling the player why they were banned and how long they have to wait.
  * **Server-Side Only**: No client installation is required. Players can join your server without needing to install the mod themselves.

## 🔧 Configuration

Upon the first launch, the mod will create a `config.json` file in your server's `config/HardcoreBanRevived/` directory.

```json
{
    "banDurationSeconds": 604800,
    "banMessage": "You got banned for dying",
    "enableHardcoreBans": true
}
```

Simply edit these values and restart your server to apply the changes.

## 📦 Dependencies

To use this mod, your server must have the following installed:

  * **Fabric Loader**
  * **Fabric API**
  * **Fabric Language Kotlin**
  * **BanHammer**

## 🛠️ Building from Source

If you want to build the mod from the source code yourself, follow these steps:

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/RedddFoxxyy/hardcoreban-revived.git
    cd hardcoreban-revived
    ```

2.  **Build the `.jar` file:**
    Use the Gradle wrapper included in the project to build the mod.

      * On Windows:
        ```bash
        gradlew build
        ```
      * On macOS/Linux:
        ```bash
        ./gradlew build
        ```

3.  **Find the output:**
    The compiled `.jar` file can be found in the `build/libs/` directory.

## 📄 License

This project is licensed under the **GNU General Public License v3.0**. See the `LICENSE` file for details.
