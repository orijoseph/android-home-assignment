package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import io.reactivex.Observable

interface IDataRepository {
    fun getStories(): Observable<List<IDetailsTiDisplay>>

    fun getArticles(): Observable<List<IDetailsTiDisplay>>

    fun getArticles2(): Observable<List<IDetailsTiDisplay>>
}