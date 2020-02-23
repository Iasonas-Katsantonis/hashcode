import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

        int m;
        List<Pizza> s = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String row1 = reader.readLine();
            String[] row1Tokens = row1.split(" ");
            m = Integer.parseInt(row1Tokens[0]);
            int pizzaTypes = Integer.parseInt(row1Tokens[1]);
            String row2 = reader.readLine();
            String[] row2Tokens = row2.split(" ");
            for (int i = 0; i < pizzaTypes; i++) {
                s.add(new Pizza(i, Integer.parseInt(row2Tokens[i])));
            }
        }

        List<Pizza> solution = solve(m, s);
        if (solution == null) {
            solution = candidateSolution;
        }
        System.out.println("solution: " + solution);
        System.out.println("delta: " + (m - sum(solution)));
        write(solution, fileOut);
    }

    private List<Pizza> solve(int maximumSlices, List<Pizza> pizzas) {
        if (pizzas.isEmpty()) {
            return pizzas;
        }

        List<Pizza> candidates = new ArrayList<>();
        SolveStack stackElement = new SolveStack(pizzas.size() - 1, candidates, 0);
        stack.add(stackElement);
        return solveStack(maximumSlices, pizzas);
    }

    private List<Pizza> solveStack(final int maximumSlices, final List<Pizza> pizzas) {
        final int size = pizzas.size();
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
                System.out.println("mem: " + Runtime.getRuntime().freeMemory());
            }
        }

        return null;
    }

    private int sum(List<Pizza> pizzas) {
        int sum = 0;
        for (Pizza pizza : pizzas) {
            sum += pizza.slices;
        }
        return sum;
    }

    private void write(List<Pizza> solution, File fileOut) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileOut))) {
            int k = solution.size();
            writer.println(k);
            for (int i = 0; i < k; i++) {
                if (i > 0) {
                    writer.print(' ');
                }
                writer.print(solution.get(i).index);
            }
            writer.println();
        }
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
