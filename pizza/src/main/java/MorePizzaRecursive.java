import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MorePizzaRecursive {

    private static class Pizza {
        public final int index;
        public final int slices;

        private Pizza(int index, int slices) {
            this.index = index;
            this.slices = slices;
        }

        @Override
        public String toString() {
            return String.valueOf(slices);
        }
    }

    private int deltaMin;
    private List<Pizza> candidateSolution = new ArrayList<>();
    private final Pizza emptyPizza = new Pizza(-1, 0);

    private void solve(File fileIn, File fileOut) throws IOException {
        deltaMin = Integer.MAX_VALUE;
        candidateSolution.clear();

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
        return solve(maximumSlices, pizzas, pizzas.size() - 1, candidates, 0);
    }

    private List<Pizza> solve(int maximumSlices, List<Pizza> pizzas, int pizzaIndex, List<Pizza> candidates, int sumCandidates) {
        if (pizzaIndex >= 0) {
            final int size = pizzas.size();
//            tab(size - pizzaIndex);
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
//            tab(size - pizzaIndex);
//            System.out.println("pick: " + pick);
                List<Pizza> picked = solve(maximumSlices, pizzas, pizzaIndex - 1, pick, sumPick);
                if (picked != null) {
                    return picked;
                }
            }

            List<Pizza> skip = candidates;
//        tab(size - pizzaIndex);
//        System.out.println("skip: " + skip);
            List<Pizza> skipped = solve(maximumSlices, pizzas, pizzaIndex - 1, skip, sumCandidates);
            if (skipped != null) {
                return skipped;
            }
        }

        return null;
    }

    private void tab(int count) {
        for (int i = 0; i <= count; i++) {
            System.out.print(" ");
        }
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
        new MorePizzaRecursive().solve(fileIn, fileOut);
    }
}
