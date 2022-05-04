package de.fhe.ai.pmc.acat.app.di.modules

import de.fhe.ai.pmc.acat.android_core.LoggerImpl
import de.fhe.ai.pmc.acat.data.AppDatabase
import de.fhe.ai.pmc.acat.data.RepositoryImpl
import de.fhe.ai.pmc.acat.data.UserEntityDao
import de.fhe.ai.pmc.acat.domain.Logger
import de.fhe.ai.pmc.acat.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<Repository> {
        RepositoryImpl(
            AppDatabase.getUserEntityDao(get())
        )
    }
}

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
    }
}