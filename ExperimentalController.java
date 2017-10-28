import java.util.*;
/**
 * ExperimentalController class that conduct the test for this program
 * 
 */
public class ExperimentalController
{
    
    public static void main(String [] args)
    {
        Scheduler s = new Scheduler();
         //call the addShop method to load in all the coffeeshops
        s.addShop(args[0]);      
        //call the addwarehouses method to load in all the warehouses
        s.addWarehouses(args[1]);
        //call the loadEdge method to create edges between thoes coffeeshops and warehouses
        s.loadEdge();     
        //do the simulation and print out the total distance traveled 
        System.out.println(s.Simulate());
    }
}
