import java.util.Comparator;

public class SortLibraries3 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        int c;
        // Smaller signup first.
        c = l1.T - l2.T;
        if (c != 0) return c;
        // Bigger storage first.
        c = l2.N - l1.N;
        if (c != 0) return c;
        // Bigger capacity first.
        c = l2.M - l1.M;
        if (c != 0) return c;
        c = l1.id - l2.id;
        return c;
    }
}
