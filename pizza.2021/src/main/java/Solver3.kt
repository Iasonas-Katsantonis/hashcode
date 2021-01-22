import java.util.*
import kotlin.collections.HashSet

class Solver3 {

    fun solve(problem: Problem): Solution {
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

    private fun findNextPizza(pizzas: MutableList<Pizza>, toppings: Collection<Int>): Pizza {
        var next = 0
        var deltaMax = 0
        var delta: Int
        var sizeMin = Int.MAX_VALUE
        var pizza: Pizza
        var pizzaSize: Int
        for (i in 1 until pizzas.size) {
            pizza = pizzas[i]
            delta = compare(toppings, pizza.toppings)
            if (delta > deltaMax) {
                deltaMax = delta
                pizzaSize = pizza.toppings.size
                sizeMin = pizzaSize
                next = i//TODO if (sizeMin <= 1) break
            } else if (delta == deltaMax) {
                pizzaSize = pizza.toppings.size
                if (pizzaSize < sizeMin) {
                    sizeMin = pizzaSize
                    next = i
                    if (sizeMin <= 1) break
                }
            }
        }
        return pizzas.removeAt(next)
    }

    private val toppingsCompare = HashSet<Int>()

    private fun compare(t1: Collection<Int>, t2: Collection<Int>): Int {
        val s1 = t1.size

        toppingsCompare.clear()
        toppingsCompare.addAll(t1)
        toppingsCompare.addAll(t2)
        val s3 = toppingsCompare.size

        // How much does the new pizza contribute?
        return s3 - s1
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
