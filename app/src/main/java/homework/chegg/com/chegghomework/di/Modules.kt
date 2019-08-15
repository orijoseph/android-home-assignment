package homework.chegg.com.chegghomework.di

import homework.chegg.com.chegghomework.data.repositories.DataRepository
import homework.chegg.com.chegghomework.data.repositories.LocalDataRepository
import homework.chegg.com.chegghomework.data.repositories.RemoteDataRepository
import homework.chegg.com.chegghomework.features.DataViewModel
import homework.chegg.com.chegghomework.network.CheggApi
import homework.chegg.com.chegghomework.network.WebService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit: Retrofit = WebService.client

private val cheggRemoteApi: CheggApi = retrofit.create(CheggApi::class.java)

val dataRepository = module {
    single { DataRepository(LocalDataRepository(), RemoteDataRepository(cheggRemoteApi)) }
}

val viewModulesModul = module {
    viewModel { DataViewModel(get()) }
}