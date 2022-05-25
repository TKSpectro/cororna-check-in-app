package de.fhe.ai.pmc.acat.app.di.modules

import de.fhe.ai.pmc.acat.android_core.LoggerImpl
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenViewModel
import de.fhe.ai.pmc.acat.data.AppDatabase
import de.fhe.ai.pmc.acat.data.RepositoryImpl
import de.fhe.ai.pmc.acat.domain.GetUsers
import de.fhe.ai.pmc.acat.domain.GetUsersAsync
import de.fhe.ai.pmc.acat.domain.Logger
import de.fhe.ai.pmc.acat.domain.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
    }
    single {
        NavigationManager()
    }
}

val databaseModule = module {
    single<Repository> {
        RepositoryImpl(
            AppDatabase.getUserEntityDao(get())
        )
    }
}

val useCaseModule = module {
    factory { GetUsers(get()) }
    factory { GetUsersAsync(get()) }
}

val viewModelModule = module {
    viewModel { UserListScreenViewModel(get(), get(), get()) }
    viewModel { DetailScreenViewModel() }
}
