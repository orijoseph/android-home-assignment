package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.Consts
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import io.reactivex.Observable

class DataRepository(val local: LocalDataRepository,
                     val remote: RemoteDataRepository) : IDataRepository {

    override fun getStories(): Observable<List<IDetailsTiDisplay>> {
        if (local.getCachedData(Consts.DATA_SOURCE_A_URL).isNullOrEmpty()) {
            return getStoriesFromServer()
        } else {
            return if (local.isTimeHasNotExpired(Consts.EXPIRATION_A_MINUTES, Consts.DATA_SOURCE_A_URL)) {
                Observable.just(local.getCachedData(Consts.DATA_SOURCE_A_URL))
            } else {
                return getStoriesFromServer()
            }
        }
    }

    private fun getStoriesFromServer(): Observable<List<IDetailsTiDisplay>> {
        return remote.getStories()
                .doOnNext { response ->
                    local.saveResponse(Consts.DATA_SOURCE_A_URL, response)
                }
    }

    override fun getArticles(): Observable<List<IDetailsTiDisplay>> {
        if (local.getCachedData(Consts.DATA_SOURCE_B_URL).isNullOrEmpty()) {
            return getArticlesFromServer()
        } else {
            return if (local.isTimeHasNotExpired(Consts.EXPIRATION_B_MINUTES, Consts.DATA_SOURCE_B_URL)) {
                Observable.just(local.getCachedData(Consts.DATA_SOURCE_B_URL))
            } else {
                return getArticlesFromServer()
            }
        }
    }

    private fun getArticlesFromServer(): Observable<List<IDetailsTiDisplay>> {
        return remote.getArticles()
                .doOnNext { response ->
                    local.saveResponse(Consts.DATA_SOURCE_B_URL, response)
                }
    }

    override fun getArticles2(): Observable<List<IDetailsTiDisplay>> {
        if (local.getCachedData(Consts.DATA_SOURCE_C_URL).isNullOrEmpty()) {
            return getArticles2FromServer()
        } else {
            return if (local.isTimeHasNotExpired(Consts.EXPIRATION_C_MINUTES, Consts.DATA_SOURCE_C_URL)) {
                Observable.just(local.getCachedData(Consts.DATA_SOURCE_C_URL))
            } else {
                return getArticles2FromServer()
            }
        }
    }

    private fun getArticles2FromServer(): Observable<List<IDetailsTiDisplay>> {
        return remote.getArticles2()
                .doOnNext { response ->
                    local.saveResponse(Consts.DATA_SOURCE_C_URL, response)
                }
    }
}