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

//        var solution: Solution
//        var scoreMax: Long
//
//        val solver1 = Solver1()
//        val solution1 = solver1.solve(problem)
//        val score1 = solution1.score()
//        println("$name: solution 1: $score1")
//        solution = solution1
//        scoreMax = score1
//
//        val solver1b = Solver1b()
//        val solution1b = solver1b.solve(problem)
//        val score1b = solution1b.score()
//        println("$name: solution 1b: $score1b")
//        if (score1b > scoreMax) {
//            solution = solution1b
//            scoreMax = score1b
//        }
//
//        val solver2 = Solver2()
//        val solution2 = solver2.solve(problem)
//        val score2 = solution2.score()
//        println("$name: solution 2: $score2")
//        if (score2 > scoreMax) {
//            solution = solution2
//            scoreMax = score2
//        }
//
//        val solver2b = Solver2b()
//        val solution2b = solver2b.solve(problem)
//        val score2b = solution2b.score()
//        println("$name: solution 2b: $score2b")
//        if (score2b > scoreMax) {
//            solution = solution2b
//            scoreMax = score2b
//        }
//
//        val solver3 = Solver3()
//        val solution3 = solver3.solve(problem)
//        val score3 = solution3.score()
//        println("$name: solution 3: $score3")
//        if (score3 > scoreMax) {
//            solution = solution3
//            scoreMax = score3
//        }
//
//        val solver3b = Solver3b()
//        val solution3b = solver3b.solve(problem)
//        val score3b = solution3b.score()
//        println("$name: solution 3b: $score3b")
//        if (score3b > scoreMax) {
//            solution = solution3b
//            scoreMax = score3b
//        }
//
//        val solver4 = Solver4()
//        val solution4 = solver4.solve(problem)
//        val score4 = solution4.score()
//        println("$name: solution 4: $score4")
//        if (score4 > scoreMax) {
//            solution = solution4
//            scoreMax = score4
//        }
//
//        val solver4b = Solver4b()
//        val solution4b = solver4b.solve(problem)
//        val score4b = solution4b.score()
//        println("$name: solution 4b: $score4b")
//        if (score4b > scoreMax) {
//            solution = solution4b
//            scoreMax = score4b
//        }
//
//        println("$name: solution: $scoreMax")
//        SolutionWriter().write(solution, fileOut)
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