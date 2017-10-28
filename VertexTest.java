
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class VertexTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VertexTest
{
    /**
     * Default constructor for test class VertexTest
     */
    public VertexTest()
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
    public void testAddEdge()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(100, 100, 2);
        Edge<Integer> edge1 = new Edge<Integer>(vertex1, vertex2, 100);
        Vertex<Integer> vertex3 = new Vertex<Integer>(10, 10, 3);
        Edge<Integer> edge2 = new Edge<Integer>(vertex1, vertex3, 1);
        vertex1.addEdge(edge1);
        vertex1.addEdge(edge2);
        ArrayList<Edge<Integer>> test= new ArrayList<Edge<Integer>>();
        test.add(edge1);
        test.add(edge2);
        assertEquals(test, vertex1.getNeighborList());
    }

    @Test
    public void testGetNeighborList()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(100, 100, 2);
        Edge<Integer> edge1 = new Edge<Integer>(vertex1, vertex2, 100);
        vertex1.addEdge(edge1);
        ArrayList<Edge<Integer>> test= new ArrayList<Edge<Integer>>();
        test.add(edge1);
        assertEquals(test, vertex1.getNeighborList());
    }

    @Test
    public void testgetNeighbor()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(100, 100, 2);
        Edge<Integer> edge1 = new Edge<Integer>(vertex1, vertex2, 100);
        Vertex<Integer> vertex3 = new Vertex<Integer>(10, 10, 3);
        Vertex<Integer> vertex4 = new Vertex<Integer>(90, 0, 4);
        Edge<Integer> edge2 = new Edge<Integer>(vertex1, vertex3, 1);
        Edge<Integer> edge3 = new Edge<Integer>(vertex1, vertex4, 99);
        vertex1.addEdge(edge1);
        vertex1.addEdge(edge2);
        vertex1.addEdge(edge3);
        assertEquals(vertex2.key, vertex1.closestNeighbor(0).key);
        assertEquals(vertex3.key, vertex1.closestNeighbor(1).key);
        assertEquals(vertex4.key, vertex1.closestNeighbor(2).key);
    } 

    @Test
    public void testGetDistance()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(100, 100, 2);
        assertEquals(200, vertex1.getDistance(vertex2));
    }
    
     @Test
    public void testhasVisited()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        vertex1.hasVisited();
        assertEquals(true, vertex1.checkVisit());
    }
    
    @Test
    public void testnotVisited()
    {
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 0, 1);
        vertex1.notVisited();
        assertEquals(false, vertex1.checkVisit());
    }

}

