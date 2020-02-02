import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
public class SeamCarver {
private Picture picture;
public SeamCarver(Picture picture) {
if (picture == null) {
throw new IllegalArgumentException();
}
this.picture = new Picture(picture);
}
public Picture picture() {
return picture;
}
public int width() {
return picture.width();
}
public int height() {
return picture.height();
}
public double energy(int x, int y) {
    if(x<0 || y<0 || x>=width() || y>=height())
        throw new java.lang.IllegalArgumentException();
    if(x==0 || y==0 || x==width()-1 ||y==height()-1)
        return 1000;
    double squareX = 0.0, squareY = 0.0;
    Color a1,a2,b1,b2;
    a1 = picture.get(x-1,y);
    a2 = picture.get(x+1,y);
    b1 = picture.get(x,y-1);
    b2 = picture.get(x,y+1);
    squareX = Math.pow((a1.getRed()-a2.getRed()),2) + Math.pow((a1.getGreen()-a2.getGreen()),2) + Math.pow((a1.getBlue()-a2.getBlue()),2);
    squareY = Math.pow((b1.getRed()-b2.getRed()),2) + Math.pow((b1.getGreen()-b2.getGreen()),2) + Math.pow((b1.getBlue()-b2.getBlue()),2);
    return Math.sqrt(squareX + squareY);
}
public int[] findHorizontalSeam() {
double[][] energyMatrix = buildEnergyMatrix();
return findSeam(energyMatrix, width(), height());
}
private void initMatrices(double[][] distTo, int[][] from, int w, int h) {
    for (int i = 0; i < w; i++) {
    for (int j = 0; j < h; j++) {
    distTo[i][j] = i == 0 ? 0 : Double.POSITIVE_INFINITY;
    from[i][j] = i == 0 ? 0 : -1;
}    }   }
private double[][] buildEnergyMatrix() {
    double[][] energy = new double[width()][height()];
    for (int i = 0; i < width(); i++) {
    for (int j = 0; j < height(); j++) {
    energy[i][j] = energy(i, j);
    }
    }
    return energy;
}
public int[] findVerticalSeam() {
    double[][] energyMatrix = transpose(buildEnergyMatrix());
    return findSeam(energyMatrix, height(), width());
}
    
private int[] findSeam(double[][] energyMatrix, int w, int h) {
double[][] distTo = new double[w][h];
int[][] from = new int[w][h];
initMatrices(distTo, from, w, h);
for (int i = 1; i < w; i++) {
for (int j = 0; j < h; j++) {
double e = Double.POSITIVE_INFINITY;
int eFrom = -1;
if (j != 0) {
if (distTo[i-1][j-1] < e) {
e = distTo[i-1][j-1];
eFrom = j-1;
}
}
if (distTo[i-1][j] < e) {
e = distTo[i-1][j];
eFrom = j;
}
if (j != (h - 1)) {
if (distTo[i-1][j+1] < e) {
e = distTo[i-1][j+1];
eFrom = j+1;
}
}
distTo[i][j] = e + energyMatrix[i][j];
from[i][j] = eFrom;
}
}
double m = Double.POSITIVE_INFINITY;
int a = -1;
for (int j = 0; j < h; j++) {
double e = distTo[w - 1][j];
if (e < m) {
m = e;
a = j;
}
}
int[] seam = new int[w];
seam[w - 1] = a;
for (int i = w - 2; i > 0; i--) {
seam[i] = from[i+1][seam[i + 1]];
}
return seam;
}
private double[][] transpose(double[][] m) {
double[][] mt = new double[m[0].length][m.length];
for (int i = 0; i < mt.length; i++) {
for (int j = 0; j < mt[0].length; j++) {
mt[i][j] = m[j][i];
}
}
return mt;
}
public void removeHorizontalSeam(int[] seam) {
if (seam == null || seam.length != width()) {
throw new IllegalArgumentException("Invalid seam");
}
Picture newPicture = new Picture(width(), height() - 1);
for (int i = 0; i < width(); i++) {
for (int j = 0, jn = 0; j < height(); j++) {
if (j != seam[i]) {
newPicture.set(i, jn, picture.get(i, j));
jn++;
}
}
}
this.picture = newPicture;
}
public void removeVerticalSeam(int[] seam) {
if (seam == null || seam.length != height()) {
throw new IllegalArgumentException();
}
Picture newPicture = new Picture(width() - 1, height() );
for (int j = 0; j < height(); j++) {
for (int i = 0, in = 0; i < width(); i++) {
if (i != seam[j]) {
newPicture.set(in, j, picture.get(i, j));
in++;
}
}
}
this.picture = newPicture;
}
}  