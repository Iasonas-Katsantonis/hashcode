import java.util.Comparator;

public class SortBooks implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        // Bigger scores first.
        return b2.score - b1.score;
    }
}
