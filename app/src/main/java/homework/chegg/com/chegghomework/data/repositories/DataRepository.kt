package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.Consts
import homework.chegg.com.chegghomework.data.Article2
import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DataRepository(val local: LocalDataRepository,
                     val remote: RemoteDataRepository) : IDataRepository {


    override fun getStories(): Observable<ResponseA> {
        return local.getStories()
                .subscribeOn(Schedulers.io())
                .flatMap { t ->
                    if (t.stories.isNullOrEmpty()) {
                        return@flatMap getStoriesFromServer()
                    } else {
                        if (local.timeHasExpired(5, Consts.DATA_SOURCE_A_URL)) {
                            return@flatMap Observable.just(t)
                        } else {
                            return@flatMap getStoriesFromServer()
                        }
                    }
                }

    }

    private fun getStoriesFromServer() = remote.getStories()
            .doOnNext { response -> local.saveResponseA(response) }

    override fun getArticles(): Observable<ResponseB> {
        return local.getArticles()
                .subscribeOn(Schedulers.io())
                .flatMap { t ->
                    if (t.metadata?.articles.isNullOrEmpty()) {
                        return@flatMap getArticlesFromServer()
                    } else {
                        if (local.timeHasExpired(30, Consts.DATA_SOURCE_B_URL)) {
                            return@flatMap Observable.just(t)
                        } else {
                            return@flatMap getArticlesFromServer()
                        }
                    }
                }
    }

    private fun getArticlesFromServer() = remote.getArticles().doOnNext { response ->
        local.saveResponseB(response)
    }


    override fun getArticles2(): Observable<List<Article2>> {
        return local.getArticles2()
                .subscribeOn(Schedulers.io())
                .flatMap { t ->
                    if (t.isNullOrEmpty()) {
                        return@flatMap getArticles2FromServer()
                    } else {
                        if (local.timeHasExpired(60, Consts.DATA_SOURCE_C_URL)) {
                            return@flatMap Observable.just(t)
                        } else {
                            return@flatMap getArticles2FromServer()
                        }
                    }
                }
    }


    private fun getArticles2FromServer() = remote.getArticles2().doOnNext { response ->
        local.saveResponseC(response)
    }
}