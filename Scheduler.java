import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Scheduler class that contains methods to read the coffeeshop and warehouse file and create a map for them, and the method to do the simulation
 * of the delivery process and minimize the total distance traveled.
 * 
 * @author Robson Adem and Tianyu Zhu
 * @version 
 */
public class Scheduler
{
    //instantiate the gragh 
    private Graph<String> town;

    
    /**
     * Constructor for the class that create a new graph 
     */
    public Scheduler()
    {
        town = new Graph<String>();
    }
    
    /**
     * main method of the class that take the coffeeshop file's name and warehouse file's name as two input parameters
     */
    public static void main(String [] args)
    {
        //call the constructor
        Scheduler s= new Scheduler();

        //call the addShop method to load in all the coffeeshops
        s.addShop(args[0]);      
        //call the addwarehouses method to load in all the warehouses
        s.addWarehouses(args[1]);
        //call the loadEdge method to create edges between thoes coffeeshops and warehouses
        s.loadEdge();     
        //do the simulation and print out the total distance traveled 
        System.out.println(s.Simulate());

    }
    
    /**
     * method that read in the coffeeshop file and create coffeeshop then add them in the graph
     * @ param fileName the name of the coffeeshop file
     */
    public void addShop(String fileName)
    {
        //read file         
        try
        {
            //file reader
            FileReader fin = new FileReader(fileName);
            //create a new scanner
            Scanner graphFile = new Scanner(fin);

            //String variable 
            String line;
            //while the file has next line
            while(graphFile.hasNextLine())
            {

                //get the next line in the file
                line=graphFile.nextLine();

                //create a new StringTokenizer at that line
                StringTokenizer st = new StringTokenizer(line);
                //read through that line
                try
                {
                    //if there is only 1 string in this line
                    if(st.countTokens()==1)
                    {
                        // pick up this String as the number of coffeeshop in total 
                        int numOfShops = Integer.parseInt(st.nextToken());
                        //continue to next iteration of the loop 
                        continue;
                    }

                    //if more than 1 String in the line
                    //take the first String and store as the id of coffeeShop
                    String id = ("S"+Integer.parseInt(st.nextToken()));
                    //take the next String which contains the X coordinate of the coffeeshop
                    String coordinateX = st.nextToken();
                    //create a new StringTokenizer on this String
                    StringTokenizer st1 = new StringTokenizer(coordinateX,"(,");
                    //take the first String and store it as the x coordinate of this coffeeshop 
                    int x= Integer.parseInt(st1.nextToken());
                    //take the next String which contains the Y coordinate of the coffeeshop
                    String coordinateY = st.nextToken();
                    //create a new StringTokenizer on this String
                    StringTokenizer st2 = new StringTokenizer(coordinateY,"):");
                     //take the first String and store it as the y coordinate of this coffeeshop 
                    int y= Integer.parseInt(st2.nextToken());
                    //create a new Queue implement by linkedlist to store the order of the warehouse
                    Queue<Integer> cargoList= new LinkedList<Integer> ();
                    //while the line has more Strings inside it 
                    while(st.hasMoreTokens())
                    {
                        //take the next String in the line and store it as cargo
                        String cargo =st.nextToken();
                        //create a new StringTokenizer on this String
                        StringTokenizer st3 = new StringTokenizer(cargo,",");
                        //take the first String and store it as the order of this coffeeshop 
                        Integer order =Integer.parseInt(st3.nextToken());

                        //add the order into the cargo list
                        cargoList.add(order);
                       

                    }

                    //add the coffeeshop into the graph 
                    town.addCoffeeShop(x,y,id, cargoList);



                }
                //catch exceptions
                catch(NumberFormatException e )
                { System.err.println( "Skipping bad line " + line ); }

            }

        }
        //catch exceptions 
        catch( IOException e )
        { System.err.println( e ); }
    }

    
    /**
     * method that read in the warehouse file and create warehouse then add them in the graph
     * @ param fileName the name of the warehouse file
     */
    public void addWarehouses(String fileName)
    {
         //read file     
        try
        {
            //file reader
            FileReader fin = new FileReader(fileName);
            //create a new scanner
            Scanner graphFile1 = new Scanner(fin);

            //String variable 
            String line;
            //while the file has next line
            while(graphFile1.hasNextLine())
            {
                //get the next line in the file
                line=graphFile1.nextLine();
                //create a new StringTokenizer at that line
                StringTokenizer st = new StringTokenizer(line);
                //read through that line
                try
                {
                    //if there is only 1 string in this line
                    if(st.countTokens()==1)
                    {
                        // pick up this String as the number of warehouse in total 
                        int numOfWarehouse = Integer.parseInt(st.nextToken());
                        //continue to next iteration of the loop 
                        continue;
                    }
                    //if more than 1 String in the line
                    //take the first String and store as the id of warehouse
                    String id = ("W"+Integer.parseInt(st.nextToken()));
                    //take the next String which contains the X coordinate of the warehouse
                    String coordinateX = st.nextToken();
                    //create a new StringTokenizer on this String
                    StringTokenizer st1 = new StringTokenizer(coordinateX,"(,");
                    //take the first String and store it as the x coordinate of this warehouse 
                    int x= Integer.parseInt(st1.nextToken());

                    //take the next String which contains the Y coordinate of the warehouse
                    String coordinateY = st.nextToken();
                    //create a new StringTokenizer on this String
                    StringTokenizer st2 = new StringTokenizer(coordinateY,"):");
                    //take the first String and store it as the y coordinate of this warehouse 
                    int y= Integer.parseInt(st2.nextToken());
                    //take the next String which contains number of truck of the warehosue
                    String Truck =st.nextToken();
                    //create a new StringTokenizer on this String
                    StringTokenizer st3 = new StringTokenizer(Truck,",");
                    //take the next String and store it as the number of trucks the warehouse has 
                    int numOfTruck =Integer.parseInt(st3.nextToken());

                    //add the warehouse into the graph 
                    town.addWarehouse(x,y,id, numOfTruck);
                }
                catch(NumberFormatException e )
                { System.err.println( "Skipping bad line " + line ); }

            }

        }
        catch( IOException e )
        { System.err.println( e ); }

        
    }

    /**
     * method that create the edge between all these coffeeshops and warehouses
     */
    public void loadEdge()
    {
        town.createConnection();
    }
    
    /**
     * method that simulate the delivery process and minimize the total distance traveled
     * @ return the total distance traveled 
     */
    public int Simulate()
    {
        return town.planRoute();
    }

}
