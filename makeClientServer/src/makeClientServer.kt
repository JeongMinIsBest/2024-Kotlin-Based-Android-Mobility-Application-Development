import java.io.*
import java.net.*
import java.nio.charset.Charset
import java.util.*

fun main() {

    try {

        println("client started")

        val serverlp: String = "127.0.0.1";
        val socket: Socket = Socket(serverlp, 40067)
        println("client is connected to $serverlp")

        //입력 스트링

        var ins: InputStream = socket.getInputStream()
        var dis: DataInputStream = DataInputStream(ins)
        var outs: OutputStream = socket.getOutputStream()
        var dos: DataOutputStream = DataOutputStream(outs)

        while (true) {

            print("(Q)uit, (L)ist, (G)et>")
            val reader = Scanner(System.`in`)
            val input: String = reader.nextLine().toLowerCase()
            when (input) {

                "q" -> break
                "l" -> {

                    dos.writeInt(10)

                    val param1Length = dis.readInt();
                    println("received param1Length($param1Length)");

                    if (param1Length > 0) {

                        val buff: ByteArray = ByteArray(param1Length);
                        dis.readFully(buff, 0, param1Length)
                        println("received file list");
                        val fileList: String = String(buff, 0, param1Length)
                        println(fileList)

                    }
                }

                "g" -> {
                    dos.writeInt(20)
                    val fileName: String = "aa.txt"
                    val arr = fileName.toByteArray(Charset.defaultCharset())
                    println("sent param1Length(${arr.size}");
                    dos.writeInt(arr.size)
                    dos.write(arr)

                    val param1Length = dis.readInt();
                    println("Received param1Length($param1Length)");
                    if (param1Length > 0) {
                        val buff: ByteArray = ByteArray(param1Length);
                        dis.readFully(buff, 0, param1Length)
                        println("received file content");
                        val content: String = String(buff, 0, param1Length)
                        println(content)
                    }
                }

                else -> {
                    println("Unknown command $input")
                }
            }
        }
        dis.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}