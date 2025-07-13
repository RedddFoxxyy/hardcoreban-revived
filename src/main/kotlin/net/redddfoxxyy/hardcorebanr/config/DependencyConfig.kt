package net.redddfoxxyy.hardcorebanr.config

import net.redddfoxxyy.hardcorebanr.HardcoreBanRevived.logger
import net.redddfoxxyy.hardcorebanr.customConfig
import java.io.File

object DependencyConfig {
	internal fun modifyBanHammerConfig() {
		try {
			// create the banhammer config dir
			val banHammerConfigDir = File("config/banhammer")
			banHammerConfigDir.mkdirs()

			val messagesConfigJson = File(banHammerConfigDir, "messages.json")

			if (messagesConfigJson.exists()) {
				messagesConfigJson.delete()
			}

			messagesConfigJson.writeText(customConfig)
			logger.info("Successfully created custom Ban Hammer messages config.")
		} catch (e: Exception) {
			logger.error("Failed to modify Ban Hammer config", e)
		}
	}
}