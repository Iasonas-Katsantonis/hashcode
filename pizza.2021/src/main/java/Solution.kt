import java.util.ArrayList

class Solution(private val problem: Problem) {
    val teams: MutableList<Team> = ArrayList()

    fun score(): Long {
        val teams = this.teams
        var score = 0L
        val toppings = HashSet<Int>()
        val pizzasDelivered = HashSet<Int>()

        for (team in teams) {
            toppings.clear()
            assert(team.numberOfMembers == team.pizzas.size, "size mismatch: $team")
            for (pizza in team.pizzas) {
                toppings.addAll(pizza.toppings)
                assert(!pizzasDelivered.contains(pizza.index), "$pizza delivered")
                pizzasDelivered.add(pizza.index)
            }
            score += toppings.size * toppings.size
        }
        assert(pizzasDelivered.size <= problem.numberOfPizzas, "${pizzasDelivered.size} <= ${problem.numberOfPizzas}")
        return score
    }

    private fun assert(value: Boolean, message: String) {
        if (!value) {
            throw AssertionError(message)
        }
    }
}