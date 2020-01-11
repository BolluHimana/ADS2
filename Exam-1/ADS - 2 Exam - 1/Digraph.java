public class Digraph
{
 final int V;
 final Bag<Integer>[] adj;

public Digraph(int V)
{
this.V = V;
adj = (Bag<Integer>[]) new Bag[V];
for (int v = 0; v < V; v++)
adj[v] = new Bag<Integer>();
}

public void addEdge(int v, int w)
{
adj[v].add(w);

}
public int size(){
    return adj.length;
}
public Iterable<Integer> adj(int v)
{ return adj[v]; }
}