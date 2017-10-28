

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EdgeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EdgeTest
{
    /**
     * Default constructor for test class EdgeTest
     */
    public EdgeTest()
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
        Vertex<Integer> vertex1 = new Vertex<Integer>(0, 100, 1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(100, 0, 2);
        Edge<Integer> edge1 = new Edge<Integer>(vertex1, vertex2, 100);
    }
}

