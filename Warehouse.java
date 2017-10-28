import java.util.*;
/**
 * Write a description of class Warehouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Warehouse<E> extends Vertex<E>
{
    private int numOfTrucks;
    //private boolean NoTruck=false;
    /**
     * Constructor of the class 
     */
    public Warehouse(int x, int y, E key, int numOfTrucks)
    {
        super(x,y,key);
        this.numOfTrucks= numOfTrucks;
    }

    /**
     * A method to   return the number of trucks of a warehouse
     * @return       int the num of trucks
     */ 
    public int getNumOfTruck()
    {
        return numOfTrucks;
    }

    /**
     * A method to decrease the num of trucks
     */
    public void decreaseTruck()
    {
        numOfTrucks=numOfTrucks-1;
    }

    /**
     * A method to return the Nth neighbor from a neighborlist
     * @param       int Nth
     * @return      CoffeeShop<E>
     */    
    public CoffeeShop<E> closestNeighbor(int Nth)
    {
        return (CoffeeShop<E>)outgoingEdges.get(Nth).end;
    }

    /**
     *method that check whether there has truck left in the warehouse or not
     *@ return true if the warehosue run out of trucks
     */
    public boolean noTruck()
    {
        //if the number of trucks equal to 0, return true
        if(numOfTrucks==0)    
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    
    
    /**
     *method that check the number of coffeeshops that close to the warehouse within certain distance
     *@ param the number of warehouses, using to calculate the approprite distance threshold
     *@ return true if the there are more than 5 coffee shops locate within the threshold distance to the warehouse
     */
    public boolean checkAround(int numberOfWarehouse)
    {
        //variable that store number of coffee shop that close to the warehouse
        int numOfCloserShop=0;
        //iterate through the outgoingedge list and increase the numOfCloserShop by 1 if the distance is less than the threshold
        for(int n=0; n<outgoingEdges.size();n++)
        {
            if(outgoingEdges.get(n).weight<=100/numberOfWarehouse && !((CoffeeShop<E>)outgoingEdges.get(n).end).orderFinish() )
            {
                numOfCloserShop=numOfCloserShop+1;
            }
            
        }
        //if more than 5 coffeeshop is locate within the threshold distance to the warehouse, return true
        if(numOfCloserShop>5)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    
    

}
