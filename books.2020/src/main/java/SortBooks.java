import java.util.Comparator;

public class SortBooks implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        // Bigger scores first.
        int c = Long.compare(b2.score, b1.score);
        if (c != 0) return c;
        c = b1.id - b2.id;
        return c;
    }
}
