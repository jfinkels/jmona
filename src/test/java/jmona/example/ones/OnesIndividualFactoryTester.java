/**
 * OnesIndividualFactoryTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jmona.InitializationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the OnesIndividualFactory class.
 * 
 * @author jeff
 */
public class OnesIndividualFactoryTester {

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new OnesIndividualFactory();
  }

  /** The factory under test. */
  private OnesIndividualFactory factory = null;

  /**
   * Test method for
   * {@link jmona.example.ones.OnesIndividualFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    try {
      this.factory.createIndividual();
    } catch (final InitializationException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }
  }

  @Test
  public void testSetLength() {
    OnesIndividual individual = null;
    try {
      individual = this.factory.createIndividual();
    } catch (final InitializationException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

    assertEquals(OnesIndividualFactory.DEFAULT_LENGTH, individual.gene().length);

    final int newLength = 100;
    this.factory.setLength(newLength);
    try {
      individual = this.factory.createIndividual();
    } catch (final InitializationException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

    assertEquals(newLength, individual.gene().length);

  }

}
