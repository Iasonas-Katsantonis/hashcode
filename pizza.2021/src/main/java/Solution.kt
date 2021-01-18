import java.util.ArrayList

class Solution(private val problem: Problem) {
    val teams: MutableList<Team> = ArrayList()

    fun score(): Long {
        val teams = this.teams
        var score = 0L
        val toppings = HashSet<String>()
        for (team in teams) {
            for (pizza in team.pizzas) {
                toppings.addAll(pizza.toppings)
            }
            score += toppings.size * toppings.size
        }
        return score
    }
}