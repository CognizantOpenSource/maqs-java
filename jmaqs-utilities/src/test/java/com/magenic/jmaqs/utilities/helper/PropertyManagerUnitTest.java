package com.magenic.jmaqs.utilities.helper;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class PropertyManagerUnitTest {
    /**
     * An invalid key for the property manager to invoke failure
     */
    private String INVALID_KEY = "does-not-exist";

    /**
     * The default value provided to the property manager
     */
    private String DEFAULT_VALUE = "default";

    @Test
    public void testPropertyNotSetReturnsNull() {
        assertNull(
                PropertyManager.get(INVALID_KEY),
                String.format("key %s was found when it wasn't suppose to be.", INVALID_KEY));
    }

    @Test
    public void testPropertyNotSetReturnsDefault() {
        assertEquals(PropertyManager.get(INVALID_KEY, DEFAULT_VALUE), DEFAULT_VALUE);
    }
}
