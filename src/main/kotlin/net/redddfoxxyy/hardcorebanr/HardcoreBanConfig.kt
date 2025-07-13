package net.redddfoxxyy.hardcorebanr

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class HardcoreBanConfig(
	val banDurationSeconds: Long = 604800, // 7 days default
	val banMessage: String = "You got banned for dying",
	val enableHardcoreBans: Boolean = true
) {
	companion object {
		private val configFile = File("config/HardcoreBanRevived/config.json")
		private val json = Json {
			prettyPrint = true
			encodeDefaults = true
		}

		fun load(): HardcoreBanConfig {
			return if (configFile.exists()) {
				try {
					json.decodeFromString(configFile.readText())
				} catch (e: Exception) {
					// TODO: Log the error if decoding fails
					// logger.error("Failed to load config, using default values", e)
					HardcoreBanConfig().also { save(it) }
				}
			} else {
				HardcoreBanConfig().also { save(it) }
			}
		}

		private fun save(config: HardcoreBanConfig) {
			configFile.parentFile.mkdirs()
			configFile.writeText(json.encodeToString(config))
		}
	}
}