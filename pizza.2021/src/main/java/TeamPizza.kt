import java.io.File
import java.io.IOException

class TeamPizza(private val name: String, private val fileIn: File, private val fileOut: File) : Runnable {
    override fun run() {
        try {
            solve(fileIn, fileOut)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun solve(fileIn: File, fileOut: File) {
        var solution: Solution
        var scoreMax = 0L

        val solver1 = Solver1()
        val solution1 = solver1.solve(fileIn)
        val score1 = solution1.score()
        println("$name: solution 1: $score1")
        solution = solution1
        scoreMax = score1

        val solver2 = Solver2()
        val solution2 = solver2.solve(fileIn)
        val score2 = solution2.score()
        println("$name: solution 2: $score2")
        if (score2 > scoreMax) {
            solution = solution2
            scoreMax = score2
        }

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
                val pizzas = TeamPizza(filename, fileIn, fileOut)
                pizzas.run()
            }
        }
    }
}