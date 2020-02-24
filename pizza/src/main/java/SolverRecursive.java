import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverRecursive {

    private int deltaMin;
    private List<Pizza> candidateSolution = new ArrayList<>();

    public Solution solve(File fileIn) throws IOException {
        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(fileIn);
        return solve(problem);
    }

    public Solution solve(Problem problem) {
        deltaMin = Integer.MAX_VALUE;
        candidateSolution.clear();

        Solution solution = new Solution(problem);
        List<Pizza> pizzas = problem.pizzas;
        if (pizzas.isEmpty()) {
            return solution;
        }

        List<Pizza> candidates = new ArrayList<>();
        List<Pizza> solved = solve(problem.M, pizzas, pizzas.size() - 1, candidates, 0);
        if (solved != null) {
            solution.pizzas.addAll(solved);
        } else {
            solution.pizzas.addAll(candidateSolution);
        }
        return solution;
    }

    private List<Pizza> solve(final int maximumSlices, final List<Pizza> pizzas, int pizzaIndex, List<Pizza> candidates, int sumCandidates) {
        if (pizzaIndex >= 0) {
            if (candidates.isEmpty()) {
                System.out.println("solve: [] " + sumCandidates);
            } else {
                System.out.println("solve: [" + candidates.get(0) + ", ...] " + sumCandidates);
            }

            Pizza candidate = pizzas.get(pizzaIndex);

            List<Pizza> pick = new ArrayList<>(candidates);
            pick.add(0, candidate);
            int sumPick = sumCandidates + candidate.slices;
            int delta = maximumSlices - sumPick;
            if (delta == 0) {
                return pick;
            }
            if (delta > 0) {
                if (delta < deltaMin) {
                    deltaMin = delta;
                    candidateSolution = pick;
                }
                List<Pizza> picked = solve(maximumSlices, pizzas, pizzaIndex - 1, pick, sumPick);
                if (picked != null) {
                    return picked;
                }
            }

            List<Pizza> skip = candidates;
            List<Pizza> skipped = solve(maximumSlices, pizzas, pizzaIndex - 1, skip, sumCandidates);
            if (skipped != null) {
                return skipped;
            }
        }

        return null;
    }
}
