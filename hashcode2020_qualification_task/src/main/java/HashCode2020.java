import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HashCode2020 {

    private Problem parse(File fileIn) throws IOException {
        Problem problem = new Problem();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn))) {
            String row1 = reader.readLine();
            String[] tokens1 = row1.split(" ");
            problem.B = Integer.parseInt(tokens1[0]);
            problem.L = Integer.parseInt(tokens1[1]);
            problem.D = Integer.parseInt(tokens1[2]);
            problem.books.clear();
            problem.libraries.clear();

            String booksRow = reader.readLine();
            String[] bookScores = booksRow.split(" ");
            for (int b = 0; b < problem.B; b++) {
                problem.add(new Book(b, Integer.parseInt(bookScores[b])));
            }

            for (int l = 0; l < problem.L; l++) {
                String l1 = reader.readLine();
                String l2 = reader.readLine();

                String[] l1Tokens = l1.split(" ");
                String[] l2Tokens = l2.split(" ");

                Library library = new Library(l);
                problem.libraries.add(library);
                library.N = Integer.parseInt(l1Tokens[0]);
                library.T = Integer.parseInt(l1Tokens[1]);
                library.M = Integer.parseInt(l1Tokens[2]);

                for (int n = 0; n < library.N; n++) {
                    int bid = Integer.parseInt(l2Tokens[n]);
                    Book book = problem.books.get(bid);
                    library.books.add(book);
                }
                library.init(problem.D);
            }
        }

        return problem;
    }

    private void write(Solution solution, File fileOut) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileOut))) {
            List<Library> libraries = solution.libraries;
            final int a = libraries.size();
            writer.println(a);
            for (int i = 0; i < a; i++) {
                Library library = libraries.get(i);
                List<Book> books = library.booksToScan;
                int k = books.size();
                if (k > 0) {
                    writer.print(library.id);
                    writer.print(' ');
                    writer.println(k);

                    for (int j = 0; j < k; j++) {
                        Book book = books.get(j);
                        if (j > 0) writer.print(' ');
                        writer.print(book.id);
                    }
                    writer.println();
                }
            }
        }
    }

    private void solve(File fileIn, File fileOut) throws IOException {
        Problem problem = parse(fileIn);
        Solution solution = solve(problem);
        System.out.println("solution: " + solution.score());
        write(solution, fileOut);
    }

    private Solution solve(Problem problem) {
        Collections.sort(problem.libraries, new Sort1());
        Solution solution = new Solution();
        assign(problem, solution);
        return solution;
    }

    private void assign(Problem problem, Solution solution) {
        List<Library> librariesProblem = problem.libraries;
        List<Library> librariesSolution = solution.libraries;
        librariesSolution.clear();
        int daysRemaining = problem.D;

        for (Library library : librariesProblem) {
            daysRemaining -= library.T;
            if (daysRemaining <= 0) break;
            librariesSolution.add(library);
            library.scan(daysRemaining);
        }
    }

    public static void main(String[] args) throws Exception {
        HashCode2020 taxis = new HashCode2020();
        for (String arg : args) {
            String filename = arg;
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
