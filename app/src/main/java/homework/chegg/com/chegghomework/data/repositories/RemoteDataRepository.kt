package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.data.Article2
import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import homework.chegg.com.chegghomework.network.CheggApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RemoteDataRepository(var cheggApi: CheggApi) :IDataRepository{

    override fun getStories(): Observable<ResponseA> {
        return cheggApi.getDataSourceA()
                .subscribeOn(Schedulers.io())
    }

    override fun getArticles(): Observable<ResponseB> {
        return cheggApi.getDataSourceB()
                .subscribeOn(Schedulers.io())
    }

    override fun getArticles2(): Observable<List<Article2>> {
        return cheggApi.getDataSourceC()
                .subscribeOn(Schedulers.io())
    }
}