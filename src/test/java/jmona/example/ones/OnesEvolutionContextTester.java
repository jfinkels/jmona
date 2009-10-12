package jmona.example.ones;

import jmona.CompletionCriteria;
import jmona.EvolutionContext;
import jmona.EvolutionException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * OnesEvolutionContextTester.java
 */

/**
 * Test class for an evolution of the Ones example.
 * 
 * @author jeff
 */
@ContextConfiguration
public class OnesEvolutionContextTester extends
    AbstractJUnit4SpringContextTests {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(OnesEvolutionContextTester.class);
  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  protected static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
  }
  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCriteria<OnesIndividual> completionCriteria = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<OnesIndividual> context = null;

  /** Test method for a Ones evolution. */
  @Test
  public final void testOnesEvolution() {
    try {
      while (!this.completionCriteria.isSatisfied(this.context)) {
        this.context.stepGeneration();
        LOG.debug(this.context.population());
      }
    } catch (final EvolutionException exception) {
      fail(exception);
    }
  }
}
