public class Book {
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
}
