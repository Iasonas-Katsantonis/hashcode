public class Book {
    final int id;
    final int score;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + score + "}";
    }
}
