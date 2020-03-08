import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class BooksSameScore {
    final long score;
    final List<Book> books = new ArrayList<>();
    final List<Library> libraries = new ArrayList<>();

    int minimumSignup = Integer.MAX_VALUE;
    private final Map<Integer, Library> librariesById = new HashMap<>();

    public BooksSameScore(long score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        return (int) score;
    }

    @Override
    public String toString() {
        return "{" + score + ", T: " + minimumSignup + "}";
    }

    public void add(Book book, Library library) {
        books.add(book);

        minimumSignup = min(minimumSignup, library.T);

        Library library2 = librariesById.get(library.id);
        if (library2 == null) {
            library2 = new Library(library.id, library.N, library.T, library.M);
            librariesById.put(library.id, library2);
            libraries.add(library2);
        }
        library2.add(book);
    }
}
