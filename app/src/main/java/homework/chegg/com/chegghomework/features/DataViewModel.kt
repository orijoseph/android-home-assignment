package homework.chegg.com.chegghomework.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import homework.chegg.com.chegghomework.data.Response
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
                dataRepository.getStories()
                        .map { Response.Success(it) as Response<List<IDetailsTiDisplay>> }
                        .onErrorReturn { Response.Error(it) },
                dataRepository.getArticles()
                        .map { Response.Success(it) as Response<List<IDetailsTiDisplay>> }
                        .onErrorReturn { Response.Error(it) },
                dataRepository.getArticles2()
                        .map { Response.Success(it) as Response<List<IDetailsTiDisplay>> }
                        .onErrorReturn { Response.Error(it) }
        ) { responseA, responseB, responseC ->

            val viewState: MainActivityState
            var loadedList = mutableListOf<IDetailsTiDisplay>()

            val error = responseA.error != null || responseB.error != null || responseC.error != null

            loadedList.addAll(responseA.data?: emptyList())
            loadedList.addAll(responseB.data?: emptyList())
            loadedList.addAll(responseC.data?: emptyList())

            viewState = if (error) {
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