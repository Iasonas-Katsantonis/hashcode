import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MorePizza {

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

    private void solve(File fileIn, File fileOut) throws IOException {
        deltaMin = Integer.MAX_VALUE;
        candidateSolution.clear();
        stack.clear();

        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(fileIn);

        Solution solution = solve(problem);
        if (solution == null) {
            solution = new Solution(problem);
            solution.pizzas.addAll(candidateSolution);
        }
        System.out.println("solution: " + solution);
        System.out.println("delta: " + solution.score());
        new SolutionWriter().write(solution, fileOut);
    }

    private Solution solve(Problem problem) {
        List<Pizza> pizzas = problem.pizzas;
        if (pizzas.isEmpty()) {
            return null;
        }

        Solution solution = null;
        List<Pizza> candidates = new ArrayList<>();
        SolveStack stackElement = new SolveStack(pizzas.size() - 1, candidates, 0);
        stack.add(stackElement);
        List<Pizza> solved = solveStack(problem.M, pizzas);
        if (solved != null) {
            solution = new Solution(problem);
            solution.pizzas.addAll(solved);
        }
        return solution;
    }

    private List<Pizza> solveStack(final int maximumSlices, final List<Pizza> pizzas) {
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

    public static void main(String[] args) throws Exception {
        String filename = args[0];
        File fileIn = new File(filename);
        int indexExt = filename.lastIndexOf('.');
        if (indexExt >= 0) {
            filename = filename.substring(0, indexExt);
        }
        File fileOut = new File(filename + ".out");
        new MorePizza().solve(fileIn, fileOut);
    }
}
