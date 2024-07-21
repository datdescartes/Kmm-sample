package me.dat.kmp.shared.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunchDto(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("details")
    val details: String?,
    @SerialName("success")
    val launchSuccess: Boolean?,
    @SerialName("links")
    val links: Links
) {
    @Serializable
    data class Links(
        @SerialName("patch")
        val patch: Patch?,
        @SerialName("article")
        val article: String?
    )

    @Serializable
    data class Patch(
        @SerialName("small")
        val small: String?,
        @SerialName("large")
        val large: String?
    )
}
