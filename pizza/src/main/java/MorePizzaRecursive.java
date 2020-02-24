import java.io.File;
import java.io.IOException;

public class MorePizzaRecursive implements Runnable {

    private final String name;
    private final File fileIn;
    private final File fileOut;

    public MorePizzaRecursive(String name, File fileIn, File fileOut) {
        this.name = name;
        this.fileIn = fileIn;
        this.fileOut = fileOut;
    }

    @Override
    public void run() {
        try {
            solve(fileIn, fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void solve(File fileIn, File fileOut) throws IOException {
        SolverRecursive solver = new SolverRecursive();
        Solution solution = solver.solve(fileIn);
        System.out.println(name + ": solution: " + solution.score());
        new SolutionWriter().write(solution, fileOut);
    }

    public static void main(String[] args) {
        for (String arg : args) {
            String filename = arg;
            File fileIn = new File(filename);
            int indexExt = filename.lastIndexOf('.');
            if (indexExt >= 0) {
                filename = filename.substring(0, indexExt);
            }
            File fileOut = new File(filename + ".out");
            MorePizzaRecursive slices = new MorePizzaRecursive(filename, fileIn, fileOut);
            new Thread(slices).start();
        }
    }
}
