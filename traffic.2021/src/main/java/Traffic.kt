import java.io.File
import java.io.IOException

class Traffic(private val name: String, private val fileIn: File, private val fileOut: File) : Runnable {
    override fun run() {
        try {
            solve(fileIn, fileOut)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun solve(fileIn: File, fileOut: File) {
        val parser = ProblemParser()
        val problem = parser.parse(fileIn)

        var solution: Solution
        var scoreMax: Long

        val solver1 = Solver1()
        val solution1 = solver1.solve(problem)
        val score1 = solution1.score()
        println("$name: solution 1: $score1")
        solution = solution1
        scoreMax = score1

        println("$name: solution: $scoreMax")
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
                val teamPizza = Traffic(filename, fileIn, fileOut)
                Thread(teamPizza).start()
            }
        }
    }
}