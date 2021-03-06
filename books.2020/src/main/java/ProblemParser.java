import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProblemParser {
    public Problem parse(File fileIn) throws IOException {
        Problem problem = new Problem();
        Map<Integer, Book> books = new HashMap<>();
        Book book;

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
                book = new Book(b, Long.parseLong(bookScores[b]));
                problem.add(book);
                books.put(book.id, book);
            }

            for (int l = 0; l < problem.L; l++) {
                String l1 = reader.readLine();
                String l2 = reader.readLine();

                String[] l1Tokens = l1.split(" ");
                String[] l2Tokens = l2.split(" ");

                int N = Integer.parseInt(l1Tokens[0]);
                int T = Integer.parseInt(l1Tokens[1]);
                int M = Integer.parseInt(l1Tokens[2]);
                Library library = new Library(l, N, T, M);

                for (int n = 0; n < N; n++) {
                    int bid = Integer.parseInt(l2Tokens[n]);
                    book = books.get(bid);
                    library.add(book);
                }

                problem.libraries.add(library);
            }
        }

        return problem;
    }
}
