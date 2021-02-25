class Problem {
    /**
     * the duration of the simulation, in seconds.
     */
    var simulationDuration = 0

    /**
     * the number of intersections (with IDs from 0 to I-1).
     */
    var numberOfIntersections = 0

    /**
     * the streets.
     */
    val streets = ArrayList<Street>()

    /**
     * the cars.
     */
    val cars = ArrayList<Car>()

    /**
     * the bonus points for each car that reaches its destination before time D.
     */
    var bonus = 0
}