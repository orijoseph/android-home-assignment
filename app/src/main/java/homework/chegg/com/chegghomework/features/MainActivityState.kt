package homework.chegg.com.chegghomework.features

import homework.chegg.com.chegghomework.data.IDetailsTiDisplay

sealed class MainActivityState(val displayLoading: Boolean,
                               val didLoadAll: Boolean,
                               val list: List<IDetailsTiDisplay> = mutableListOf()) {
    class LoadingState : MainActivityState(displayLoading = true, didLoadAll = false, list = mutableListOf())
    class NotAllLoaded(list: List<IDetailsTiDisplay>) : MainActivityState(displayLoading = false, didLoadAll = false, list = list)
    class LoadedAll(list: List<IDetailsTiDisplay>) : MainActivityState(displayLoading = false, didLoadAll = true, list = list)
}