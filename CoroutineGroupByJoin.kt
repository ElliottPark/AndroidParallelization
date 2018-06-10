
import android.util.Log
import kotlinx.coroutines.experimental.*

class CoroutineGroupByJoin(vararg funs: () -> Unit?) {

  private val jobs: List<Job> = funs.map {
    launch { it() }
  }

  fun join() {
    runBlocking {
      jobs.forEach {
        it.join()
      }
    }
  }
}
