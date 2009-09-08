/**
 * OnesIndividualTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Test class for the OnesIndividual class.
 * 
 * @author jeff
 */
public class OnesIndividualTester {

  /**
   * Test method for {@link jmona.example.ones.OnesIndividual#copy()}.
   */
  @Test
  public void testCopy() {
    final short[] array = new short[]{1, 2, 3};
    final OnesIndividual individual = new OnesIndividual(array);
    final OnesIndividual clone = individual.copy();
    assertNotSame(clone, individual);
    assertNotSame(array, clone.gene());
    assertEquals(array[0], clone.gene()[0]);
    assertEquals(array[1], clone.gene()[1]);
    assertEquals(array[2], clone.gene()[2]);
  }

}
