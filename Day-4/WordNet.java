import java.util.ArrayList;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
public class WordNet{
    private HashMap<Integer,String> synsets1;
    private HashMap<String,ArrayList<Integer>> hypernyms1;
    private Digraph dg;
    private SAP sap;
    public WordNet(String synsets, String hypernyms) {
        synsets1 = new HashMap<Integer,String>();
        hypernyms1 = new HashMap<String,ArrayList<Integer>>();
        this.synsets(synsets);
        dg = new Digraph(synsets1.size());
        this.hypernyms(hypernyms);
         sap = new SAP(dg);
    }
    private void hypernyms(String f) {
        // File f1=new File("E:\\ADS-2\\ADS2_2019501072\\DAY-1\\hypernyms.txt");        
        // FileReader fr = new FileReader(f1);
        // BufferedReader br=new BufferedReader(fr);
        // //StringBuffer sb=new StringBuffer();
        In in = new In(f);
        while(in.hasNextLine()){
            String[] h = in.readLine().split(",");
            for(int k = 1; k<h.length;k++){
                dg.addEdge(Integer.parseInt(h[0]),Integer.parseInt(h[k]));
            }
        }

        }
    private void synsets(String f2){
        In in = new In(f2);
        while(in.hasNextLine()){
            String[] s = in.readLine().split(",");
            String[] l = s[1].split("\\s++");
            for(String s1: l){
                synsets1.put(Integer.parseInt(s[0]),s[1]);
                if(!hypernyms1.containsKey(s1)){
                ArrayList<Integer>array = new ArrayList<Integer>();
                array.add(Integer.parseInt(s[0]));
                hypernyms1.put(s1,array);
            }
            else {
                ArrayList<Integer> val = hypernyms1.get(s1);
                val.add(Integer.parseInt(s[0]));
                hypernyms1.put(s1,val);
            }
        }
    }
}
public Iterable<String> nouns(){
    return hypernyms1.keySet();
}
public boolean isNoun(String w) {
    // if (w == null)
    //   throw new IllegalArgumentException("");
    return hypernyms1.containsKey(w);
  }
  public int distance(String nounA, String nounB) {
    // if (nounA==null||nounB==null)
    //   throw new IllegalArgumentException("");
    return sap.length(hypernyms1.get(nounA),hypernyms1.get(nounB));
  }

  public String sap(String nounA, String nounB) {
    // if (nounA==null || nounB==null)
    //   throw new IllegalArgumentException("");
    return synsets1.get(sap.ancestor(hypernyms1.get(nounA), hypernyms1.get(nounB)));
  }
    public static void main(String[] args) {
    //     WordNet od = new WordNet("synsets","hypernyms");
    //     //od.Synsets(f);
    //     // od.hypernyms("hypernyms");
    //     //od.hypernyms();
    //     // od.synsets();
    }
}