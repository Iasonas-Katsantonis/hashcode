import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class ProblemParser {
    @Throws(IOException::class)
    fun parse(fileIn: File): Problem {
        val problem = Problem()

        BufferedReader(FileReader(fileIn)).use { reader ->
            val row1 = reader.readLine()
            val row1Tokens = row1.split(" ").toTypedArray()
            problem.simulationDuration = row1Tokens[0].toInt()
            problem.numberOfIntersections = row1Tokens[1].toInt()
            val streetCount = row1Tokens[2].toInt()
            val carCount = row1Tokens[3].toInt()
            problem.bonus = row1Tokens[4].toInt()

            var row: String
            var rowTokens: List<String>
            val streets = HashMap<String, Street>()

            var streetId = 0
            for (i in 0 until streetCount) {
                row = reader.readLine()
                rowTokens = row.split(" ")
                val streetStart = rowTokens[0].toInt()
                val streetEnd = rowTokens[1].toInt()
                val streetName = rowTokens[2]
                val streetDuration = rowTokens[3].toInt()

                val street = Street(streetId, streetName, streetStart, streetEnd, streetDuration)
                problem.streets.add(street)
                streetId++
                streets[streetName] = street
            }
            for (i in 0 until carCount) {
                row = reader.readLine()
                rowTokens = row.split(" ")

                val p = rowTokens[0].toInt()
                val pathStreets = ArrayList<Street>()
                for (pp in 1..p) {
                    val streetName = rowTokens[pp]
                    val street = streets[streetName]!!
                    pathStreets.add(street)
                }
                val path = Path(i, pathStreets)
                val car = Car(i, path)
                problem.cars.add(car)
            }
        }
        return problem
    }
}