import java.util.Comparator;

import static java.lang.Math.min;

public class SortLibraries3 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        int c;
        // Smaller signup first.
        c = l1.T - l2.T;
        if (c != 0) return c;
        // Bigger book descending.
        c = compareBooks(l1, l2);
        if (c != 0) return c;
        c = l1.id - l2.id;
        return c;
    }

    private int compareBooks(Library l1, Library l2) {
        final int s1 = l1.books.size();
        final int s2 = l2.books.size();
        final int s = min(s1, s2);
        int c;
        for (int i = 0; i < s; i++) {
            // Bigger book first.
            c = Long.compare(l2.books.get(i).score, l1.books.get(i).score);
            if (c != 0) return c;
        }
        return 0;
    }
}
