import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution to Problem H, "Coloring Graphs", from the
 * 2015 Virginia Tech High School Programming Contest.
 *
 * This is computing the chromatic number of a graph with no twists.
 * Since N is small, we can try to color the graph with 1, 2, ..., N colors
 * until we find a minimal coloring that works. Attempting a coloring requires
 * backtracking, where we stop an attempted coloring when we realize that the
 * coloring in progress forces a vertex to have the same color as one of its
 * neighbors.
 *
 * @author Chris Wu
 */
public class H_CW {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        HashSet<Integer>[] adj = (HashSet<Integer>[])new HashSet[N];
        colors = new int[N];

        boolean complete = true;
        for (int i = 0 ; i < N; i++) {
            adj[i] = new HashSet<>();
            Scanner lineScanner = new Scanner(sc.nextLine());
            while (lineScanner.hasNextInt()) {
                adj[i].add(lineScanner.nextInt());
            }
            if (adj[i].size() < N-1) {
                complete = false;
            }
        }

        if (complete) { // since backtracking is slow for large complete graphs
            System.out.println(N);
        } else {
            // Try with 1, 2, ..., N colors until it works
            for (int numColors = 1; numColors <= N; numColors++) {
                Arrays.fill(colors, NO_COLOR);
                if (canColor(adj, numColors)) {
                    System.out.println(numColors);
                    break;
                }
            }
        }
    }

    static int[] colors;
    final static int NO_COLOR = -1;

    static boolean canColor(HashSet<Integer>[] adj, int numColors) {
        return canColorRemaining(adj, numColors, 0);
    }

    static boolean canColorRemaining(HashSet<Integer>[] adj, int numColorsAvailable,
                                     int numVerticesColored) {
        if (numVerticesColored == adj.length) return true; // base case, all vertices colored

        // Try all colors for this vertex
        for (int color = 0; color < numColorsAvailable; color++) {
            boolean ableToContinue = true;
            for (int v : adj[numVerticesColored]) {
                // Can't reuse a color from an adjacent vertex
                if (v < numVerticesColored && colors[v] == color) ableToContinue = false;
            }
            if (ableToContinue) {
                colors[numVerticesColored] = color;
                // Try to color the next vertex
                if (canColorRemaining(adj, numColorsAvailable, numVerticesColored+1)) return true;
            }
        }

        // Cannot color this vertex with the available colors
        return false;
    }
}
