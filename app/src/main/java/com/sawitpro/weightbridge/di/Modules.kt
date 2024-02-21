package com.sawitpro.weightbridge.di

import androidx.room.Room
import com.sawitpro.weightbridge.data.core.RetrofitBuilder
import com.sawitpro.weightbridge.data.local.core.AppDatabase
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.repository.WeighBridgeRepositoryImpl
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.ui.feature.detail.WeighingCreateViewModel
import com.sawitpro.weightbridge.ui.feature.detail.WeighingEditViewModel
import com.sawitpro.weightbridge.ui.feature.detail.WeighingDetailViewModel
import com.sawitpro.weightbridge.ui.feature.list.WeighingListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    private val persistenceModule = module {
        single {
            Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "appdb")
                .fallbackToDestructiveMigration().build()
        }
    }

    private val networkModules = module {
        single<WeighingApi> {
            RetrofitBuilder.create(WeighingApi::class.java)
        }
    }

    private val repositoryModules = module {
        single<WeighBridgetRepository> { WeighBridgeRepositoryImpl(get(), get()) }
    }

    private val viewModelModules = module {
        viewModel { WeighingListViewModel(get()) }
        viewModel { WeighingEditViewModel(get()) }
        viewModel { WeighingDetailViewModel(get()) }
        viewModel { WeighingCreateViewModel(get()) }
    }

    private val daoModules = module {
        single { RoomModule(get()).weighingTicketDao() }
    }

    fun getAppComponents() = listOf(
        persistenceModule,
        networkModules,
        repositoryModules,
        viewModelModules,
        daoModules
    )
}