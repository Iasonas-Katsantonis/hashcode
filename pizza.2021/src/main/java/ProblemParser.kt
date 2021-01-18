import kotlin.Throws
import java.io.IOException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

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
            var pizzaToppings: MutableSet<String>
            for (i in 1..pizzasCount) {
                row = reader.readLine()
                rowTokens = row.split(" ")
                pizzaToppings = TreeSet<String>()
                for (j in 1 until rowTokens.size) {
                    pizzaToppings.add(rowTokens[j])
                }
                s.add(Pizza(i, pizzaToppings))
            }
        }
        return problem
    }
}