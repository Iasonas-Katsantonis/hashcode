import java.io.File
import kotlin.Throws
import java.io.IOException
import java.io.PrintWriter
import java.io.FileWriter

class SolutionWriter {
    @Throws(IOException::class)
    fun write(solution: Solution, fileOut: File) {
        PrintWriter(FileWriter(fileOut)).use { writer ->
            val intersections = solution.intersections

            writer.print(intersections.size)
            writer.print(EOL)
            for (intersection in intersections) {
                writer.print(intersection.index)
                writer.print(EOL)
                val schedule = intersection.schedule
                writer.print(schedule.size)
                writer.print(EOL)
                for (street in schedule.keys) {
                    val t = schedule[street]
                    writer.print(street.name)
                    writer.print(' ')
                    writer.print(t)
                    writer.print(EOL)
                }
            }
            writer.flush()
        }
    }

    companion object {
        private const val EOL = '\n'
    }
}