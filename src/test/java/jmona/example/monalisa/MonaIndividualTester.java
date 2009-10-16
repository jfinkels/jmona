/**
 * MonaIndividualTester.java
 */
package jmona.example.monalisa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;

import org.junit.Test;

/**
 * Test class for the MonaIndividual class.
 * 
 * @author jeff
 */
public class MonaIndividualTester {

  /**
   * Test method for {@link jmona.example.monalisa.MonaIndividual#copy()}.
   */
  @Test
  public void testCopy() {
    final MonaIndividual original = new MonaIndividual();
    
    final Polygon polygon = new Polygon();
    final Color color = new Color(0); 
    
    original.gene().put(polygon, color);

    final MonaIndividual clone = original.copy();

    assertNotSame(original, clone);
    assertNotSame(original.gene(), clone.gene());
    
    assertTrue(original.gene().containsKey(polygon));
    assertTrue(original.gene().containsValue(color));
    
    assertEquals(original.gene().size(), clone.gene().size());
    
    assertFalse(clone.gene().containsKey(polygon));
    assertTrue(clone.gene().containsValue(color));
    
  }

}
