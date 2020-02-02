import java.awt.Color;
public class SeamCarver{
    Picture pic;
    int width;
    int height;
    public SeamCarver(Picture picture){
        pic=new Picture(picture);
        width=pic.width();
        height=pic.height();
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
            return 0;
        }
        if(y==0 || y==height-1){
            return 0;
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
}