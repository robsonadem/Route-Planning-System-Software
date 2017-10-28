import java.util.*;
/**
 * Write a description of class CoffeShop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoffeeShop<E> extends Vertex<E>
{

    private Queue<Integer> OrderList;
    
    
    /**
     * Constructor for objects of class CoffeShop
     */
    public CoffeeShop(int x, int y,E key,Queue<Integer> Order)
    {
        super(x,y,key); 
        this.OrderList=Order;
        
    }
    
    /**
     * A method to return all the orders
     * @return       Queue<Integer> a queue of order
     */     
    public Queue<Integer> getOrders()
    {
        return OrderList;
    }
    
    /**
     * A method to return the Nth neighbor from a neighborlist
     * @param       int Nth
     * @return      CoffeeShop<E>
     */    
    public CoffeeShop<E> closestNeighbor(int Nth)
    {
        //return the nth edge's end vertex in the outgoingedge list of the vetex
        return (CoffeeShop<E>)outgoingEdges.get(Nth).end;
    }
    
    
    
  
    /**
     *method that check whether the coffeeshop finish its orders
     * @ return orderFinish
     */
    public boolean orderFinish()
    {
        if(OrderList.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

}
