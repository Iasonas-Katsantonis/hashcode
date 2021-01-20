class Pizza(val index: Int, val toppings: Set<Int>) {
    override fun toString(): String {
        return toppings.toString()
    }
}