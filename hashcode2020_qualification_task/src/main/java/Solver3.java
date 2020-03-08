import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
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

        Map<Book, List<Library>> librariesPerBook = getLibrariesPerBook(problem);
        List<Library> libraries;
        Library library;
        int daysRemaining = problem.D;
        Map<Library, Integer> daysPerLibrary = new HashMap<>();
        int days;
        Collection<Book> booksUnused = new TreeSet<>(librariesPerBook.keySet());
        boolean scanned;

        for (Book book : librariesPerBook.keySet()) {
            libraries = librariesPerBook.get(book);
            scanned = false;

            library = findLibraryScanning(libraries, librariesSolution);
            while (!scanned && (library != null)) {
                libraries.remove(library);
                days = daysPerLibrary.get(library);
                if (library.scan(book, days)) {
                    scanned = true;
                    booksUnused.remove(book);
                } else {
                    library = findLibraryScanning(libraries, librariesSolution);
                }
            }
            while (!scanned && (libraries.size() > 0)) {
                library = libraries.remove(0);
                days = daysRemaining;
                if (library.scan(book, days)) {
                    scanned = true;
                    booksUnused.remove(book);
                    librariesSolution.add(library);
                    daysPerLibrary.put(library, days);
                    daysRemaining -= library.T;
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

    private SortedMap<Book, List<Library>> getLibrariesPerBook(Problem problem) {
        List<Library> librariesProblem = new ArrayList<>(problem.libraries);
        SortedMap<Book, List<Library>> librariesPerBook = new TreeMap<>();
        List<Library> libraries;
        Comparator<Library> librarySorter = new SortLibraries3();

        Collections.sort(librariesProblem, librarySorter);

        for (Library library : librariesProblem) {
            library.init(problem.D);
            for (Book book : library.books) {
                libraries = librariesPerBook.get(book);
                if (libraries == null) {
                    libraries = new ArrayList<>();
                    librariesPerBook.put(book, libraries);
                }
                libraries.add(library);
            }
        }

        return librariesPerBook;
    }
}
