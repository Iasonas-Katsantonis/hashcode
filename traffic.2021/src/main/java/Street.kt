data class Street(
    val index: Int,
    val name: String,
    val startIntersection: Int,
    val endIntersection: Int,
    val duration: Int
) : Comparable<Street> {
    override fun compareTo(other: Street): Int {
        var c = this.duration - other.duration
        if (c != 0) return c
        c = this.startIntersection - other.startIntersection
        if (c != 0) return c
        c = this.endIntersection - other.endIntersection
        if (c != 0) return c
        return this.index - other.index
    }
}