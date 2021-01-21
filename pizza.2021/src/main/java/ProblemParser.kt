import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class ProblemParser {
    @Throws(IOException::class)
    fun parse(fileIn: File): Problem {
        val problem = Problem()
        val s = problem.pizzas
        BufferedReader(FileReader(fileIn)).use { reader ->
            val row1 = reader.readLine()
            val row1Tokens = row1.split(" ").toTypedArray()
            problem.numberOfPizzas = row1Tokens[0].toInt()
            problem.numberOf2PersonTeams = row1Tokens[1].toInt()
            problem.numberOf3PersonTeams = row1Tokens[2].toInt()
            problem.numberOf4PersonTeams = row1Tokens[3].toInt()
            val pizzasCount = problem.numberOfPizzas
            var row: String
            var rowTokens: List<String>
            var pizzaTopping: String
            var pizzaToppings: MutableSet<Int>
            val pizzaToppingsIndices = ArrayList<String>()
            var pizzaToppingIndex: Int

            for (i in 0 until pizzasCount) {
                row = reader.readLine()
                rowTokens = row.split(" ")
                pizzaToppings = HashSet()
                for (j in 1 until rowTokens.size) {
                    pizzaTopping = rowTokens[j]
                    pizzaToppingIndex = pizzaToppingsIndices.indexOf(pizzaTopping)
                    if (pizzaToppingIndex < 0) {
                        pizzaToppingIndex = pizzaToppingsIndices.size
                        pizzaToppingsIndices.add(pizzaTopping)
                    }
                    pizzaToppings.add(pizzaToppingIndex)
                }
                s.add(Pizza(i, pizzaToppings.toList()))
            }
        }
        return problem
    }
}