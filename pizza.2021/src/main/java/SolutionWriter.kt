import java.io.File
import kotlin.Throws
import java.io.IOException
import java.io.PrintWriter
import java.io.FileWriter

class SolutionWriter {
    @Throws(IOException::class)
    fun write(solution: Solution, fileOut: File) {
        PrintWriter(FileWriter(fileOut)).use { writer ->
            val teams = solution.teams

            writer.print(teams.size)
            writer.print(EOL)
            for (team in teams) {
                writer.print(team.numberOfMembers)
                for (i in team.pizzas.indices) {
                    writer.print(' ')
                    writer.print(team.pizzas[i].index)
                }
                writer.print(EOL)
            }
            writer.flush()
        }
    }

    companion object {
        private const val EOL = '\n'
    }
}