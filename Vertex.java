import java.util.*;
/**
 * Write a description of class Node here.
 * 
 * @author Robson
 * @version (a version number or a date)
 */
public class Vertex<E>
{

    protected ArrayList<Edge<E>> outgoingEdges;
    protected int x;
    protected int y;
    protected E key;
    
    //boolean variable that state whether the vertex has been visited or not
    private boolean visited=false;

   /**
     * Constructor for objects of class Vertex
     */
    public Vertex(int x ,int y,E key){
        this.x=x;
        this.y=y;
        this.key = key;
        outgoingEdges= new ArrayList<Edge<E>>();
        visited=false;
    }

    /**
     * A method to return the Nth neighbor from a neighborlist
     * @param       int Nth
     * @return      Vertex<E> v
     */    
    public Vertex<E> closestNeighbor(int Nth)
    {
        return outgoingEdges.get(Nth).end;
    }
    
    /**
     * A method to add edge to a vertex
     * @param      Edge<E> e
     */
    public void  addEdge(Edge<E> e)
    {
        outgoingEdges.add(e);
    }
    
    /**
     * A method to return a neighbors list at a vertex
     * @return      ArrayList<Edge<E>> list
     */ 
    public ArrayList<Edge<E>> getNeighborList()
    {
        return outgoingEdges;
    }

    
   /**
     * A method to get the distance from another vertex
     * @param       Vertex<E> w
     * @return       int distance
     */ 
    public int getDistance(Vertex<E> w)
    {
        int x = Math.abs(w.x-this.x);
        int y = Math.abs(w.y-this.y);
        return x+y;
    }
    
    /**
     *method that set the vertex as has been visited
     */
    public void hasVisited()
    {
        visited=true;
    }
    
    /**
     *method that set the vertex as has not been visited
     */
    public void notVisited()
    {
        visited=false;
    }
    
    /**
     *method that check whether the vertex has been visited or not
     *@ return boolean variable visited
     */
    public boolean checkVisit()
    {
        return visited;
    }

}