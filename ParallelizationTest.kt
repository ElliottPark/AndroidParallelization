
import android.util.Log

/**
  Results:
 Testing ThreadGroup
 Starting testOne: 11
 Starting testTwo: 15
 Starting testThree: 23
 Finishing testOne: 112
 Finishing testThree: 525
 Finishing testTwo: 1016
 Done Testing ThreadGroup 1017

 Testing CoroutineGroupByAwait
 Starting testOne: 15
 Starting testTwo: 17
 Starting testThree: 19
 Finishing testOne: 118
 Finishing testThree: 524
 Finishing testTwo: 1018
 Done Testing CoroutineGroupByAwait 1019

 Testing CoroutineGroupByJoin
 Done Testing CoroutineGroupByJoin 6
 Starting testOne: 6
 Starting testTwo: 6
 Starting testThree: 6
 Finishing testOne: 107
 Finishing testThree: 507
 Finishing testTwo: 1007
 */
class ParallelizationTest {

  private var startTime = 0L

  private fun elapsedTime(): Long {
    return (System.currentTimeMillis() - startTime)
  }

  fun runTests() {
    startTime = System.currentTimeMillis()
    Log.d("test_log", "Testing ThreadGroup")
    val threadGroup = ThreadGroup({testOne()}, {testTwo()}, {testThree()})
    threadGroup.start()
    threadGroup.join()
    Log.d("test_log", "Done Testing ThreadGroup ${elapsedTime()}")

    startTime = System.currentTimeMillis()
    Log.d("test_log", "Testing CoroutineGroupByAwait")
    val coroutineGroupByAwait = CoroutineGroupByAwait({testOne()}, {testTwo()}, {testThree()})
    coroutineGroupByAwait.await()
    Log.d("test_log", "Done Testing CoroutineGroupByAwait ${elapsedTime()}")

    startTime = System.currentTimeMillis()
    Log.d("test_log", "Testing CoroutineGroupByJoin")
    val coroutineGroupByJoin = CoroutineGroupByJoin({testOne()}, {testTwo()}, {testThree()})
    Log.d("test_log", "Done Testing CoroutineGroupByJoin ${elapsedTime()}")

  }

  private fun testOne() {
    Log.d("test_log", "Starting testOne: ${elapsedTime()}")
    Thread.sleep(100)
    Log.d("test_log", "Finishing testOne: ${elapsedTime()}")
  }

  private fun testTwo() {
    Log.d("test_log", "Starting testTwo: ${elapsedTime()}")
    Thread.sleep(1000)
    Log.d("test_log", "Finishing testTwo: ${elapsedTime()}")
  }

  private fun testThree() {
    Log.d("test_log", "Starting testThree: ${elapsedTime()}")
    Thread.sleep(500)
    Log.d("test_log", "Finishing testThree: ${elapsedTime()}")
  }

}
