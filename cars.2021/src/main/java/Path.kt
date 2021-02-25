data class Path(val index: Int, val streets: List<Street>) : Comparable<Path> {
    override fun compareTo(other: Path): Int {
        var c = this.streetTime - other.streetTime
        if (c != 0) return c
        c = this.streets.compareTo(other.streets)
        if (c != 0) return c
        return this.index - other.index
    }

    private val streetTime = calculateStreetTime()

    private fun calculateStreetTime(): Int {
        return streets.sumBy { it.duration }
    }
}