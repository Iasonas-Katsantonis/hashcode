import java.io.File;
import java.io.IOException;

public class MorePizza {

    private void solve(File fileIn, File fileOut) throws IOException {
        SolverRecursive solver = new SolverRecursive();
        Solution solution = solver.solve(fileIn);
        System.out.println("solution: " + solution);
        System.out.println("delta: " + solution.score());
        new SolutionWriter().write(solution, fileOut);
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
