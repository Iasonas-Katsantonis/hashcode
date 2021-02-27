import java.io.File
import java.io.IOException
import java.util.*

class SolverRecursive {
    private var deltaMin = 0
    private var candidateSolution: MutableList<Pizza> = ArrayList()

    @Throws(IOException::class)
    fun solve(fileIn: File?): Solution {
        val parser = ProblemParser()
        val problem = parser.parse(fileIn!!)
        return solve(problem)
    }

    fun solve(problem: Problem): Solution {
        deltaMin = Int.MAX_VALUE
        candidateSolution.clear()
        val solution = Solution(problem)
        val pizzas: List<Pizza> = problem.pizzas
        if (pizzas.isEmpty()) {
            return solution
        }
        val candidates: MutableList<Pizza> = ArrayList()
        val solved = solve(problem.maximumSlicesCount, pizzas, pizzas.size - 1, candidates, 0)
        if (solved != null) {
            solution.pizzas.addAll(solved)
        } else {
            solution.pizzas.addAll(candidateSolution)
        }
        return solution
    }

    private fun solve(
        maximumSlices: Int,
        pizzas: List<Pizza>,
        pizzaIndex: Int,
        candidates: List<Pizza>,
        sumCandidates: Int
    ): List<Pizza>? {
        if (pizzaIndex >= 0) {
            if (candidates.isEmpty()) {
                println("solve: [] $sumCandidates")
            } else {
                println("solve: [" + candidates[0] + ", ...] " + sumCandidates)
            }
            val candidate = pizzas[pizzaIndex]
            val pick: MutableList<Pizza> = ArrayList(candidates)
            pick.add(0, candidate)
            val sumPick = sumCandidates + candidate.slices
            val delta = maximumSlices - sumPick
            if (delta == 0) {
                return pick
            }
            if (delta > 0) {
                if (delta < deltaMin) {
                    deltaMin = delta
                    candidateSolution = pick
                }
                val picked = solve(maximumSlices, pizzas, pizzaIndex - 1, pick, sumPick)
                if (picked != null) {
                    return picked
                }
            }
            val skipped = solve(maximumSlices, pizzas, pizzaIndex - 1, candidates, sumCandidates)
            if (skipped != null) {
                return skipped
            }
        }
        return null
    }
}