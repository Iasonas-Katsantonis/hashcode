import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SolutionWriter {

    private static final char EOL = '\n';

    public void write(Solution solution, File fileOut) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileOut))) {
            List<Pizza> pizzas = solution.pizzas;
            int k = pizzas.size();
            writer.print(k);
            writer.print(EOL);

            for (int i = 0; i < k; i++) {
                if (i > 0) {
                    writer.print(' ');
                }
                writer.print(pizzas.get(i).index);
            }
            writer.print(EOL);
            writer.flush();
        }
    }
}
