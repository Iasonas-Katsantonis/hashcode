import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class Solution {
    final Problem problem;
    final List<Library> libraries = new ArrayList<>();

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public int score() {
        int daysRemaining = problem.D;
        int booksToScan;
        int score = 0;
        Book book;
        Set<Book> books = new HashSet<>();

        for (Library library : libraries) {
            daysRemaining -= library.T;
            if (daysRemaining <= 0) break;
            booksToScan = library.M * daysRemaining;
            booksToScan = min(booksToScan, library.booksToScan.size());
            for (int b = 0; b < booksToScan; b++) {
                book = library.booksToScan.get(b);
                if (!books.contains(book)) {
                    books.add(book);
                    score += book.score;
                }
            }
        }

        return score;
    }
}
