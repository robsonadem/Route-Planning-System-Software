

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class CoffeeShopTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CoffeeShopTest
{
   

    @Test
    public void testGetOrders()
    {
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        CoffeeShop<Integer> c= new CoffeeShop<Integer>(0,0,1,OrderList);
        assertEquals(OrderList,c.getOrders());
    }
    
    @Test
    public void testOrderFinish()
    {
        Queue<Integer> OrderList= new LinkedList<Integer>();
        OrderList.add(100);
        OrderList.add(10);
        CoffeeShop<Integer> c= new CoffeeShop<Integer>(0,0,1,OrderList);
        assertEquals(true,!c.orderFinish());
        OrderList.remove();
        OrderList.remove();
        CoffeeShop<Integer> c1= new CoffeeShop<Integer>(0,0,1,OrderList);
        assertEquals(true,c.orderFinish());
    }
    
}

