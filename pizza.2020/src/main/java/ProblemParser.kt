import kotlin.Throws
import java.io.IOException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ProblemParser {
    @Throws(IOException::class)
    fun parse(fileIn: File): Problem {
        val problem = Problem()
        val s = problem.pizzas
        BufferedReader(FileReader(fileIn)).use { reader ->
            val row1 = reader.readLine()
            val row1Tokens = row1.split(" ").toTypedArray()
            problem.maximumSlicesCount = row1Tokens[0].toInt()
            val pizzaTypesCount = row1Tokens[1].toInt()
            val row2 = reader.readLine()
            val row2Tokens = row2.split(" ").toTypedArray()
            for (i in 0 until pizzaTypesCount) {
                s.add(Pizza(i, row2Tokens[i].toInt()))
            }
        }
        return problem
    }
}