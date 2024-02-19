package com.sawitpro.weightbridge.di

import com.sawitpro.weightbridge.data.core.RetrofitBuilder
import com.sawitpro.weightbridge.data.remote.api.WeighingApi
import com.sawitpro.weightbridge.domain.repository.WeighBridgeRepositoryImpl
import com.sawitpro.weightbridge.domain.repository.WeighBridgetRepository
import com.sawitpro.weightbridge.ui.feature.detail.WeighingDetailViewModel
import com.sawitpro.weightbridge.ui.feature.list.WeighingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    private val networkModules = module {
        single<WeighingApi> {
            RetrofitBuilder.create(WeighingApi::class.java)
        }
    }

    private val repositoryModules = module {
        single<WeighBridgetRepository> { WeighBridgeRepositoryImpl(get()) }
    }

    private val viewModelModules = module {
        viewModel { WeighingListViewModel(get()) }
        viewModel { WeighingDetailViewModel(get()) }
    }

    fun getAppComponents() = listOf(
        networkModules,
        repositoryModules,
        viewModelModules
    )
}