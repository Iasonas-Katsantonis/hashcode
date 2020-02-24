import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MorePizzaRecursive {

    private int deltaMin;
    private List<Pizza> candidateSolution = new ArrayList<>();

    private void solve(File fileIn, File fileOut) throws IOException {
        deltaMin = Integer.MAX_VALUE;
        candidateSolution.clear();

        Problem problem = new Problem();
        List<Pizza> s = problem.pizzas;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String row1 = reader.readLine();
            String[] row1Tokens = row1.split(" ");
            problem.M = Integer.parseInt(row1Tokens[0]);
            int pizzaTypes = Integer.parseInt(row1Tokens[1]);
            String row2 = reader.readLine();
            String[] row2Tokens = row2.split(" ");
            for (int i = 0; i < pizzaTypes; i++) {
                s.add(new Pizza(i, Integer.parseInt(row2Tokens[i])));
            }
        }

        Solution solution = solve(problem);
        if (solution == null) {
            solution = new Solution();
            solution.pizzas.addAll(candidateSolution);
        }
        System.out.println("solution: " + solution);
        System.out.println("delta: " + (problem.M - sum(solution)));
        new SolutionWriter().write(solution, fileOut);
    }

    private Solution solve(Problem problem) {
        List<Pizza> pizzas = problem.pizzas;
        if (pizzas.isEmpty()) {
            return null;
        }

        Solution solution = null;
        List<Pizza> candidates = new ArrayList<>();
        List<Pizza> solved = solve(problem.M, pizzas, pizzas.size() - 1, candidates, 0);
        if (solved != null) {
            solution = new Solution();
            solution.pizzas.addAll(solved);
        }
        return solution;
    }

    private List<Pizza> solve(int maximumSlices, List<Pizza> pizzas, int pizzaIndex, List<Pizza> candidates, int sumCandidates) {
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

    private int sum(Solution solution) {
        List<Pizza> pizzas = solution.pizzas;
        int sum = 0;
        for (Pizza pizza : pizzas) {
            sum += pizza.slices;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        String filename = args[0];
        File fileIn = new File(filename);
        int indexExt = filename.lastIndexOf('.');
        if (indexExt >= 0) {
            filename = filename.substring(0, indexExt);
        }
        File fileOut = new File(filename + ".out");
        new MorePizzaRecursive().solve(fileIn, fileOut);
    }
}
