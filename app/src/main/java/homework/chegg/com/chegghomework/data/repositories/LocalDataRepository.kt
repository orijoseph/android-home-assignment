package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.Consts
import homework.chegg.com.chegghomework.data.Article2
import homework.chegg.com.chegghomework.data.CahcedResponse
import homework.chegg.com.chegghomework.data.ResponseA
import homework.chegg.com.chegghomework.data.ResponseB
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class LocalDataRepository : IDataRepository {

    private val cachedData = mutableMapOf<String, CahcedResponse>()

    var responseAInfo: ResponseA? = ResponseA()
    var responseBInfo: ResponseB? = ResponseB()
    var responseCInfo: List<Article2> = listOf()
    var responseATime: Long = -1
    var responseBTime: Long = -1
    var responseCTime: Long = -1

    override fun getStories(): Observable<ResponseA> {
        return Observable.just(responseAInfo)
    }

    fun saveResponseA(response: ResponseA?) {
        responseAInfo = response
        responseATime = System.currentTimeMillis()
    }

    override fun getArticles(): Observable<ResponseB> {
        return Observable.just(responseBInfo)
    }

    fun saveResponseB(response: ResponseB?) {
        responseBInfo = response
        responseBTime = System.currentTimeMillis()
    }

    fun timeHasExpired(exoirationTimeInMinutes: Long, callType:String): Boolean {

        var responseTime = -1L

        when(callType){
            Consts.DATA_SOURCE_A_URL -> {
                responseTime = responseATime
            }

            Consts.DATA_SOURCE_B_URL -> {
                responseTime = responseBTime
            }

            Consts.DATA_SOURCE_C_URL -> {
                responseTime = responseCTime
            }
        }

        val delta = System.currentTimeMillis() - responseTime
        return delta < TimeUnit.MINUTES.toMillis(exoirationTimeInMinutes)
    }

    override fun getArticles2(): Observable<List<Article2>> {
        return Observable.just(responseCInfo)
    }

    fun saveResponseC(response: List<Article2>?) {
        responseCInfo = response!!
        responseCTime = System.currentTimeMillis()
    }
}