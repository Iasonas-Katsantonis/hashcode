open class Solver1 {

    open fun solve(problem: Problem): Solution {
        val solution = Solution(problem)

        val maxTime = problem.simulationDuration
        var time = 0
        var intersection: Intersection
        val intersections = HashMap<Int, Intersection>()

        for (car in problem.cars) {
            for (street in car.path.streets) {
                val intersectionId = street.endIntersection
                var inter = intersections[intersectionId]
                if (inter == null) {
                    inter = Intersection(intersectionId)
                    intersections[intersectionId] = inter
                }
                intersection = inter
                intersection.schedule[street] = 1

                time += street.duration
                if (time >= maxTime) break
            }
            if (time >= maxTime) break
        }

        solution.intersections.addAll(intersections.values)

        return solution
    }
}