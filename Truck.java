import java.util.*;
/**
 * Write a description of class Truck here.
 * 
 * @author Robson Adem and Tianyu Zhu
 * @version 
 */
public class Truck<E>
{
    private ArrayList<Vertex<E>> route;
    private int capacity;
    private int distance;
    private Vertex<E> currentLocation;
    private Warehouse<E> startVertex;
    //private Vertex prevVertex;
    /**
     * Constructor for objects of class Truck
     */
    public Truck(Warehouse<E> startVertex)
    {
        capacity=500; 
        distance=0;
        this.startVertex=startVertex;
        currentLocation = startVertex;
        route= new ArrayList<Vertex<E>>();
        route.add(startVertex);//add the warehouse to the route 

    }

    /**
     * A method to get the capacity of a truck
     * @return       int capacity
     */
    public int getCapacity()
    {
        return capacity;
    }

    

    /**
     * A method to get the ditance traveled by a truck
     * @return       int distance
     */
    public int getDistance()
    {
        return distance;
    }
    
    /**
     * A method to get the current loaction of a truck
     * @return       Vertex<E> location
     */
    public Vertex<E> getCurrentLocation()
    {
        return currentLocation;
    }

    
    /**
     * A method to  deliver cargo to shops
     * @return       boolean true if successful
     */
    public boolean deliver(CoffeeShop<E> c)
    {
       
        if(c.checkVisit()==true)
        {
            return false;
        }
        
        if(c.orderFinish())
        {
           return false;
        }   

        
        int order=(int)c.getOrders().peek();
        
        //not enough capacity to deliver

        if(capacity<order)
        { 
            return false;// delivery was not successful, so finalize the trip;
        }
        //if there is enough capacity deliver the cargo and add the shop to the route
        
        
        capacity=capacity-order;//update capacity
        
        c.getOrders().poll();
        int distanceTraveled  = currentLocation.getDistance(c);
        distance=distance+distanceTraveled;
        currentLocation=c;
        route.add(c);
        c.hasVisited();
        return true;

    }

    /**
     * A method to get the status of the trucsk
     * @return      boolean true if empty
     */
    public boolean isEmpty()
    {
        if(capacity==0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
     * A method to finalize the route of a truck going back to the starting warehouse
     */
    public void finishRoute() 
    {
        
        int distanceTraveled  = currentLocation.getDistance(startVertex);
        distance=distance+distanceTraveled;
        for(int i=1;i<route.size();i++)
        {
            route.get(i).notVisited();
        }
        route.add(startVertex);

        
        for(int i=0;i<route.size();i++)
        {
             System.out.print(route.get(i).key+"  ");
             
        }
        currentLocation=startVertex;
        System.out.print("\n");
        
        

    }

}
