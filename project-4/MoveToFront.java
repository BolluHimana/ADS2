import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;
public class MoveToFront {
    private static int R = 256;
    public static void encode() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < R; i++) {
            list.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int lst = (int)(c);
            int index = list.indexOf(lst);
            list.remove(index);
            list.addFirst(lst);
            BinaryStdOut.write(index, 8);
        }
        BinaryStdOut.close();
    }
    public static void decode() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < R; i++) {
            list.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readInt(8);
            int lst = list.get(index);
            char c = (char)(lst);
            list.remove(index);
            list.addFirst(lst);
            BinaryStdOut.write(c, 8);
        }
        BinaryStdOut.close();
    }
    public static void main(String[] args) {
        
    }
}