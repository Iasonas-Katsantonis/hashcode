import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solver3 {
    public Solution solve(File fileIn) throws IOException {
        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(fileIn);
        return solve(problem);
    }

    private Solution solve(Problem problem) {
        Solution solution = new Solution(problem);
        List<Library> librariesSolution = solution.libraries;

        Map<Integer, Library> librariesById = new HashMap<>();
        for (Library library : problem.libraries) {
            librariesById.put(library.id, library);
        }

        List<BooksSameScore> librariesPerBookPerScore = new ArrayList<>(getLibrariesPerBookPerScore(problem));
        Collections.sort(librariesPerBookPerScore, new SortSameScore());

        Collection<Book> booksUnused = new TreeSet<>();
        for (BooksSameScore bss : librariesPerBookPerScore) {
            booksUnused.addAll(bss.books);
        }

        Collection<Book> books;
        List<Library> libraries;
        Library library;
        boolean libraryScanning;
        int daysRemaining = problem.D;
        Map<Library, Integer> daysPerLibrary = new HashMap<>();
        int days;

        for (BooksSameScore booksSameScore : librariesPerBookPerScore) {
            libraries = booksSameScore.libraries;

            for (Library lib : libraries) {
                library = librariesById.get(lib.id);
                books = lib.books;
                libraryScanning = daysPerLibrary.containsKey(library);
                days = libraryScanning ? daysPerLibrary.get(library) : daysRemaining;

                for (Book book : books) {
                    if (library.scan(book, days)) {
                        booksUnused.remove(book);
                        if (!libraryScanning) {
                            libraryScanning = true;
                            librariesSolution.add(library);
                            daysPerLibrary.put(library, days);
                            daysRemaining -= library.T;
                        }
                    }
                }
            }
        }

        System.err.println("unused: " + booksUnused.size());
        return solution;
    }

    private Library findLibraryScanning(List<Library> libraries, List<Library> solution) {
        if (libraries.isEmpty() || solution.isEmpty()) {
            return null;
        }
        for (Library library : libraries) {
            if (solution.contains(library)) {
                return library;
            }
        }
        return null;
    }

    private Collection<BooksSameScore> getLibrariesPerBookPerScore(Problem problem) {
        List<Library> librariesProblem = new ArrayList<>(problem.libraries);
        Map<Long, BooksSameScore> result = new TreeMap<>();
        BooksSameScore booksSameScore;
        Comparator<Library> librarySorter = new SortLibraries3();
        long score;

        Collections.sort(librariesProblem, librarySorter);

        for (Library library : librariesProblem) {
            library.init(problem.D);
            for (Book book : library.books) {
                score = book.score;
                booksSameScore = result.get(score);
                if (booksSameScore == null) {
                    booksSameScore = new BooksSameScore(score);
                    result.put(score, booksSameScore);
                }
                booksSameScore.add(book, library);
            }
        }

        return result.values();
    }
}
