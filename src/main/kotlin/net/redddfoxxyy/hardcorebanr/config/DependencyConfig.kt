package net.redddfoxxyy.hardcorebanr.config

import net.redddfoxxyy.hardcorebanr.HardcoreBanRevived.logger
import kotlinx.serialization.json.*
import net.redddfoxxyy.hardcorebanr.HardcoreBanRevived
import net.redddfoxxyy.hardcorebanr.impl.banHammerMessagesConfig
import java.io.File

object DependencyConfig {
	internal fun modifyBanHammerConfig() {
		try {
			val banHammerConfigDir = File("config/banhammer")
			val banHammerMessagesFile = File(banHammerConfigDir, "messages.json")

			// If the config file or directory doesn't exist, create a default one.
			if (!banHammerMessagesFile.exists()) {
				logger.info("BanHammer config not found. Creating a default one.")
				banHammerConfigDir.mkdirs()
				banHammerMessagesFile.writeText(banHammerMessagesConfig)
			}

			val json = Json {
				prettyPrint = true
				ignoreUnknownKeys = true
			}

			val messagesConfigJson = json.parseToJsonElement(banHammerMessagesFile.readText()).jsonObject

			// Create a mutable copy of the original JSON
			val mutableConfig = messagesConfigJson.toMutableMap()

			// Create our custom tempBanScreen using the message from our own config
			val customTempBanScreen = buildJsonArray {
				add(JsonPrimitive("<red><bold>${HardcoreBanRevived.modConfig.banMessage}</bold></red>"))
				add(JsonPrimitive("<gray>Cause of death: </gray><yellow>\${reason}</yellow>"))
				add(JsonPrimitive("<gray>You will be able to play again after: </gray><yellow>\${expiration_time}</yellow>"))
				add(JsonPrimitive(""))
			}

			// Replace only the tempBanScreen part of the config
			mutableConfig["tempBanScreen"] = customTempBanScreen

			// Write the modified JSON object back to the file
			banHammerMessagesFile.writeText(json.encodeToString(JsonObject(mutableConfig)))

			// Replace only the tempBanScreen part of the config
			mutableConfig["tempBanScreen"] = customTempBanScreen

			logger.info("Successfully updated Ban Hammer's tempBanScreen.")
		} catch (e: Exception) {
			logger.error("Failed to modify Ban Hammer config", e)
		}
	}
}