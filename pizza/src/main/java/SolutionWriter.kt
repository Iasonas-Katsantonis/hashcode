import java.io.File
import kotlin.Throws
import java.io.IOException
import java.io.PrintWriter
import java.io.FileWriter

class SolutionWriter {
    @Throws(IOException::class)
    fun write(solution: Solution, fileOut: File) {
        PrintWriter(FileWriter(fileOut)).use { writer ->
            val pizzas: List<Pizza> = solution.pizzas
            val k = pizzas.size
            writer.print(k)
            writer.print(EOL)
            for (i in 0 until k) {
                if (i > 0) {
                    writer.print(' ')
                }
                writer.print(pizzas[i].index)
            }
            writer.print(EOL)
            writer.flush()
        }
    }

    companion object {
        private const val EOL = '\n'
    }
}