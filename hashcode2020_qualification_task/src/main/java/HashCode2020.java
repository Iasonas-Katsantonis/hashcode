import java.io.File;

public class HashCode2020 {

    private void solve(File fileIn, File fileOut) throws Exception {
        Solution solution = null;
        int score = 0;

        Solution solution1 = new Solver1().solve(fileIn);
        int score1 = solution1.score();
        System.out.println("solution 1: " + score1);
        if (score1 > score) {
            score = score1;
            solution = solution1;
        }

        Solution solution2 = new Solver2().solve(fileIn);
        int score2 = solution2.score();
        System.out.println("solution 2: " + score2);
        if (score2 > score) {
            score = score2;
            solution = solution2;
        }

        System.out.println("solution: " + score);

        if (solution == null) throw new NullPointerException("solution expected!");
        SolutionWriter writer = new SolutionWriter();
        writer.write(solution, fileOut);
    }

    public static void main(String[] args) throws Exception {
        HashCode2020 taxis = new HashCode2020();
        for (String arg : args) {
            String filename = arg;
            System.out.println(filename);
            File fileIn = new File(filename);
            int indexExt = filename.lastIndexOf('.');
            if (indexExt >= 0) {
                filename = filename.substring(0, indexExt);
            }
            File fileOut = new File(filename + ".out");
            taxis.solve(fileIn, fileOut);
        }
    }
}
