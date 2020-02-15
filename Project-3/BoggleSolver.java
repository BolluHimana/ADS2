import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.HashSet;
public class BoggleSolver {
    private Node root;
    private HashSet<String> words;
    public BoggleSolver(String[] dictionary) {
        for (String word : dictionary)
            insert(word);
    }
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        words = new HashSet<>();
        for (int row = 0; row < board.rows(); row++)
            for (int col = 0; col < board.cols(); col++)
                getWords(board, root, "", row, col, 0, new boolean[board.rows()][board.cols()]);
        return words;
    }
    private void getWords(BoggleBoard board, Node node, String word, int row, int col, int i, boolean[][] marked) {
        if (validate(board, row, col, marked)) {
            word += (getLetter(board, row, col));
            Node x = get(node, word, i++);
            if (getLetter(board, row, col).equals("QU")) i++;
            if (x != null) {
                if (x.word && word.length() > 2) words.add(word);
                boolean[][] visited = clone(marked, row, col);
                getWords(board, x.mid, word, row, col - 1, i, visited);
                getWords(board, x.mid, word, row, col + 1, i, visited);
                getWords(board, x.mid, word, row - 1, col, i, visited);
                getWords(board, x.mid, word, row + 1, col, i, visited);
                getWords(board, x.mid, word, row + 1, col + 1, i, visited);
                getWords(board, x.mid, word, row - 1, col - 1, i, visited);
                getWords(board, x.mid, word, row + 1, col - 1, i, visited);
                getWords(board, x.mid, word, row - 1, col + 1, i, visited);
            }
        }
    }
    private String getLetter(BoggleBoard board, int row, int col) {
        String str = "";
        char letter = board.getLetter(row, col);
        if (letter == 'Q')
        str += "QU";
        else str += letter;
        return str;
    }
    private boolean validate(BoggleBoard board, int row, int col, boolean[][] marked) {
        return col < board.cols() && col >= 0 && row < board.rows() && row >= 0 && !marked[row][col];
    }
    private boolean[][] clone(boolean[][] marked, int row, int col) {
        boolean[][] Markedd= new boolean[marked.length][marked[0].length];
        for (int i = 0; i < marked.length; i++)
            for (int j = 0; j < marked[0].length; j++)
                Markedd[i][j] = marked[i][j];
        Markedd[row][col] = true;
        return Markedd;
    }
    public int scoreOf(String word) {
        if (contains(word))
            if (word.length() < 3) return 0;
            else if (word.length() < 5) return 1;
            else if (word.length() < 6) return 2;
            else if (word.length() < 7) return 3;
            else if (word.length() < 8) return 5;
            else return 11;
        else return 0;
    }
    private class Node {
        private boolean word;
        private char c;
        private Node left, mid, right;

        Node(char c) {
            this.c = c;
        }
    }
    private void insert(String key) {
        root = insert(root, key, 0);
    }
    private Node insert(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) x = new Node(c);
        if (c < x.c) x.left = insert(x.left, key, d);
        else if (c > x.c) x.right = insert(x.right, key, d);
        else if (d < key.length() - 1) x.mid = insert(x.mid, key, d + 1);
        else x.word = true;
        return x;
    }

    private boolean contains(String key) {
        Node x = get(root, key, 0);
        return x.word;
    }

    private Node get(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) return null;
        else if (c < x.c)
            return get(x.left, key, d);
        else if (c > x.c)
            return get(x.right, key, d);
        else if (d < key.length() - 1)
            return get(x.mid, key, d + 1);
        else
            return x;
    }
}