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

    public long score() {
        int daysRemaining = problem.D;
        long booksMax;
        int booksToScan;
        long score = 0;
        Book book;
        Set<Book> books = new HashSet<>();

        for (Library library : libraries) {
            daysRemaining -= library.T;
            if (daysRemaining <= 0) break;
            booksMax = ((long) daysRemaining) * library.M;
            booksToScan = (int) min(booksMax, library.booksToScan.size());
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
