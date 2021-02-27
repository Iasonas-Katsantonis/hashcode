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
    final int N;
    /**
     * the number of days it takes to finish the library signup process
     */
    final int T;
    /**
     * the number of books that can be shipped
     */
    final int M;

    long booksScore;
    long booksScorePerSignup;
    long booksScorePerDay;
    final List<Book> booksToScan = new ArrayList<>();
    int daysScanning;
    int daysIdle;

    private static Comparator<Book> sorter;

    public Library(int id, int n, int t, int m) {
        this.id = id;
        this.N = n;
        this.T = t;
        this.M = m;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "{" + id + ", N: " + N + ", T: " + T + ", M: " + M + "}";
    }

    public void add(Book book) {
        books.add(book);
        Collections.sort(books);
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

    public List<Book> scan(int daysRemaining) {
        booksToScan.clear();

        int daysMax = max(daysRemaining - T, 0);
        long booksMax = ((long) daysMax) * M;
        int booksTotal = (int) min(booksMax, books.size());

        int i = 0;
        for (Book book : books) {
            booksToScan.add(book);
            if ((++i) >= booksTotal) break;
        }
        return booksToScan;
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
