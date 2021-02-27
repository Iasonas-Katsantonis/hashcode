data class Intersection(val index: Int, val schedule: LinkedHashMap<Street, Int> = LinkedHashMap()) {
    var carsPerStreet = HashMap<Street, Int>()
}