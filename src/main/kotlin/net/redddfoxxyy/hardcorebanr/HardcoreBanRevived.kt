package net.redddfoxxyy.hardcorebanr

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.server.network.ServerPlayerEntity
import org.slf4j.LoggerFactory
import java.io.File

object HardcoreBanRevived : ModInitializer {
	private const val MOD_ID = "hardcoreban-revived"
    private val logger = LoggerFactory.getLogger(MOD_ID)
	lateinit var config: HardcoreBanConfig

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hardcore Ban Revived Initialized.")
		config = HardcoreBanConfig.load()
		modifyBanHammerConfig(logger)
		//	ServerPlayerEvents.AFTER_RESPAWN.register { old, _, alive ->
		//		if (!alive) banOnDeath(old)
		//	}

		ServerLivingEntityEvents.AFTER_DEATH.register { entity, damageSource ->
			if (entity is ServerPlayerEntity) {
				HardcoreDeathHandler.onPlayerDeath(entity, damageSource)
			}
		}
	}

	private fun modifyBanHammerConfig(logger: org.slf4j.Logger) {
		try {
			val configDir = File("config/banhammer")
			configDir.mkdirs()

			val messagesFile = File(configDir, "messages.json")

			if (messagesFile.exists()) {
				messagesFile.delete()
			}

			messagesFile.writeText(customConfig)
			logger.info("Successfully created custom Ban Hammer messages config")
		} catch (e: Exception) {
			logger.error("Failed to modify Ban Hammer config", e)
		}
	}
}