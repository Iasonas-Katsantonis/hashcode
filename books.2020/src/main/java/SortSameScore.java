import java.util.Comparator;

public class SortSameScore implements Comparator<BooksSameScore> {
    @Override
    public int compare(BooksSameScore b1, BooksSameScore b2) {
        int c;
        c = Long.compare(b2.score / b2.minimumSignup, b1.score / b1.minimumSignup);
        if (c != 0) return c;
        // Smaller signup first.
        c = b1.minimumSignup - b2.minimumSignup;
        if (c != 0) return c;
        // Bigger scores first.
        c = Long.compare(b2.score, b1.score);
        return c;
    }
}
