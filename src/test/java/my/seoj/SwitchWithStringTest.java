package my.seoj;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwitchWithStringTest
{
    private SwitchWithString switchWithString;

    @Before
    public void before()
    {
        switchWithString = new SwitchWithString();
    }

    @Test
    public void testLorem()
    {
        assertEquals(1, switchWithString.run("LOREM"));
    }

    @Test
    public void testIpsum()
    {
        assertEquals(2, switchWithString.run("IPSUM"));
    }

    @Test
    public void testDolorem()
    {
        assertEquals(3, switchWithString.run("DOLOREM"));
    }

    @Test
    public void testDefault()
    {
        assertEquals(4, switchWithString.run("DEFAULT"));
    }

    @Test
    public void testNull()
    {
        try
        {
            switchWithString.run(null);
            fail();
        }
        catch (Exception e)
        {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
}
