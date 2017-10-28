import java.util.*;
/**
 * Generic Graph class that uses hashMap to store all the vertices and uses ArrayList to track coffeeshop and warehouse. It contains methods
 * for adding vertex and edge and also the algorithm of traversing through the map. 
 * 
 * 
 * @Tianyu Zhu
 * @2017.05.5
 */

public class Graph<E>
{  

    //vertex map that store all the vertex and their mapping
    private Map<E,Vertex<E>> vertexMap;
    //ArrayList that tracks all the coffeeshop vertices that  in the graph 
    private ArrayList<CoffeeShop<E>> coffeeshopList;
    //ArrayList that tracks all the warehouse vertices that in the graph
    private ArrayList<Warehouse<E>> WarehouseList;
    //variable that store the total distance for all trucks traveled
    private int totalDistance;

    /**
     *constructor of the class that create a empty graph
     */
    public Graph()
    {
        //initiate the totalDistance to 0
        totalDistance=0;
        //instantiate the hash map 
        vertexMap = new HashMap<E,Vertex<E>>( );
        //instantiate the ArrayList stores Coffeeshop
        coffeeshopList=new ArrayList<CoffeeShop<E>>();
        //instantiate the ArrayList stores warehouse
        WarehouseList = new ArrayList<Warehouse<E>>();

    }

    /**
     * method that Add a coffeeshop
     * @param k, the key of coffeeshop
     * @param x,y, the coordinates of coffeeshop
     * @param Order, the order list of coffeeshop stores in a Queue
     * @return true if the adding success. if there are duplicated item in the tree, return false.
     */
    public boolean addCoffeeShop(int x, int y, E k, Queue<Integer> Order)
    {
        //if the graph already contain the key, return false
        if(vertexMap.containsKey(k))
        {
            return false;
        }
        //if the graph doesn't have this key
        else
        {
            //create a new coffeeShop with the key k, coordinate x,y and order list
            CoffeeShop<E> newCoffeeShop = new CoffeeShop<E>(x,y,k,Order);
            //map this key with the new coffeeShop  in the vertexMap
            vertexMap.put(k,newCoffeeShop); 
            //add this coffeeShop to the coffeeShop list
            coffeeshopList.add(newCoffeeShop);
            //return true
            return true;
        }

    }

    /**
     * method that Add a warehouse
     * @param k, the key of warehouse
     * @param x,y, the coordinates of warehouse
     * @param numberOfTruck, the number of trucks that warehouse has
     * @return true if the adding success. if there are duplicated item in the tree, return false.
     */
    public boolean addWarehouse(int x, int y ,E k, int numberOfTruck)
    {
        //if the graph already contain the key, return false
        if(vertexMap.containsKey(k))
        {
            return false;
        }
        //if the graph doesn't have this key
        else
        {
            //create a new wareHouse with the key k, coordinate x,y and number of trucks 
            Warehouse<E> newWarehouse = new Warehouse<E>(x,y,k,numberOfTruck);
            //map this key with the new warehouse in the vertexMap
            vertexMap.put(k,newWarehouse); 
            //add this warehouse to the ArrayList that stores warehouse
            WarehouseList.add(newWarehouse);
            //return true
            return true;
        }

    }

    /**
     * method that Add a edge
     * @param k1, the key of vertex 1
     * @param k2, the key of vertex 2
     * @param w, the weight of the edge
     */
    public void addEdge(E k1,E k2,int w)
    {
        //get the vertex with the key k1 and store as start node
        Vertex<E> start = vertexMap.get(k1);
        //get the vertex with the key k2 and store as end node
        Vertex<E> end = vertexMap.get(k2);

        //for loop to store the edge in an increasing order into the outgoingEdge list of the start vertex
        for(int i=0; i<start.outgoingEdges.size();i++)
        {
            //if the given weight is smaller than the weight of current edge's weight in tht outgoingEdge list
            if(w<start.outgoingEdges.get(i).weight)
            {
                //create a new edge from start vertex to end vertex with weight w and add it into the current location of the outgoing edge list of start vertex
                start.outgoingEdges.add(i,(new Edge<E>(start, end, w)));
                //end the loop
                return;
            }
        }

        //if the given weight is larger than all the edges'weight in the list, create a new edge and add it at the end of the list
        start.outgoingEdges.add(new Edge<E>(start, end, w));

    }

