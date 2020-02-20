import java.util.Comparator;

public class Sort1 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        int c = l1.comparator - l2.comparator;
        if (c != 0) return c;
        c = l1.T - l2.T;
        return c;
    }
}
