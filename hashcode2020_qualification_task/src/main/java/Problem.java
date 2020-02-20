import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    /**
     * the number of different books
     */
    int B;
    /**
     * the number of libraries
     */
    int L;
    /**
     * the number of days
     */
    int D;

    final Map<Integer, Book> books = new HashMap<>();

    final List<Library> libraries = new ArrayList<>();

    public void add(Book book) {
        books.put(book.id, book);
    }
}
