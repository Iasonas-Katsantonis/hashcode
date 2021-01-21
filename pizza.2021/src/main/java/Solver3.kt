import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs

class Solver3 {

    fun solve(problem: Problem): Solution {
        val solution = Solution(problem)
        val pizzas = ArrayList(problem.pizzas)
        if (pizzas.isEmpty()) {
            return solution
        }
        pizzas.sortWith(PizzaSorter())
        val teams = solution.teams

        val delivered4 = solve(pizzas, 4, problem.numberOf4PersonTeams)
        if (delivered4 != null) {
            teams.addAll(delivered4)
        }
        val delivered3 = solve(pizzas, 3, problem.numberOf3PersonTeams)
        if (delivered3 != null) {
            teams.addAll(delivered3)
        }
        val delivered2 = solve(pizzas, 2, problem.numberOf2PersonTeams)
        if (delivered2 != null) {
            teams.addAll(delivered2)
        }

        return solution
    }

    private fun solve(
        pizzas: MutableList<Pizza>,
        numberOfTeamMembers: Int,
        numberOfTeams: Int
    ): List<Team>? {
        if (pizzas.isEmpty()) return null
        val teams = ArrayList<Team>()
        val toppings = HashSet<Int>()
        for (t in 0 until numberOfTeams) {
            if (pizzas.size < numberOfTeamMembers) return teams
            val team = Team(numberOfTeamMembers)
            var pizza = pizzas.removeAt(0)
            team.pizzas.add(pizza)
            toppings.addAll(pizza.toppings)
            for (m in 1 until numberOfTeamMembers) {
                pizza = findNextPizza(pizzas, toppings)
                team.pizzas.add(pizza)
                toppings.addAll(pizza.toppings)
            }
            toppings.clear()
            teams.add(team)
        }
        return teams
    }

    private fun findNextPizza(pizzas: MutableList<Pizza>, toppings: Set<Int>): Pizza {
        var next = 0
        var deltaMin = Int.MAX_VALUE
        var delta: Int
        var pizza: Pizza
        for (i in pizzas.indices) {
            pizza = pizzas[i]
            delta = compare(toppings, pizza)
            if (delta == 0) {
                next = i
                break
            }
            if (abs(delta) < deltaMin) {
                deltaMin = delta
                next = i
            }
        }
        return pizzas.removeAt(next)
    }

    private val toppingsCompare = HashSet<Int>()

    private fun compare(toppings: Set<Int>, pizza: Pizza): Int {
        val t1 = toppings
        val t2 = pizza.toppings
        val s1 = t1.size
        val s2 = t2.size

        val tTotal = s1 + s2

        toppingsCompare.clear()
        toppingsCompare.addAll(t1)
        toppingsCompare.addAll(t2)
        val tDistinct = toppingsCompare.size

        return tTotal - tDistinct
    }

    /** Larger toppings first. */
    inner class PizzaSorter : Comparator<Pizza> {
        private val toppings = HashSet<Int>()

        override fun compare(o1: Pizza, o2: Pizza): Int {
            val t1 = o1.toppings
            val t2 = o2.toppings
            return -t1.compareTo(t2)
        }
    }
}
