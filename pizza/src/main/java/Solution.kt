import java.util.ArrayList

class Solution(private val problem: Problem) {
    val pizzas: MutableList<Pizza> = ArrayList()

    fun score(): Int {
        val pizzas = this.pizzas
        var score = 0
        for (pizza in pizzas) {
            score += pizza.slices
        }
        return problem.maximumSlicesCount - score
    }
}