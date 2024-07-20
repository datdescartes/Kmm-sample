package me.dat.kmp.shared.data

import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.LaunchEntity
import me.dat.kmp.shared.data.network.RocketLaunch
import me.dat.kmp.shared.data.network.SpaceXApi

class SpaceXRepository(private val api: SpaceXApi, private val db: AppDatabase) {
    private fun RocketLaunch.toLaunchEntity(): LaunchEntity {
        return LaunchEntity(
            flightNumber = this.flightNumber,
            missionName = this.missionName,
            details = this.details,
            launchSuccess = this.launchSuccess,
            launchDateUTC = this.launchDateUTC,
            patchUrlSmall = this.links.patch?.small,
            patchUrlLarge = this.links.patch?.large,
            articleUrl = this.links.article
        )
    }

    suspend fun getAllLaunches(forceReload: Boolean): List<LaunchEntity> {
        val cachedLaunches = db.launchDao().getAllLaunches()
        return if (cachedLaunches.isEmpty() || forceReload) {
            api.getAllLaunches().map { it.toLaunchEntity() }.also {
                db.launchDao().insertAll(it)
            }
        } else {
            cachedLaunches
        }
    }
}