    /**
     * method that creats all the connecting edges from warehouse to coffeeShop and coffeeShop to coffeeShop
     */
    public void createConnection()
    {

        //for loop that iterate through the coffeeshop list 
        for(int i=0;i<coffeeshopList.size();i++)
        {
            //for loop that iterate through the coffeeshop list
            for(int j=0;j<coffeeshopList.size();j++)
            {
                //if two identical coffeeshop meets,continue to next iteration
                if(i==j)
                {

                    continue;

                }

                else
                {
                    //create an edge between these two coffee shops with the weight of distance between them
                    addEdge(coffeeshopList.get(i).key,coffeeshopList.get(j).key,coffeeshopList.get(i).getDistance(coffeeshopList.get(j)));
                }
            }

        }

        //add edge between warehouse and coffeeshop
        //for loop iterates through the warehouse list
        for(int i=0; i< WarehouseList.size();i++)
        {
            //for loop iterates through the coffeeshop list
            for(int j=0;j<coffeeshopList.size();j++)
            {
                //add the edge between the warehouse and the coffeeshop with the weight of the distance between them 
                addEdge(WarehouseList.get(i).key, coffeeshopList.get(j).key, WarehouseList.get(i).getDistance(coffeeshopList.get(j)));

            }
        }

    }

    /**
     * method that check whether all the coffeeshops finish their orders 
     * @return true when all coffeeshops finish their orders 
     */
    public boolean checkShopOrder()
    {
        //variable that store the number of coffeeshops that finished their orders
        int numOfShopDone=0;

        //iterates through the coffeeshoplist
        for(CoffeeShop<E> c: coffeeshopList)
        {
            //if the coffeeshop finish its orders
            if(c.orderFinish())
            {
                //increase numOfShopDone by 1 
                numOfShopDone=numOfShopDone+1;
            }

        }
        //if the number of coffeeshops that have finished the orders is equal to total number of coffeeshop,return true
        if(numOfShopDone==coffeeshopList.size())
        {

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * method that check whether all the warehouses run out of trucks except the main warehouse 
     * @return true when all warehouse except the main warehouse run out of trucks 
     */
    public boolean checkWarehouseTruck()
    {
        //variable that store the number of warehouses that run out of trucks
        int outOfTruck=0;
        //iterate through the warehouselist except the main warehouse
        for(int i=0;i<WarehouseList.size()-1;i++)
        {
            //get the ith warehouse 
            Warehouse<E> w= WarehouseList.get(i);
            //if the warehouse is run out of truck, increase the outOfTruck by 1
            if(w.noTruck())
            {
                outOfTruck=outOfTruck+1;
            }

        }
        //if all warehouses except the main warehouse are run out of trucks, return true
        if(outOfTruck==WarehouseList.size()-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * method that using the greedy algorithm to let truck traverse through the vertex on the map with the shortest distance traveled in total
     * @return the total distance traveled by all trucks
     */
    public int planRoute()
    {

        //boolean variable which is the condition of the loop
        boolean next;

        //variable that store the number of warehouse that still has trucks remained, initiated to be 0
        int warehouseHasTruck=0;
        //boolean variable that is the condition of the loop, initiated to be 0
        boolean finishLoop=true;

        // if there are more than 1 warehouse, we ignore the main warehouse first. Otherwise, if there is only main warehouse, we start from the main warehouse
        if(WarehouseList.size()>1)
        {
            //iterate through the warehouse list except the main warehouse and check if any warehouse has many coffeeshops close to it(threshold is 5 coffeeshops and distance is 100/10=10), then exault that warehouse's 
            //trucks until the number of remainnig coffeeshops within the distance is lower than the threshold. 
            for(int i=0; i<WarehouseList.size()-1;i++)
            {

                //get the ith warehouse from the warehouse list
                Warehouse<E> w= WarehouseList.get(i);
                //while there are coffeeshop near the warehouse close to it and the numebr of trucks left and there is no coffeeshop finish its orders
                while(w.checkAround(WarehouseList.size())&&!w.noTruck()&&!checkShopOrder())
                {
                    //create a new truck start from that warehouse
                    Truck<E> t =new Truck<E>(w);
                    //while the finishLoop is true                
                    while(!w.noTruck())
                    {
                        //set the next to be false
                        next=false;
                        //get the current vertex of the truck
                        Vertex<E> current=t.getCurrentLocation();
                        //for loop iterates through the outgoingedge list of the current vertex 
                        for(int j=0;j<current.outgoingEdges.size();j++)
                        {

                            //creat a vertex and set it to be the jth closest neighbor of the current vertex
                            Vertex<E> nextShop=current.closestNeighbor(j);

                            //truck deliver to the nextshop vertex, if deliver succeed, set next to be true and break the loop, otherwise continue the next iteration
                            if(t.deliver(((CoffeeShop<E>)nextShop)))
                            {
                                //set next to be true
                                next=true;
                                //break the loop
                                break;

                            }

                        }
                        //if there is no coffeeshop near the current vetex is available to deliver, then end this route
                        if(next==false)
                        {
                            //let the truck finish the route 
                            t.finishRoute();
                            //decrease the number of truck by 1 in this warehouse
                            w.decreaseTruck();
                            //update the total distance traveled
                            totalDistance=totalDistance+t.getDistance();
                            //break the loop
                            break;
                        }
                    }
                }

            }

            //while loop that iterate throught the the warehouse list as long as the number of warehouse that still has trucks left is not equal to the total number of 
            //warehouses excepet the last warehouse (main warehouse)
            while(!checkWarehouseTruck())
            {


                //for loop iterates through the warehouse list 
                for(int i=0; i<WarehouseList.size()-1;i++)
                {
                    //check if all the coffeeshop's orders have been delivered, if so, return the total distance traveled
                    if(checkShopOrder())
                    {
                        //return the total distance traveled 
                        return totalDistance;
                    }

                    //get the ith warehouse from the warehouse list
                    Warehouse<E> w= WarehouseList.get(i);

                    //create a new truck start from that warehouse
                    Truck<E> t =new Truck<E>(w);


                    //while the finishLoop is true                
                    while(!w.noTruck())
                    {
                        //set the next to be false
                        next=false;
                        //get the current vertex of the truck
                        Vertex<E> current=t.getCurrentLocation();
                        //for loop iterates through the outgoingedge list of the current vertex 
                        for(int j=0;j<current.outgoingEdges.size();j++)
                        {

                            //creat a vertex and set it to be the jth closest neighbor of the current vertex
                            Vertex<E> nextShop=current.closestNeighbor(j);

                            //truck deliver to the nextshop vertex, if deliver succeed, set next to be true and break the loop, otherwise continue the next iteration
                            if(t.deliver(((CoffeeShop<E>)nextShop)))
                            {
                                //set next to be true
                                next=true;
                                //break the loop
                                break;

                            }

                        }
                        //if there is no coffeeshop near the current vetex is available to deliver, then end this route
                        if(next==false)
                        {
                            //let the truck finish the route 
                            t.finishRoute();
                            //decrease the number of truck by 1 in this warehouse
                            w.decreaseTruck();
                            //update the total distance traveled
                            totalDistance=totalDistance+t.getDistance();

                            //break the loop
                            break;
                        }

                    }
                }
            }
        }
        //get the main warehouse from the warehouse list
        Warehouse<E> w= WarehouseList.get(WarehouseList.size()-1);

        //while there still have coffeeshops that have orders left
        while(!checkShopOrder())
        {
            //create a new truck start from the main warehouse 
            Truck<E> t =new Truck<E>(w);

            //while the warehouse still have trucks 
            while(!w.noTruck())
            {

                //get the current location of the truck
                Vertex<E> current=t.getCurrentLocation();
                //reset the next to be false
                next=false;
                //iterates through the outgoingedge list
                for(int j=0;j<current.outgoingEdges.size();j++)
                {

                    //get the jth closest coffeeshop from the current location
                    Vertex<E> nextShop=current.closestNeighbor(j);

                    //truck deliver to the nextshop vertex, if deliver succeed, set next to be true and break the loop, otherwise continue the next iteration
                    if(t.deliver(((CoffeeShop<E>)nextShop)))
                    {

                        //set next to be true
                        next=true;
                        //break the loop
                        break;
                    }

                }
                //if the next is false, which there is no coffeeshop near the current location is available for delivery(not enough capacity left for truck or no order left in the coffeeshop)
                if(next==false || checkShopOrder())
                {
                    //finish the route
                    t.finishRoute();
                    //subtract the number of trucks in the warehouse by 1
                    w.decreaseTruck();
                    //update the total distance traveled
                    totalDistance=totalDistance+t.getDistance();

                    //create a new truck start from the warehouse
                    t=new Truck<E>(w);

                }

                //check whether all the coffeeshops finished their orders, if so, return the total distance travelde
                if(checkShopOrder())
                {
                    return totalDistance;
                }

            }
        }
        //return the total distance traveled
        return totalDistance;
    }

}
