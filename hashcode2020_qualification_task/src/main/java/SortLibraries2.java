import java.util.Comparator;

import static java.lang.Math.max;

public class SortLibraries2 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        // Bigger scores first.
        int c = l2.booksScorePerDay - l1.booksScorePerDay;
        if (c != 0) return c;
        // Smaller signup first.
        c = l1.T - l2.T;
        if (c != 0) return c;
        c = l1.id - l2.id;
        return c;
    }
}
