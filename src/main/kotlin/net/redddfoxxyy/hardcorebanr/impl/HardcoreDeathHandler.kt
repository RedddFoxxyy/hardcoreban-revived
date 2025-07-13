package net.redddfoxxyy.hardcorebanr.impl

import eu.pb4.banhammer.api.BanHammer
import eu.pb4.banhammer.api.PunishmentData
import eu.pb4.banhammer.api.PunishmentType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.redddfoxxyy.hardcorebanr.HardcoreBanRevived

object HardcoreDeathHandler {
//	private val config = HardcoreBanConfig.load()

	fun onPlayerDeath(player: ServerPlayerEntity, damageSource: DamageSource) {
		val config = HardcoreBanRevived.modConfig
		if (!config.enableHardcoreBans) return

		val deathCause = getDeathCause(damageSource)
		val customReason = deathCause

		val serverSource = player.server?.commandSource

		val punishment = PunishmentData.create(
			player,
			serverSource,
			customReason,
			config.banDurationSeconds,
			PunishmentType.BAN
		)

		BanHammer.punish(punishment, false, false)
	}

	private fun getDeathCause(damageSource: DamageSource): String {
		return when {
			damageSource.attacker != null -> "killed by ${damageSource.attacker!!.displayName?.string}"
			damageSource.isOf(DamageTypes.LAVA) -> "tried to swim in lava"
			damageSource.isOf(DamageTypes.FALL) -> "fell from high place"
			damageSource.isOf(DamageTypes.DROWN) -> "drowned"
			damageSource.isOf(DamageTypes.STARVE) -> "starved to death"
			else -> "died"
		}
	}


}