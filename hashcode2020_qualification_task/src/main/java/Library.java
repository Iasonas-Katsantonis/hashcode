import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Library {
    final int id;
    final Collection<Book> books = new TreeSet<>();

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
    long booksScorePerSignup;
    long booksScorePerDay;
    final List<Book> booksToScan = new ArrayList<>();
    int daysScanning;
    int daysIdle;

    private static Comparator<Book> sorter;

    public Library(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + id + ", N: " + N + ", T: " + T + ", M: " + M + "}";
    }

    public void add(Book book) {
        books.add(book);
    }

    public void init(int daysRemaining) {
        int daysMax = max(daysRemaining - T, 0);
        long booksMax = ((long) daysMax) * M;
        int booksTotal = (int) min(booksMax, books.size());

        booksScore = 0;
        daysScanning = max(1, booksTotal / M);
        daysIdle = max(daysMax - daysScanning, 0);

        int i = 0;
        for (Book book : books) {
            booksScore += book.score;
            if ((++i) >= booksTotal) break;
        }
        booksScorePerSignup = booksScore / T;
        booksScorePerDay = booksScore / daysScanning;
        booksToScan.clear();
    }

    public void scan(int daysRemaining) {
        booksToScan.clear();

        int daysMax = max(daysRemaining - T, 0);
        long booksMax = ((long) daysMax) * M;
        int booksTotal = (int) min(booksMax, books.size());

        int i = 0;
        for (Book book : books) {
            booksToScan.add(book);
            if ((++i) >= booksTotal) break;
        }
    }

    public boolean scan(Book book, int daysRemaining) {
        if ((daysRemaining > T) && books.contains(book)) {
            int daysMax = max(daysRemaining - T, 0);
            long booksMax = ((long) daysMax) * M;
            int booksTotal = (int) min(booksMax, books.size());

            if (booksToScan.size() < booksTotal) {
                booksToScan.add(book);
                return true;
            }
        }
        return false;
    }
}
