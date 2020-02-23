import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashCode2020 {

    private void solve(File fileIn, File fileOut) throws IOException {
        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(fileIn);
        Solution solution = null;
        int score = 0;

        Solution solution1 = solve1(problem);
        int score1 = solution1.score();
        System.out.println("solution 1: " + score1);
        if (score1 > score) {
            score = score1;
            solution = solution1;
        }

        Solution solution2 = solve2(problem);
        int score2 = solution2.score();
        System.out.println("solution 2: " + score2);
        if (score2 > score) {
            score = score2;
            solution = solution2;
        }

        System.out.println("solution: " + score);

        SolutionWriter writer = new SolutionWriter();
        writer.write(solution, fileOut);
    }

    private Solution solve2(Problem problem) {
        Solution solution = new Solution();
        assign2(problem, solution);
        return solution;
    }

    private void assign2(Problem problem, Solution solution) {
        List<Library> librariesProblem = new ArrayList<>(problem.libraries);
        List<Library> librariesSolution = solution.libraries;
        librariesSolution.clear();
        int daysRemaining = problem.D;
        Set<Book> booksScanned = new HashSet<>();
        Library library;
        Comparator<Library> sorter = new SortLibraries2();
        int size = librariesProblem.size();

        while (size > 0) {
            for (int i = 0; i < size; i++) {
                library = librariesProblem.get(i);
                library.books.removeAll(booksScanned);
                library.init(daysRemaining);
            }
            Collections.sort(librariesProblem, sorter);
            library = librariesProblem.remove(0);
            size--;
            daysRemaining -= library.T;
            if (daysRemaining <= 0) break;
            library.scan(daysRemaining);
            if (library.booksToScan.size() > 0) {
                booksScanned.addAll(library.booksToScan);
                librariesSolution.add(library);
            }
        }
    }

    private Solution solve1(Problem problem) {
        Solution solution = new Solution();
        assign1(problem, solution);
        return solution;
    }

    private void assign1(Problem problem, Solution solution) {
        List<Library> librariesProblem = new ArrayList<>(problem.libraries);
        List<Library> librariesSolution = solution.libraries;
        librariesSolution.clear();
        int daysRemaining = problem.D;
        Set<Book> booksScanned = new HashSet<>();
        Library library;
        Comparator<Library> sorter = new SortLibraries1();
        int size = librariesProblem.size();

        while (size > 0) {
            for (int i = 0; i < size; i++) {
                library = librariesProblem.get(i);
                library.books.removeAll(booksScanned);
                library.init(daysRemaining);
            }
            Collections.sort(librariesProblem, sorter);
            library = librariesProblem.remove(0);
            size--;
            daysRemaining -= library.T;
            if (daysRemaining <= 0) break;
            library.scan(daysRemaining);
            if (library.booksToScan.size() > 0) {
                booksScanned.addAll(library.booksToScan);
                librariesSolution.add(library);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HashCode2020 taxis = new HashCode2020();
        for (String arg : args) {
            String filename = arg;
            System.out.println(filename);
            File fileIn = new File(filename);
            int indexExt = filename.lastIndexOf('.');
            if (indexExt >= 0) {
                filename = filename.substring(0, indexExt);
            }
            File fileOut = new File(filename + ".out");
            taxis.solve(fileIn, fileOut);
        }
    }
}
