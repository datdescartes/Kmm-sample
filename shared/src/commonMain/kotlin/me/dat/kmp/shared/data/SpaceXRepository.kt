package me.dat.kmp.shared.data

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.dat.kmp.shared.data.db.AppDatabase
import me.dat.kmp.shared.data.db.LaunchTable
import me.dat.kmp.shared.data.network.RocketLaunchDto
import me.dat.kmp.shared.data.network.SpaceXApi
import me.dat.kmp.shared.domain.model.RocketLaunchModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SpaceXRepository : KoinComponent {
    private val api: SpaceXApi by inject()
    private val db: AppDatabase by inject()

    private fun RocketLaunchDto.toLaunchTable(): LaunchTable {
        return LaunchTable(
            flightNumber = flightNumber,
            missionName = missionName,
            details = details,
            launchSuccess = launchSuccess,
            launchDateUTC = launchDateUTC,
            patchUrlSmall = links.patch?.small,
            patchUrlLarge = links.patch?.large,
            articleUrl = links.article,
        )
    }

    private fun LaunchTable.toLaunchModel(): RocketLaunchModel {
        return RocketLaunchModel(
            flightNumber = flightNumber,
            missionName = missionName,
            details = details,
            launchSuccess = launchSuccess,
            launchDate = Instant.parse(launchDateUTC).toLocalDateTime(TimeZone.UTC),
            patchUrlSmall = patchUrlSmall,
            patchUrlLarge = patchUrlLarge,
            articleUrl = articleUrl,
        )
    }

    suspend fun getAllLaunches(forceReload: Boolean): List<RocketLaunchModel> {
        val cachedLaunches = db.launchDao().getAllLaunches()
        return if (cachedLaunches.isEmpty() || forceReload) {
            api.getAllLaunches().map { it.toLaunchTable() }.also {
                db.launchDao().insertAll(it)
            }
        } else {
            cachedLaunches
        }.map { it.toLaunchModel() }
    }
}