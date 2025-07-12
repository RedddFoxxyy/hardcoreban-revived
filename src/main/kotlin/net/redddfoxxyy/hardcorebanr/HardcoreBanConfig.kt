package net.redddfoxxyy.hardcorebanr

data class HardcoreBanConfig(
	val banDurationSeconds: Long = 604800, // 7 days default
	val banMessage: String = "You got banned for dying",
	val enableHardcoreBans: Boolean = true
) {
	companion object {
		fun load(): HardcoreBanConfig {
			// Load from config file or return default ( to be implemented )
			return HardcoreBanConfig()
		}
	}
}