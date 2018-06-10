
import android.util.Log
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

class CoroutineGroupByAwait(vararg funs: () -> Unit?) {

  private val deferred: List<Deferred<Unit?>> = funs.map { f ->
    async { f() }
  }

  fun await() {
    runBlocking {
      deferred.forEach { it.await() }
    }
  }
}
