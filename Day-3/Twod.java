import java.util.*;
import java.io.*;
class Twod{
    ArrayList<String> al;
    ArrayList<String> al1;
    ArrayList<String> al2;
    Digraph dg;
    SAP sap;
    Twod(String synsets, String hypernyms) throws Exception{
        al= new ArrayList<>();
        al1= new ArrayList<>();
        al2 = new ArrayList<>();
        this.synsets(synsets);
        dg = new Digraph(al.size());
        this.hypernyms(hypernyms);
        System.out.println(dg.size());
        int c=0;
        for(int i=0;i<dg.size();i++){
            c=c+dg.adj[i].size();
        }
        System.out.println(c);
        sap = new SAP(dg);
    }
    public void hypernyms(String f)throws Exception {
        File f1=new File("E:\\ADS-2\\ADS2_2019501072\\DAY-1\\hypernyms.txt");        
        FileReader fr = new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);
        //StringBuffer sb=new StringBuffer();
        String line;
        int c= 0;
        while((line=br.readLine())!=null){
        String[] hypernyms = line.split(",");
        al1.add(hypernyms[0]);
        for(int i=1;i< hypernyms.length;i++){
            dg.addEdge(Integer.parseInt(hypernyms[c]),Integer.parseInt(hypernyms[i]));
        }
        for(int i=1;i<hypernyms.length;i++){
            al2.add(hypernyms[i]);
        }
        //System.out.println(al2.size());    
        }
        br.close();
        }
    public void synsets(String f2) throws Exception{
        File f3=new File("E:\\ADS-2\\ADS2_2019501072\\DAY-1\\synsets.txt");        
        FileReader fr1 = new FileReader(f3);
        BufferedReader br1=new BufferedReader(fr1);
        //StringBuffer sb=new StringBuffer();
        String line1;
        while((line1=br1.readLine())!=null){
            String[] synsets = line1.split(",");
        // System.out.println(synsets[0]);  
        // System.out.println(synsets[1]); 
            al.add(synsets[0]);   
        }
        //System.out.println(al.size());
        br1.close();
    }
    public static void main(String[] args) throws Exception{
        Twod od = new Twod("synsets","hypernyms");
        //od.Synsets(f);
        // od.hypernyms("hypernyms");
        //od.hypernyms();
        // od.synsets();
        System.out.println(od.sap.length(76465,2));
        System.out.println(od.sap.length(13343,5));
        System.out.println(od.sap.length(5,7));
        System.out.println(od.sap.ancestor(8,82191));
    }
}