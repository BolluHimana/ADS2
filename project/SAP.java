import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
public class SAP {
    private final Digraph digraph;
    public SAP(Digraph G) {
        digraph = new Digraph(G);
    }
    private void validateVertex(int v) {
        int V = digraph.V();
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new NullPointerException("argument is null");
        }
        int V = digraph.V();
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    private int[] shortest(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        int[] result = new int[2];
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        int shortestLen = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < digraph.V(); ++i) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < shortestLen) {
                    shortestLen = len;
                    shortestAncestor = i;
                }
            }
        }
        if (shortestAncestor == -1) {
            result[0] = -1;
            result[1] = -1;
        }
        else {
            result[0] = shortestLen;
            result[1] = shortestAncestor;
        }
        return result;
    }
    private int[] shortest(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        int[] result = new int[2];
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        int shortestLen = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < digraph.V(); ++i) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < shortestLen) {
                    shortestLen = len;
                    shortestAncestor = i;
                }
            }
        }
        if (shortestAncestor == -1) {
            result[0] = -1;
            result[1] = -1;
        }
        else {
            result[0] = shortestLen;
            result[1] = shortestAncestor;
        }
        return result;
    }
    public int length(int v, int w) {
        int[] result = shortest(v, w);
        return result[0];
    }
    public int ancestor(int v, int w) {
        int[] result = shortest(v, w);
        return result[1];
    }
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int[] result = shortest(v, w);
        return result[0];
    }
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int[] result = shortest(v, w);
        return result[1];
    }
}