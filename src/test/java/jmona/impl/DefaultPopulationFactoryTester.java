/**
 * DefaultPopulationFactoryTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.Individual;
import jmona.IndividualFactory;
import jmona.InitializationException;
import jmona.Population;
import jmona.PopulationFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultPopulationFactory class.
 * 
 * @author jeff
 */
public class DefaultPopulationFactoryTester {

  /** The population factory under test in this class. */
  private PopulationFactory<Individual> populationFactory = null;
  /** The individual factory required by the population factory. */
  private IndividualFactory<Individual> individualFactory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.populationFactory = new DefaultPopulationFactory<Individual>();
    this.individualFactory = new IndividualFactory<Individual>() {
      /**
       * {@inheritDoc}
       * 
       * @return {@inheritDoc}
       * @throws InitializationException
       *           {@inheritDoc}
       */
      @Override
      public Individual createIndividual() throws InitializationException {
        return new Individual() {
          /**
           * {@inheritDoc}
           * 
           * @param <T>
           *          {@inheritDoc}
           * @return {@inheritDoc}
           */
          @Override
          public <T extends Individual> T copy() {
            return null;
          }
        };
      }
    };

    this.populationFactory.setIndividualFactory(this.individualFactory);
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultPopulationFactory#createPopulation()} with no
   * IndividualFactory set (an Exception is expected to be thrown).
   */
  @Test
  public void testException() {
    this.populationFactory.setIndividualFactory(null);
    try {
      this.populationFactory.createPopulation();
      org.junit.Assert
          .fail("Exception was not thrown when it should have been.");
    } catch (final InitializationException exception) {
      assertTrue(exception != null);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultPopulationFactory#createPopulation()}.
   */
  @Test
  public void testCreatePopulation() {
    Population<Individual> population = null;
    try {
      population = this.populationFactory.createPopulation();
    } catch (final InitializationException exception) {
      fail(exception);
    }
    
    assertEquals(AbstractPopulationFactory.DEFAULT_SIZE, population.size());
    
    for (final Individual individual : population) {
      assertNotSame(individual, null);
      assertTrue(individual instanceof Individual);
    }
  }

  /**
   * Test method for {@link DefaultPopulationFactory#size()} and
   * {@link DefaultPopulationFactory#setSize(int)}.
   */
  @Test
  public final void testSize() {
    assertSame(AbstractPopulationFactory.DEFAULT_SIZE, this.populationFactory
        .size());
    final int newSize = 10;
    this.populationFactory.setSize(newSize);
    assertSame(newSize, this.populationFactory.size());
  }

  /**
   * Helper method for printing the stack trace of an Exception and failing the
   * test.
   * 
   * @param cause
   *          The cause of the test failure.
   */
  protected static void fail(final Throwable cause) {
    cause.printStackTrace(System.err);
    org.junit.Assert.fail(cause.getMessage());
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractPopulationFactory#individualFactory()}.
   */
  @Test
  public void testIndividualFactory() {
    assertSame(this.individualFactory, this.populationFactory
        .individualFactory());
    this.populationFactory.setIndividualFactory(null);
    assertNotSame(this.individualFactory, this.populationFactory
        .individualFactory());
    assertEquals(null, this.populationFactory.individualFactory());
  }
}
