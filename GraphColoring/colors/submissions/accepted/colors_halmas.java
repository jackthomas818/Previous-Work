import java.util.*;

/**
 * Graph coloring problem using canonical backtracking. 
 * Try to color the graph using a certain number of colors,
 * incrementing the allowed number of colors each step.
 * 
 * @author halmas94@vt.edu
 */
public class colors_halmas
{
   // Maintain a global reference to the adjacency list representing the graph
   static ArrayList<ArrayList<Integer>> edges;
   
   // Maintain a global reference to the array representing the coloring of the graph
   // (0 represents uncolored, an int is the color otherwise)
   static int[] colors;
   
   public static void main(String[] args)
   {
      Scanner scan = new Scanner(System.in);
      
      int N = scan.nextInt();
      scan.nextLine();
      
      colors = new int[N];
      edges = new ArrayList<ArrayList<Integer>>();
      
      // Build the adjacency lists
      for(int i = 0; i<N; i++)
      {
         String line = scan.nextLine();
         Scanner lScan = new Scanner(line);
         ArrayList<Integer> curEdges = new ArrayList<Integer>();
         while(lScan.hasNext())
         {
            curEdges.add(lScan.nextInt());
         }
         edges.add(curEdges);
      }
      
      // There must be a minimum number of 2 colors, as you have two connected nodes
      // There can be a max of N colors, (each node having its own color)
      for(int max = 2; max<=N; max++)
      {
         // We can fix the colors of two nodes along one edge, as they must be color 1 and color 2
         colors[0] = 1;
         colors[edges.get(0).get(0)] = 2;
         
         // Check if this graph can be colored with max colors, if so we are done
         if(backTrack(1, max))
         {
            System.out.println(max);
            break;
         }
      }
   }
   
   
   /**
    * Canonical backtracking. If we have colored all the edges
    * we return true. Otherwise we go through all possible colors,
    * make sure there are no existing conflicts. If there are not
    * then we color this node and attempt to color the rest of the nodes.
    * If the coloring of the rest of the graph didn't work, then we try 
    * changing the color of this node and try again.
    * 
    */
   public static boolean backTrack(int ind, int max)
   {
      if(ind == colors.length) return true;
      
      // As we are fixing some of the colors we might be able to
      // skip a node.
      if(colors[ind] != 0) return backTrack(ind+1, max);
      
      out: for(int c = 1; c<=max; c++)
      {
         for(int e : edges.get(ind)) {
            if(colors[e] == c) continue out;
         }
         colors[ind] = c;
         if(backTrack(ind+1, max)) return true;
         colors[ind] = 0;
      }
      return false;
   }
}