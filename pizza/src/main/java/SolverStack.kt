import java.io.File
import java.io.IOException
import java.util.*

class SolverStack {
    private class SolveStack(val pizzaIndex: Int, val candidates: List<Pizza>, val sumCandidates: Int)

    private var deltaMin = 0
    private var candidateSolution: MutableList<Pizza> = ArrayList()
    private val stack: MutableList<SolveStack> = ArrayList()

    @Throws(IOException::class)
    fun solve(fileIn: File?): Solution {
        val parser = ProblemParser()
        val problem = parser.parse(fileIn!!)
        return solve(problem)
    }

    fun solve(problem: Problem): Solution {
        deltaMin = Int.MAX_VALUE
        candidateSolution.clear()
        stack.clear()
        val solution = Solution(problem)
        val pizzas: List<Pizza> = problem.pizzas
        if (pizzas.isEmpty()) {
            return solution
        }
        val candidates: List<Pizza> = ArrayList()
        val stackElement = SolveStack(pizzas.size - 1, candidates, 0)
        stack.add(stackElement)
        val solved = solve(problem.maximumSlicesCount, pizzas)
        if (solved != null) {
            solution.pizzas.addAll(solved)
        } else {
            solution.pizzas.addAll(candidateSolution)
        }
        return solution
    }

    private fun solve(maximumSlices: Int, pizzas: List<Pizza>): List<Pizza>? {
        var stackElement: SolveStack
        var pizzaIndex: Int
        var candidates: List<Pizza>
        var sumCandidates: Int
        var gcCounter = 0
        while (stack.size > 0) {
            stackElement = stack.removeAt(0)
            pizzaIndex = stackElement.pizzaIndex
            candidates = stackElement.candidates
            sumCandidates = stackElement.sumCandidates
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
                val skip = candidates
                stackElement = SolveStack(pizzaIndex - 1, skip, sumCandidates)
                stack.add(0, stackElement)
                if (delta > 0) {
                    if (delta < deltaMin) {
                        deltaMin = delta
                        candidateSolution = pick
                    }
                    stackElement = SolveStack(pizzaIndex - 1, pick, sumPick)
                    stack.add(0, stackElement)
                }
            }
            gcCounter++
            if (gcCounter >= 100) {
                gcCounter = 0
                System.gc()
            }
        }
        return null
    }
}