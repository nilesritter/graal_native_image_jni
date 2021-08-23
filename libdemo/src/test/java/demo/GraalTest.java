package demo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Unit test for GraalDemo
 */
public class GraalTest 
{

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testAdd()
    {
        long result = NativeImpl.add_internal(3,4);
	assertEquals(7,3 + 4);
    }

    @Test
    public void testEvaluate()
    {
        String result = NativeImpl.evaluate_internal("Bob");
	assertEquals("Foo-Bob",result);
    }

}
