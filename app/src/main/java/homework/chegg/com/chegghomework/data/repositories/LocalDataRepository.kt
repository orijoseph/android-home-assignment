package homework.chegg.com.chegghomework.data.repositories

import homework.chegg.com.chegghomework.data.CahcedResponse
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay
import java.util.concurrent.TimeUnit

class LocalDataRepository {

    private val cachedData = mutableMapOf<String, CahcedResponse>()

    fun getCachedData(url: String): List<IDetailsTiDisplay>? {
        return cachedData[url]?.cachedData
    }

    fun saveResponse(url: String, data: List<IDetailsTiDisplay>?) {
        cachedData[url] = CahcedResponse(System.currentTimeMillis(), data)
    }

    fun isTimeHasNotExpired(exoirationTimeInMinutes: Long, callType: String): Boolean {

        var responseTime = cachedData[callType]?.lastEntery!!

        val delta = System.currentTimeMillis() - responseTime
        return delta < TimeUnit.MINUTES.toMillis(exoirationTimeInMinutes)
    }
}