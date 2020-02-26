import java.io.File;

public class HashCode2020 implements Runnable {

    private final String name;
    private final File fileIn;
    private final File fileOut;

    public HashCode2020(String name, File fileIn, File fileOut) {
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

    private void solve(File fileIn, File fileOut) throws Exception {
        Solution solution = null;
        long score = 0;

        Solution solution1 = new Solver1().solve(fileIn);
        long score1 = solution1.score();
        System.out.println(name + ": solution 1: " + score1);
        if (score1 > score) {
            score = score1;
            solution = solution1;
        }

        Solution solution2 = new Solver2().solve(fileIn);
        long score2 = solution2.score();
        System.out.println(name + ": solution 2: " + score2);
        if (score2 > score) {
            score = score2;
            solution = solution2;
        }

        System.out.println(name + ": solution: " + score);

        if (solution == null) throw new NullPointerException("solution expected!");
        SolutionWriter writer = new SolutionWriter();
        writer.write(solution, fileOut);
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
            HashCode2020 taxis = new HashCode2020(filename, fileIn, fileOut);
            new Thread(taxis).start();
        }
    }
}
