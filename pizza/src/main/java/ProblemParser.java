import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ProblemParser {
    public Problem parse(File fileIn) throws IOException {
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

        return problem;
    }
}
