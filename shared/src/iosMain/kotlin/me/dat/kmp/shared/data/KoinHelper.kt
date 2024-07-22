package me.dat.kmp.shared.data

import me.dat.kmp.shared.di.Koin

fun initKoin() {
    Koin.initKoin(DataStoreFactory())
}