import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    final List<Library> libraries = new ArrayList<>();

    public int score() {
        int score = 0;
        Set<Book> bookIds = new HashSet<>();
        for (Library library : libraries) {
            for (Book book : library.booksToScan) {
                if (!bookIds.contains(book)) {
                    score += book.score;
                    bookIds.add(book);
                }
            }
        }
        return score;
    }
}
