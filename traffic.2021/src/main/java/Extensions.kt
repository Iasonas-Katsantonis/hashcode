fun <C> List<Comparable<C>>.compareTo(that: List<C>): Int {
    var c = this.size.compareTo(that.size)
    if (c != 0) return c

    for (i in 0 until size) {
        c = this[i].compareTo(that[i])
        if (c != 0) return c
    }
    return c
}
