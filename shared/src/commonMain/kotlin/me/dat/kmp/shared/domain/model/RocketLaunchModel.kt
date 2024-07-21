package me.dat.kmp.shared.domain.model

import kotlinx.datetime.LocalDateTime

data class RocketLaunchModel(
    val flightNumber: Int,
    val missionName: String,
    val details: String?,
    val launchSuccess: Boolean?,
    val launchDate: LocalDateTime,
    val patchUrlSmall: String?,
    val patchUrlLarge: String?,
    val articleUrl: String?
)