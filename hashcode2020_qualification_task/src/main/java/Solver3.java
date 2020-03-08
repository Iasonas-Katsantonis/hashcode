import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
        boolean libraryScanning;
        int daysRemaining = problem.D;
        Map<Library, Integer> daysPerLibrary = new HashMap<>();
        int days;

        for (Book book : librariesPerBook.keySet()) {
            libraries = librariesPerBook.get(book);
            while (libraries.size() > 0) {
                library = libraries.remove(0);
                libraryScanning = daysPerLibrary.containsKey(library);
                days = libraryScanning ? daysPerLibrary.get(library) : daysRemaining;
                if (library.scan(book, days)) {
                    if (!libraryScanning) {
                        librariesSolution.add(library);
                        daysPerLibrary.put(library, days);
                        daysRemaining -= library.T;
                    }
                    break;
                }
            }
        }

        return solution;
    }

    private SortedMap<Book, List<Library>> getLibrariesPerBook(Problem problem) {
        SortedMap<Book, List<Library>> librariesPerBook = new TreeMap<>();
        List<Library> libraries;
        Comparator<Library> librarySorter = new SortLibraries3();

        for (Library library : problem.libraries) {
            library.init(problem.D);
            for (Book book : library.books) {
                libraries = librariesPerBook.get(book);
                if (libraries == null) {
                    libraries = new ArrayList<>();
                    librariesPerBook.put(book, libraries);
                }
                libraries.add(library);
                Collections.sort(libraries, librarySorter);
            }
        }

        return librariesPerBook;
    }
}
