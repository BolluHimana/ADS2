class SAP{
    int INFINITY = Integer.MAX_VALUE;
    Digraph g;
    int integer = Integer.MAX_VALUE;
    public SAP(Digraph g){
        this.g=g;
    }
    public int length(int ver,int edg){
        if(ver<0 || ver>g.V()-1 || edg<0 || edg>g.V()-1 ) throw new IndexOutOfBoundsException();
        return l(ver,edg)[1];
    }
    public int ancestor(int ver,int edg){
        if(edg<0|| ver>g.V()-1||ver<0||edg>g.V()-1) throw new IndexOutOfBoundsException();
        return l(ver,edg)[0];
    }
    public int[] l(int ver,int edg){
        int[] l= new int[2];
        int d = INFINITY;
        int a =-1;
        BreadthFirstDirectedPaths b = new BreadthFirstDirectedPaths(g,ver);
        BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(g,edg);
        for(int v=0;v<g.V();v++){
            if(b.hasPathTo(v) && b1.hasPathTo(v)){
                if(b.distTo(v)+ b1.distTo(v)<d){
                    d=b.distTo(v)+b1.distTo(v);
                    a=v;
                }
            }
        }
        l[0]=a;
        l[1]=(d==INFINITY?-1:d);
        return l;
    }
}