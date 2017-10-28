

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class GraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphTest
{
    /**
     * Default constructor for test class GraphTest
     */
    public GraphTest()
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
    public void testAddCoffeeShop()
    {
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        Graph<Integer> graph1 = new Graph<Integer>();
        assertEquals(true, graph1.addCoffeeShop(0, 0, 1, OrderList));
        assertEquals(false, graph1.addCoffeeShop(0, 0, 1,OrderList));
    }

    @Test
    public void testAddWarehouse()
    {
        Graph<Integer> graph1 = new Graph<Integer>();
        assertEquals(true, graph1.addWarehouse(0, 0, 1, 2));
        assertEquals(false, graph1.addWarehouse(0, 0, 1, 2));
    }

    @Test
    public void testAddEdge()
    {
        Graph<Integer> graph1 = new Graph<Integer>();
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        assertEquals(true, graph1.addCoffeeShop(0, 0, 1, OrderList));
        assertEquals(false, graph1.addCoffeeShop(0, 0, 1,OrderList));
        assertEquals(true, graph1.addWarehouse(2, 2, 2, 2));
        graph1.addWarehouse(0, 0, 1, 2);
    }
}



