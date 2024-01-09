// import kotlin.concurrent.thread

var obj: Int = 1

class PrintThread: Thread() {

    override fun run() {
        for(i in 1 .. 100 step 2) {

            while(true) {
                var PrintThread: Boolean = false

                synchronized(obj) {

                    if (i <= obj) {
                        println("this is no " + i + " (" + id + ")")
                        obj++
                        PrintThread = true
                    }
                }

                if(PrintThread) break
            }
        }

    }
}

class PrintThread2: Thread() {

    override fun run() {

        for (i in 2..100 step 2) {

            while (true) {

                var PrintThread2: Boolean = false

                synchronized(obj) {

                    if (i <= obj) {
                        println("this is no " + i + " (" + id + ")")
                        obj++
                        PrintThread2 = true
                    }
                }

                if (PrintThread2) break

            }
        }
    }
}

fun main() {

    val thread1 = PrintThread()
    val thread2 = PrintThread2()

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

}
