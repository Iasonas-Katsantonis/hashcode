import java.util.Comparator;

public class SortLibraries1 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        // Bigger scores first.
        int c = l2.booksScore - l1.booksScore;
        if (c != 0) return c;
        // Smaller signup first.
        c = l1.T - l2.T;
        return c;
    }
}
