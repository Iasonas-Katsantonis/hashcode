import java.util.ArrayList;
import java.util.List;

public class Solution {
    final Problem problem;
    final List<Pizza> pizzas = new ArrayList<>();

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public int score() {
        List<Pizza> pizzas = this.pizzas;
        int score = 0;
        for (Pizza pizza : pizzas) {
            score += pizza.slices;
        }
        return problem.M - score;
    }
}
