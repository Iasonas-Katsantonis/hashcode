import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;
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

    long booksScore;
    long booksScorePerDay;
    final List<Book> booksToScan = new ArrayList<>();
    long daysIdle;
    long daysScanning;

    private static Comparator<Book> sorter;

    public Library(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + id + ", N: " + N + ", T: " + T + ", M: " + M + "}";
    }

    public void init(int daysRemaining) {
        if (sorter == null) {
            sorter = new SortBooks();
        }
        Collections.sort(books, sorter);

        int daysMax = max(daysRemaining - T, 0);
        long booksMax = daysMax * M;
        long booksTotal = min(booksMax, books.size());

        Book book;
        booksScore = 0;
        daysScanning = min(1L, booksTotal / M);
        daysIdle = max(daysRemaining - daysScanning, 0);

        for (int i = 0; i < booksTotal; i++) {
            book = books.get(i);
            booksScore += book.score;
        }
        booksScorePerDay = booksScore / T;
    }

    public void scan(long daysMax) {
        booksToScan.clear();

        long booksMax = daysMax * M;
        long booksTotal = min(booksMax, books.size());

        for (int i = 0; i < booksTotal; i++) {
            Book book = books.get(i);
            booksToScan.add(book);
        }
    }

    public void add(Book book) {
        books.add(book);
    }
}
