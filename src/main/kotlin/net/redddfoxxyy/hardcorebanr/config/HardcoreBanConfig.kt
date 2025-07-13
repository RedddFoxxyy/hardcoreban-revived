package net.redddfoxxyy.hardcorebanr.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.redddfoxxyy.hardcorebanr.HardcoreBanRevived
import java.io.File

@Serializable
data class HardcoreBanConfig(
	val banDurationSeconds: Long = 604800, // 7-days default
	val banMessage: String = "You got banned for dying",
	val enableHardcoreBans: Boolean = true
) {
	companion object {
		// Define the config file path
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
					HardcoreBanRevived.logger.error("Failed to load config, using default values", e)
					// create a new instance of the config and save it
					HardcoreBanConfig().also {
						save(it)
					}
				}
			} else {
				HardcoreBanConfig().also {
					save(it)
				}
			}
		}

		private fun save(config: HardcoreBanConfig) {
			configFile.parentFile.mkdirs()
			configFile.writeText(json.encodeToString(config))
		}
	}
}