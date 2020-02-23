import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProblemParser {
    public Problem parse(File fileIn) throws IOException {
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
                library.N = Integer.parseInt(l1Tokens[0]);
                library.T = Integer.parseInt(l1Tokens[1]);
                library.M = Integer.parseInt(l1Tokens[2]);

                for (int n = 0; n < library.N; n++) {
                    int bid = Integer.parseInt(l2Tokens[n]);
                    Book book = problem.books.get(bid);
                    library.add(book);
                }

                problem.libraries.add(library);
            }
        }

        return problem;
    }
}
