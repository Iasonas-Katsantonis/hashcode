import java.util.Comparator;

public class Sort1 implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
        return l1.comparator - l2.comparator;
    }
}
