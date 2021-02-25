data class Path(val index: Int, val streets: List<Street>) : Comparable<Path> {
    override fun compareTo(other: Path): Int {
        val c = this.streets.size - other.streets.size
        if (c != 0) return c
        return this.index - other.index
    }
}