public class Book implements Comparable<Book> {
    final int id;
    final long score;

    public Book(int id, long score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + score + "}";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Book that) {
        // Bigger scores first.
        int c = Long.compare(that.score, this.score);
        if (c != 0) return c;
        return this.id - that.id;
    }
}
