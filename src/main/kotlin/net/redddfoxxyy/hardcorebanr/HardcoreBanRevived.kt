package net.redddfoxxyy.hardcorebanr

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object HardcoreBanRevived : ModInitializer {
	private const val MOD_ID = "hardcoreban-revived"
    private val logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hardcore Ban Revived Initialized.")
	}
}