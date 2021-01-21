class Pizza(val index: Int, val toppings: List<Int>) {
    override fun toString(): String {
        return "$index:$toppings"
    }

    override fun hashCode(): Int {
        return index
    }
}