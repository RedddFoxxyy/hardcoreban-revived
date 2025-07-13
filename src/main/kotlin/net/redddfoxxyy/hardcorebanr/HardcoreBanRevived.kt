package net.redddfoxxyy.hardcorebanr

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.server.network.ServerPlayerEntity
import net.redddfoxxyy.hardcorebanr.config.DependencyConfig.modifyBanHammerConfig
import net.redddfoxxyy.hardcorebanr.config.HardcoreBanConfig
import net.redddfoxxyy.hardcorebanr.impl.HardcoreDeathHandler
import org.slf4j.LoggerFactory

object HardcoreBanRevived : ModInitializer {
	private const val MOD_ID = "hardcoreban-revived"
    internal val logger = LoggerFactory.getLogger(MOD_ID)
	lateinit var config: HardcoreBanConfig

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hardcore Ban Revived Initialized.")
		config = HardcoreBanConfig.load()
		modifyBanHammerConfig()

		ServerLivingEntityEvents.AFTER_DEATH.register { entity, damageSource ->
			if (entity is ServerPlayerEntity) {
				HardcoreDeathHandler.onPlayerDeath(entity, damageSource)
			}
		}
	}
}