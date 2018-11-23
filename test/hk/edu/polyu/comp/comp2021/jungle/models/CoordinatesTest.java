package hk.edu.polyu.comp.comp2021.jungle.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for Coordinates
 */
public class CoordinatesTest {

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

        // Test true case boundary
        assertTrue(Coordinates.isValid("A1"));
        assertTrue(Coordinates.isValid("G9"));
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedXLowCoordinates() {
        Coordinates c = new Coordinates(-1, 0);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedXHighCoordinates() {
        Coordinates c = new Coordinates(7, 0);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedYLowCoordinates() {
        Coordinates c = new Coordinates(0, -1);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedYHighCoordinates() {
        Coordinates c = new Coordinates(0, 9);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailedStringCoordinates() {
        Coordinates c = new Coordinates("H9");
    }


    /**
     * Test case for getX
     */
    @Test
    public void string2xy() {
        Coordinates c = new Coordinates("C5");
        assertEquals(2, c.getX());
        assertEquals(4, c.getY());
    }
}