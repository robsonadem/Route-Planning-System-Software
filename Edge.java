
/**
 * Write a description of class Edge here.
 * 
 * @author Robson 
 * @version (a version number or a date)
 */
public class Edge<E>
{
    protected Vertex<E> start,end;

    protected int weight;
    /**
     * Constructor for objects of class Warehouse
     */ 
    public Edge(Vertex<E> start, Vertex<E> end, int weight){
        this.start=start;
        this.end=end;
        this.weight=weight;
    }

}