package otus.homework.flowcats

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import otus.homework.flowcats.Result

class CatsRepository(
    private val catsService: CatsService,
    private val refreshIntervalMs: Long = 5000
) {

    fun listenForCatFacts() = flow {
        while (true) {
            try {
                val latestNews = Result.Success(catsService.getCatFact())
                emit(latestNews)
            }
            catch (e: Exception)
            {
                val error = Result.Error(e.message.toString())
                emit(error)
            }
            delay(refreshIntervalMs)
        }
    }
}