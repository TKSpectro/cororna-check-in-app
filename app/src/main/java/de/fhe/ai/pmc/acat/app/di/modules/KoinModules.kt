package de.fhe.ai.pmc.acat.app.di.modules

import de.fhe.ai.pmc.acat.android_core.LoggerImpl
import de.fhe.ai.pmc.acat.app.ui.screens.auth.LoginScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionListScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.roomdetails.RoomDetailsScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.roomlist.RoomListScreenViewModel
import de.fhe.ai.pmc.acat.data.AppDatabase
import de.fhe.ai.pmc.acat.data.RepositoryImpl
import de.fhe.ai.pmc.acat.domain.*
import org.koin.androidx.compose.get
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

    factory { GetSessionsAsync(get()) }
}

val viewModelModule = module {
    viewModel { UserListScreenViewModel(get(), get(), get()) }
    viewModel { DetailScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get()) }
    viewModel { SessionListScreenViewModel(get(), get()) }
    viewModel { DashboardScreenViewModel(get()) }
    viewModel { ScanScreenViewModel(get()) }
    viewModel { RoomDetailsScreenViewModel(get()) }
    viewModel { RoomListScreenViewModel(get()) }
}
