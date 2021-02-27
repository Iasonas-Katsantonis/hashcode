import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

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

    final Collection<Book> books = new TreeSet<>();

    final List<Library> libraries = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }
}
