import java.util.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileReader;
class oned{
    public void hypernyms(String f)throws Exception {
        File f1=new File("E:\\ADS-2\\ADS2_2019501072\\DAY-1\\hypernyms.txt");        
        FileReader fr = new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);
        //StringBuffer sb=new StringBuffer();
        String line;
        while((line=br.readLine())!=null){
        String[] hypernyms = line.split(",");
        System.out.println(hypernyms[0]);    
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
        System.out.println(synsets[0]);  
        System.out.println(synsets[1]);    
        }
        br1.close();
    }
    public static void main(String[] args) throws Exception{
        oned od = new oned();
        //od.Synsets(f);
        od.hypernyms("f");
        od.synsets("f2");
       

    }


}