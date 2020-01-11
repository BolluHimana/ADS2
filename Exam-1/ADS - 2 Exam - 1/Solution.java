import java.util.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileReader;
class Solution{
    ArrayList<String> al;
    ArrayList<String> al1;
    ArrayList<String> al2;
    Digraph dg;
    Solution(String email, String log) throws Exception{
        al= new ArrayList<>();
        al1= new ArrayList<>();
        al2 = new ArrayList<>();
        this.email(email);
        dg = new Digraph(al.size());
        this.log(log);
        System.out.println(dg.size());
        int c=0;
        for(int i=0;i<dg.size();i++){
            c=c+dg.adj[i].size();
        }
        System.out.println(c);
    }
    
    public void email(String f)throws Exception {
        File f1=new File("E:\\ADS-2\\ADS2_2019501072\\Exam-1\\ADS - 2 Exam - 1\\emails.txt");        
        FileReader fr = new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);
        String line;
        int c=10 ;
        while((line=br.readLine())!=null){
        String[] email = line.split(";");
        al1.add(email[0]); 
          System.out.println(email[0]);
          System.out.println(email[1]);       
        }
        br.close();
        }
    public void log(String f2) throws Exception{
        File f3=new File("E:\\ADS-2\\ADS2_2019501072\\Exam-1\\ADS - 2 Exam - 1\\email-logs.txt");        
        FileReader fr1 = new FileReader(f3);
        BufferedReader br1=new BufferedReader(fr1);
        String line1;
        while((line1=br1.readLine())!=null){
        String[] log = line1.split(",");
        System.out.println(log[0]);  
        }
        br1.close();
    }
    public static void main(String[] args) throws Exception{
    Solution sl = new Solution("log","email");
        sl.email("f");
        sl.log("f2");
    }


}