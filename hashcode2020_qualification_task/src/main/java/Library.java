import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class Library {
    final int id;
    final List<Book> books = new ArrayList<>();

    /**
     * the number of books
     */
    int N;
    /**
     * the number of days it takes to finish the library signup process
     */
    int T;
    /**
     * the number of books that can be shipped
     */
    int M;

    int comparator;
    final List<Book> booksToScan = new ArrayList<>();

    public Library(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + N + ", " + T + ", " + M + "}";
    }

    public void init(int D) {
        Collections.sort(books, new SortBooks());

        int daysMax = D - T;
        int booksMax = daysMax * M;
        int booksTotal = min(daysMax * booksMax, books.size());

        for (int i = 0; i < booksTotal; i++) {
            Book book = books.get(i);
            comparator += book.score;
        }
    }
}
