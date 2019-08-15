package homework.chegg.com.chegghomework.network

import homework.chegg.com.chegghomework.Consts
import homework.chegg.com.chegghomework.data.Article2
import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import io.reactivex.Observable
import retrofit2.http.GET

interface CheggApi {

    @GET(Consts.DATA_SOURCE_A_URL)
    fun getDataSourceA(): Observable<ResponseA>

    @GET(Consts.DATA_SOURCE_B_URL)
    fun getDataSourceB(): Observable<ResponseB>

    @GET(Consts.DATA_SOURCE_C_URL)
    fun getDataSourceC(): Observable<List<Article2>>
}