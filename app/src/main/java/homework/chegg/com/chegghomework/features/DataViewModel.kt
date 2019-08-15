package homework.chegg.com.chegghomework.features

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import homework.chegg.com.chegghomework.data.repositories.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers

class DataViewModel(val dataRepository: DataRepository) : ViewModel() {

    val viewState = MutableLiveData<MainActivityState>()


    @SuppressLint("CheckResult")
    fun getData() {
        Observables.zip(
                dataRepository.getStories().onErrorReturn { ResponseA() },
                dataRepository.getArticles().onErrorReturn { ResponseB() },
                dataRepository.getArticles2().onErrorReturn { listOf() }
        ) { responseA, responseB, responseC ->

            val viewState: MainActivityState
            var loadedAll = true
            var loadedList = mutableListOf<IDetailsTiDisplay>()

            if (responseA.stories.isNullOrEmpty()) {
                 loadedAll = false
            } else {
                loadedList.addAll(responseA.stories!!)
            }

            if (responseB.metadata?.articles.isNullOrEmpty()) {
                 loadedAll = false
            } else {
                loadedList.addAll(responseB.metadata?.articles!!)
            }

            if (responseC.isNullOrEmpty()) {
                loadedAll = false
            } else {
                loadedList.addAll(responseC)
            }


            viewState = if (!loadedAll){
                MainActivityState.NotAllLoaded(loadedList)
            } else {
                MainActivityState.LoadedAll(loadedList)
            }

            return@zip viewState

        }.subscribeOn(Schedulers.io()).doOnSubscribe { viewState.value = MainActivityState.LoadingState() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewState.value = it
                }
    }
}