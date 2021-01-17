import java.io.File
import java.io.IOException

class MorePizzaRecursive(private val name: String, private val fileIn: File, private val fileOut: File) : Runnable {
    override fun run() {
        try {
            solve(fileIn, fileOut)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun solve(fileIn: File, fileOut: File) {
        val solver = SolverRecursive()
        val solution = solver.solve(fileIn)
        println(name + ": solution: " + solution.score())
        SolutionWriter().write(solution, fileOut)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            for (arg in args) {
                var filename = arg
                val fileIn = File(filename)
                val indexExt = filename.lastIndexOf('.')
                if (indexExt >= 0) {
                    filename = filename.substring(0, indexExt)
                }
                val fileOut = File("$filename.out")
                val slices = MorePizzaRecursive(filename, fileIn, fileOut)
                Thread(slices).start()
            }
        }
    }
}