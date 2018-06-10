
import android.util.Log

class ThreadGroup(vararg funs: () -> Unit?) {

  private val threads: List<Thread> = funs.map {
    Thread {
      it()
    }
  }

  fun start() = threads.forEach { it.start() }

  fun join() = threads.forEach {
    try {
      it.join()
    } catch (e: InterruptedException) {
      Log.d("ThreadGroup", e.message)
    }
  }
}
