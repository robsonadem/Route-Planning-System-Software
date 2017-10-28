

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WarehouseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WarehouseTest
{
   

    @Test
    public void testGetNumOfTrucks()
    {
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 5);
        assertEquals(5, warehous1.getNumOfTruck());
        warehous1.decreaseTruck();
        assertEquals(4, warehous1.getNumOfTruck());
        assertEquals(true, !warehous1.noTruck());
    }
    
    @Test
    public void testDecreaseTrucks()
    {
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 5);
        assertEquals(5, warehous1.getNumOfTruck());
        warehous1.decreaseTruck();
        assertEquals(4, warehous1.getNumOfTruck());
        
    }
    
    @Test
    public void testNoTruck()
    {
        Warehouse<Integer> warehous1 = new Warehouse<Integer>(0, 0, 1, 5);
        assertEquals(false, warehous1.noTruck());
        Warehouse<Integer> warehous2 = new Warehouse<Integer>(0, 0, 1, 0);
        assertEquals(true, warehous2.noTruck());
    }

    @Test
    public void testDeliver()
    {
        Warehouse<Integer> warehous2 = new Warehouse<Integer>(0, 0, 1, 1);
        Truck<Integer> truck1 = new Truck<Integer>(warehous2);
    }
}


