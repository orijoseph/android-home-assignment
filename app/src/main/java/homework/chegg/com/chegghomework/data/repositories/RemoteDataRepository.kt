package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import homework.chegg.com.chegghomework.network.CheggApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RemoteDataRepository(var cheggApi: CheggApi) : IDataRepository {

    override fun getStories(): Observable<List<IDetailsTiDisplay>> {
        return cheggApi.getDataSourceA()
                .subscribeOn(Schedulers.io())
                .map { it.stories }
    }

    override fun getArticles(): Observable<List<IDetailsTiDisplay>> {
        return cheggApi.getDataSourceB()
                .subscribeOn(Schedulers.io())
                .map { it.metadata?.articles }
    }

    override fun getArticles2(): Observable<List<IDetailsTiDisplay>> {
        return cheggApi.getDataSourceC()
                .subscribeOn(Schedulers.io())
                .map { it }
    }
}