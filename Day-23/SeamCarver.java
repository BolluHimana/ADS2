import java.awt.Color;
public class SeamCarver{
    Picture pic;
    int width;
    int height;
    private int[][] colors;
    private double[][] distTo;
    private short[][] edgeTo;
    public SeamCarver(Picture picture){
        pic=new Picture(picture);
        width=pic.width();
        height=pic.height();
        colors = new int[width][height];
        edgeTo = new short[width][height];
        distTo = new double[width][height];

    }
    public Picture picture(){
        return pic;
    }
    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public double energy(int x,int y){
        if(x<0 || x>width()){
            throw new IndexOutOfBoundsException();
        }
        if(y<0 | y>height()){
            throw new IndexOutOfBoundsException();
        }
        if(x==0 || x==width-1){
            return 1000;
        }
        if(y==0 || y==height-1){
            return 1000;
        }
        return Math.sqrt(delta_x(x,y)+delta_y(x,y));
    }
    private double delta_x(int x,int y){
        Color right=pic.get(x-1,y);
        Color left=pic.get(x+1,y);
        return Math.pow((left.getRed()-right.getRed()),2)+Math.pow((left.getBlue()-right.getBlue()),2)+Math.pow((left.getGreen()-right.getGreen()),2);
    }
    private double delta_y(int x,int y){
        Color right=pic.get(x,y-1);
        Color left=pic.get(x,y+1);
        return Math.pow((left.getRed()-right.getRed()),2)+Math.pow((left.getBlue()-right.getBlue()),2)+Math.pow((left.getGreen()-right.getGreen()),2);
    }
    public int[] findVerticalSeam() {
        initSearch();

        for (short j = 0; j < height - 1; j++) {
            for (short i = 1; i < width - 1; i++) {
                relax(i, j, i - 1, j + 1, i);
                relax(i, j, i, j + 1, i);
                relax(i, j, i + 1, j + 1, i);
            }
        }

        double min = Double.MAX_VALUE;
        int end = 0;
        for (int i = 1; i < width - 1; i++) {
            if (distTo[i][height - 1] < min) {
                min = distTo[i][height - 1];
                end = i;
            }
        }

        int[] path = new int[height];
        for (int j = height - 1; j >= 0; j--) {
            path[j] = end;
            end = edgeTo[end][j];
        }

        return path;
    }
    private void relax(int pi, int pj, int i, int j, short edge) {
        double dis = distTo[pi][pj] + energy(i, j);
        if (dis < distTo[i][j]) {
            distTo[i][j] = dis;
            edgeTo[i][j] = edge;
        }
    }

    private void initSearch() {
        for (int i = 0; i < width; i++) {
            distTo[i][0] = 1000;
            edgeTo[i][0] = 0;
        }
        for (int j = 0; j < height; j++) {
            distTo[0][j] = 1000;
            edgeTo[0][j] = 0;
        }
        for (int j = 1; j < height; j++) {
            for (int i = 1; i < width; i++) {
                distTo[i][j] = Double.MAX_VALUE;
                edgeTo[i][j] = -1;
            }
        }
    }
    public int[] findHorizontalSeam() {
        initSearch();

        for (short i = 0; i < width - 1; i++) {
            for (short j = 1; j < height - 1; j++) {
                relax(i, j, i + 1, j - 1, j);
                relax(i, j, i + 1, j, j);
                relax(i, j, i + 1, j + 1, j);
            }
        }

        double min = Double.MAX_VALUE;
        int end = 0;
        for (int j = 1; j < height - 1; j++) {
            if (distTo[width - 1][j] < min) {
                min = distTo[width - 1][j];
                end = j;
            }
        }

        int[] path = new int[width];
        for (int i = width - 1; i >= 0; i--) {
            path[i] = end;
            end = edgeTo[i][end];
        }

        return path;
    
}

}