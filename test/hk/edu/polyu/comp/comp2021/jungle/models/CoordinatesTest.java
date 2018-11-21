package hk.edu.polyu.comp.comp2021.jungle.models;

import org.junit.Test;

import static org.junit.Assert.*;

// TODO: Unit tests

/**
 * Unit test for Coordinates
 */
public class CoordinatesTest {

    /**
     * Specific hash code for tile C5
     */
    private static final int HASH_CODE_C5 = 66;

    /**
     * Test case for valid coordinates
     */
    @Test
    public void testIsValid() {
        // Test string length larger than 0
        assertFalse(Coordinates.isValid("G10"));

        // Test english letter out of range
        assertFalse(Coordinates.isValid("H9"));
        assertFalse(Coordinates.isValid("@1"));

        // Test number out of range
        assertFalse(Coordinates.isValid("A0"));
        assertFalse(Coordinates.isValid("GA"));

        // Test true case boundary
        assertTrue(Coordinates.isValid("A1"));
        assertTrue(Coordinates.isValid("G9"));
    }

    /**
     * Test the success Integer Coordinates.
     */
    @Test
    public void testSuccessIntegerCoordinates() {
        Coordinates c = new Coordinates(0, 0);
        Coordinates d = new Coordinates(6, 8);
    }

    /**
     * Test if the coordinates failed on lower X boundary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedXLowCoordinates() {
        Coordinates c = new Coordinates(-1, 0);
    }

    /**
     * Test if the coordinates failed on upper X boundary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedXHighCoordinates() {
        Coordinates c = new Coordinates(7, 0);
    }

    /**
     * Test if the coordinates failed on lower Y boundary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedYLowCoordinates() {
        Coordinates c = new Coordinates(0, -1);
    }

    /**
     * Test if the coordinates failed on higher Y boundary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedYHighCoordinates() {
        Coordinates c = new Coordinates(0, 9);
    }

    /**
     * Test if the coordinates failed on String input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedStringCoordinates() {
        Coordinates c = new Coordinates("H9");
    }


    /**
     * Test case for getX and get Y
     */
    @Test
    public void string2xy() {
        Coordinates c = new Coordinates("C5");
        assertEquals(2, c.getX());
        assertEquals(4, c.getY());
    }

    /**
     * Test the hash code
     */
    @Test
    public void testHashCode() {
        Coordinates c = new Coordinates("C5");
        assertEquals(HASH_CODE_C5, c.hashCode());
    }

    /**
     * Test whether equals() works
     */
    @Test
    public void testEquals() {
        Coordinates c = new Coordinates("C5");
        Coordinates d = new Coordinates(2, 4);

        Coordinates x = new Coordinates("B2");
        Coordinates y = new Coordinates("C4");
        Coordinates z = new Coordinates("F5");
        Object o = new Object();

        assertTrue(c.equals(d));
        assertTrue(d.equals(c));

        assertFalse(c.equals(x));
        assertFalse(c.equals(y));
        assertFalse(c.equals(z));

        assertFalse(c.equals(o));

    }
}