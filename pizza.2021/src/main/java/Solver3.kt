import java.util.*
import kotlin.collections.HashSet

open class Solver3 {

    open fun solve(problem: Problem): Solution {
        val solution = Solution(problem)
        val pizzas = ArrayList(problem.pizzas)
        if (pizzas.isEmpty()) {
            return solution
        }
        pizzas.sortWith(PizzaSorter())
        val teams = solution.teams

        println("$this solve for 4 members...")
        val delivered4 = solve(pizzas, 4, problem.numberOf4PersonTeams)
        if (delivered4 != null) {
            teams.addAll(delivered4)
        }
        println("$this solve for 3 members...")
        val delivered3 = solve(pizzas, 3, problem.numberOf3PersonTeams)
        if (delivered3 != null) {
            teams.addAll(delivered3)
        }
        println("$this solve for 2 members...")
        val delivered2 = solve(pizzas, 2, problem.numberOf2PersonTeams)
        if (delivered2 != null) {
            teams.addAll(delivered2)
        }

        return solution
    }

    protected fun solve(
        pizzas: MutableList<Pizza>,
        numberOfTeamMembers: Int,
        numberOfTeams: Int
    ): List<Team>? {
        if (pizzas.isEmpty()) return null
        val teams = ArrayList<Team>()
        for (t in 0 until numberOfTeams) {
            if (pizzas.size < numberOfTeamMembers) return teams
            val team = Team(numberOfTeamMembers)
            for (m in 0 until numberOfTeamMembers) {
                val pizza = pizzas.removeAt(0)
                team.pizzas.add(pizza)
            }
            teams.add(team)
        }
        return teams
    }

    private val toppingsCompare = HashSet<Int>()

    /** Larger toppings first. */
    inner class PizzaSorter : Comparator<Pizza> {
        override fun compare(o1: Pizza, o2: Pizza): Int {
            val t1 = o1.toppings
            val t2 = o2.toppings
            return -t1.compareTo(t2)
        }
    }

    override fun toString(): String {
        return javaClass.simpleName
    }
}
