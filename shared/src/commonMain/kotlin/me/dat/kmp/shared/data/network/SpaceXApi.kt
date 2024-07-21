package me.dat.kmp.shared.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SpaceXApi(private val httpClient: HttpClient) {
    suspend fun getAllLaunches(): List<RocketLaunchDto> {
        return httpClient.get("https://api.spacexdata.com/v5/launches").body()
    }
}