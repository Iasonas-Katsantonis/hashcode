class Team(
    /**
     * the number of people in the team.
     */
    val numberOfMembers: Int
) {
    val pizzas: MutableSet<Pizza> = HashSet()

    override fun toString(): String {
        return "$numberOfMembers:$pizzas"
    }
}