package homework.chegg.com.chegghomework.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import homework.chegg.com.chegghomework.data.repositories.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class DataViewModel(val dataRepository: DataRepository) : ViewModel() {

    val viewState = MutableLiveData<MainActivityState>()
    val disposable = CompositeDisposable()

    fun getData() {

        Observables.zip(
                dataRepository.getStories().onErrorReturn { listOf() },
                dataRepository.getArticles().onErrorReturn { listOf() },
                dataRepository.getArticles2().onErrorReturn { listOf() }
        ) { responseA, responseB, responseC ->

            val viewState: MainActivityState
            var loadedAll = true
            var loadedList = mutableListOf<IDetailsTiDisplay>()

            if (responseA.isNullOrEmpty()) {
                loadedAll = false
            } else {
                loadedList.addAll(responseA)
            }

            if (responseB.isNullOrEmpty()) {
                loadedAll = false
            } else {
                loadedList.addAll(responseB)
            }

            if (responseC.isNullOrEmpty()) {
                loadedAll = false
            } else {
                loadedList.addAll(responseC)
            }


            viewState = if (!loadedAll) {
                MainActivityState.NotAllLoaded(loadedList)
            } else {
                MainActivityState.LoadedAll(loadedList)
            }

            return@zip viewState

        }.subscribeOn(Schedulers.io())
                .doOnSubscribe { viewState.value = MainActivityState.LoadingState() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    viewState.value = it
                }.addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}