import java.util.*

class Solver2b : Solver2() {

    override fun solve(problem: Problem): Solution {
        val solution = Solution(problem)
        val pizzas = ArrayList(problem.pizzas)
        if (pizzas.isEmpty()) {
            return solution
        }
        pizzas.sortWith(PizzaSorter())
        val teams = solution.teams

        val delivered2 = solve(pizzas, 2, problem.numberOf2PersonTeams)
        if (delivered2 != null) {
            teams.addAll(delivered2)
        }
        val delivered3 = solve(pizzas, 3, problem.numberOf3PersonTeams)
        if (delivered3 != null) {
            teams.addAll(delivered3)
        }
        val delivered4 = solve(pizzas, 4, problem.numberOf4PersonTeams)
        if (delivered4 != null) {
            teams.addAll(delivered4)
        }

        return solution
    }
}
