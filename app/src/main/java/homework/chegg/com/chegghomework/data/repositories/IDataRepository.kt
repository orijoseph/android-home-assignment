package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import homework.chegg.com.chegghomework.data.Article2
import io.reactivex.Observable

interface IDataRepository {
    fun getStories(): Observable<ResponseA>

    fun getArticles(): Observable<ResponseB>

    fun getArticles2(): Observable<List<Article2>>
}