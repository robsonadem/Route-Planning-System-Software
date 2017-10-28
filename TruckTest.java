

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class TruckTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TruckTest
{
    /**
     * Default constructor for test class TruckTest
     */
    public TruckTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetters()
    {
        
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 2);
        Truck<Integer> truck1 = new Truck<Integer>(warehous1);
        assertEquals(500, truck1.getCapacity());
        assertEquals(0, truck1.getDistance());
        assertEquals(false, truck1.isEmpty());
        assertEquals(warehous1, truck1.getCurrentLocation());
    }
    
    @Test
    public void testDeliver()
    {
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        CoffeeShop<Integer> c= new CoffeeShop<Integer>(10,10,1,OrderList);
        
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 2);
        Truck<Integer> truck1 = new Truck<Integer>(warehous1);
        assertEquals(true, truck1.deliver(c));
        assertEquals(400, truck1.getCapacity());
        assertEquals(20, truck1.getDistance());
        assertEquals(c, truck1.getCurrentLocation());
    }
    
    @Test
    public void testFinishRoute()
    {
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        CoffeeShop<Integer> c= new CoffeeShop<Integer>(10,10,1,OrderList);
        
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 2);
        Truck<Integer> truck1 = new Truck<Integer>(warehous1);
        assertEquals(true, truck1.deliver(c));
        assertEquals(400, truck1.getCapacity());
        truck1.finishRoute();
        assertEquals(40, truck1.getDistance());
        assertEquals(warehous1, truck1.getCurrentLocation());
    }
}

