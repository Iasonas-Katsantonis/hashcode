import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SolutionWriter {

    private static final char EOL = '\n';

    public void write(Solution solution, File fileOut) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileOut))) {
            List<Library> libraries = solution.libraries;
            final int a = libraries.size();
            Library library;
            List<Book> books;
            int k;

            writer.print(a);
            writer.print(EOL);

            for (int i = 0; i < a; i++) {
                library = libraries.get(i);
                books = library.booksToScan;
                k = books.size();
                if (k == 0) throw new IndexOutOfBoundsException();
                writer.print(library.id);
                writer.print(' ');
                writer.print(k);
                writer.print(EOL);

                for (int j = 0; j < k; j++) {
                    Book book = books.get(j);
                    if (j > 0) writer.print(' ');
                    writer.print(book.id);
                }
                writer.print(EOL);
            }
            writer.flush();
        }
    }
}
