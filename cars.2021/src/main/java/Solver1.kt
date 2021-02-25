import java.util.Comparator

open class Solver1 {

    open fun solve(problem: Problem): Solution {
        val solution = Solution(problem)
        val cars = ArrayList(problem.cars)
        if (cars.isEmpty()) {
            return solution
        }
        cars.sortWith(CarSorter())

        val maxTime = problem.simulationDuration
        var time = 0
        var intersection: Intersection
        val intersections = HashMap<Int, Intersection>()

        for (car in cars) {
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
                // TODO if (time >= maxTime) break
            }
            // TODO if (time >= maxTime) break
        }

        solution.intersections.addAll(intersections.values)

        return solution
    }


    /** Smaller path first. */
    inner class CarSorter : Comparator<Car> {
        override fun compare(o1: Car, o2: Car): Int {
            val p1 = o1.path
            val p2 = o2.path
            return p1.compareTo(p2)
        }
    }

}