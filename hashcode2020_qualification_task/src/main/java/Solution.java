import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    final List<Library> libraries = new ArrayList<>();

    public int score() {
        int score = 0;
        Set<Book> books = new HashSet<>();
        for (Library library : libraries) {
            books.addAll(library.booksToScan);
        }
        for (Book book : books) {
            score += book.score;
        }
        return score;
    }
}
