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

        val solver2 = Solver2()
        val solution2 = solver2.solve(problem)
        val score2 = solution2.score()
        println("$name: solution 2: $score2")
        if (score2 > scoreMax) {
            solution = solution2
            scoreMax = score2
        }

        val solver3 = Solver3()
        val solution3 = solver3.solve(problem)
        val score3 = solution3.score()
        println("$name: solution 3: $score3")
        if (score3 > scoreMax) {
            solution = solution3
            scoreMax = score3
        }

        val solver4 = Solver4()
        val solution4 = solver4.solve(problem)
        val score4 = solution4.score()
        println("$name: solution 4: $score4")
        if (score4 > scoreMax) {
            solution = solution4
            scoreMax = score4
        }

        val solver5 = Solver5()
        val solution5 = solver5.solve(problem)
        val score5 = solution5.score()
        println("$name: solution 5: $score5")
        if (score5 > scoreMax) {
            solution = solution5
            scoreMax = score5
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