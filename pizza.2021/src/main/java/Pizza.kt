class Pizza(val index: Int, val toppings: Set<String>) {
    override fun toString(): String {
        return "[$toppings]"
    }
}