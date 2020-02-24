import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverStack {

    private static class SolveStack {
        final int pizzaIndex;
        final List<Pizza> candidates;
        final int sumCandidates;

        private SolveStack(int pizzaIndex, List<Pizza> candidates, int sumCandidates) {
            this.pizzaIndex = pizzaIndex;
            this.candidates = candidates;
            this.sumCandidates = sumCandidates;
        }
    }

    private int deltaMin;
    private List<Pizza> candidateSolution = new ArrayList<>();
    private List<SolveStack> stack = new ArrayList<>();

    public Solution solve(File fileIn) throws IOException {
        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(fileIn);
        return solve(problem);
    }

    public Solution solve(Problem problem) {
        deltaMin = Integer.MAX_VALUE;
        candidateSolution.clear();
        stack.clear();

        Solution solution = new Solution(problem);
        List<Pizza> pizzas = problem.pizzas;
        if (pizzas.isEmpty()) {
            return solution;
        }

        List<Pizza> candidates = new ArrayList<>();
        SolveStack stackElement = new SolveStack(pizzas.size() - 1, candidates, 0);
        stack.add(stackElement);
        List<Pizza> solved = solve(problem.M, pizzas);
        if (solved != null) {
            solution.pizzas.addAll(solved);
        } else {
            solution.pizzas.addAll(candidateSolution);
        }
        return solution;
    }

    private List<Pizza> solve(final int maximumSlices, final List<Pizza> pizzas) {
        SolveStack stackElement;
        int pizzaIndex;
        List<Pizza> candidates;
        int sumCandidates;
        int gcCounter = 0;

        while (stack.size() > 0) {
            stackElement = stack.remove(0);
            pizzaIndex = stackElement.pizzaIndex;
            candidates = stackElement.candidates;
            sumCandidates = stackElement.sumCandidates;

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

                List<Pizza> skip = candidates;
                stackElement = new SolveStack(pizzaIndex - 1, skip, sumCandidates);
                stack.add(0, stackElement);

                if (delta > 0) {
                    if (delta < deltaMin) {
                        deltaMin = delta;
                        candidateSolution = pick;
                    }
                    stackElement = new SolveStack(pizzaIndex - 1, pick, sumPick);
                    stack.add(0, stackElement);
                }
            }

            gcCounter++;
            if (gcCounter >= 100) {
                gcCounter = 0;
                System.gc();
            }
        }

        return null;
    }
}
