/**
 * GPEvolutionContextTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
 * 
 * This file is part of jmona.
 * 
 * jmona is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * jmona is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jmona. If not, see <http://www.gnu.org/licenses/>.
 */
package jmona.gp.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.InitializationException;
import jmona.Population;
import jmona.gp.Tree;
import jmona.gp.TreeFactory;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.impl.DefaultPopulation;
import jmona.impl.metrics.EuclideanMetric;
import jmona.impl.selection.FitnessProportionateSelection;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPEvolutionContext class.
 * 
 * @author jfinkels
 */
public class GPEvolutionContextTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(GPEvolutionContextTester.class);
  /** The EvolutionContext under test. */
  private GPEvolutionContext<Integer> context = null;
  /** The initial population for the EvolutionContext under test. */
  private Population<Tree<Integer>> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    final TreeFactory<Integer> factory = new ExampleTreeFactory();
    this.population = new DefaultPopulation<Tree<Integer>>();
    try {
      this.population.add(factory.createIndividual());
      this.population.add(factory.createIndividual());
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
    this.context = new GPEvolutionContext<Integer>(this.population);
    this.context.setCrossoverFunction(new GPCrossoverFunction<Integer>());
    this.context.setMutationFunction(new GPMutationFunction<Integer>());
    this.context
        .setSelectionFunction(new FitnessProportionateSelection<Tree<Integer>>());

    final GPFitnessFunction<Integer> fitnessFunction = new GPFitnessFunction<Integer>();
    fitnessFunction.setMetric(new EuclideanMetric<Integer>());
    fitnessFunction.setTarget(0);

    try {
      this.context.setFitnessFunction(fitnessFunction);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPEvolutionContext#GPEvolutionContext(jmona.Population)}
   * .
   */
  @Test
  public void testGPEvolutionContext() {
    try {
      new GPEvolutionContext<Integer>(new DefaultPopulation<Tree<Integer>>());
      fail("Exception should have been thrown on the previous line.");
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.GPEvolutionContext#stepGeneration()}.
   */
  // TODO make assertions that some individuals have changed
  @Test
  public void testStepGeneration() {

    final Population<Tree<Integer>> before = this.context.currentPopulation();
    LOG.debug(before);

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    final Population<Tree<Integer>> after = this.context.currentPopulation();
    LOG.debug(after);

  }

}
