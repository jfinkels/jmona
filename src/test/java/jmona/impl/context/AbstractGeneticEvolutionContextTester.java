/**
 * AbstractEvolutionContextTester.java
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
package jmona.impl.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.CrossoverFunction;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.MutationFunction;
import jmona.SelectionFunction;
import jmona.impl.context.AbstractGeneticEvolutionContext;
import jmona.impl.example.ExampleCrossoverFunction;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;
import jmona.impl.selection.FitnessProportionateSelection;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 */
public class AbstractGeneticEvolutionContextTester {

  /** The amount by which to change the fitnesses of individuals. */
  public static final double INCREMENT = 0.1;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The context under test in this class. */
  private AbstractGeneticEvolutionContext<ExampleIndividual> context = null;
  /** A crossover function. */
  private CrossoverFunction<ExampleIndividual> crossoverFunction = null;
  /** An empty Population. */
  private List<ExampleIndividual> emptyPopulation = null;
  /** A fitness function. */
  private FitnessFunction<ExampleIndividual> fitnessFunction = null;
  /** An Individual to be placed into a Population. */
  private ExampleIndividual individual1 = null;
  /** An Individual to be placed into a Population. */
  private ExampleIndividual individual2 = null;
  /** A mutation function. */
  private MutationFunction<ExampleIndividual> mutationFunction = null;
  /** A Population. */
  private List<ExampleIndividual> population = null;
  /** A selection function. */
  private IndependentSelectionFunction<ExampleIndividual> selectionFunction = null;
  /** An AbstractEvolutionContext which has no functions set. */
  private AbstractGeneticEvolutionContext<ExampleIndividual> unsetContext = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.emptyPopulation = new Vector<ExampleIndividual>();
    this.population = new Vector<ExampleIndividual>();

    this.individual1 = new ExampleIndividual(0);
    this.individual2 = new ExampleIndividual(1);

    this.population.add(this.individual1);
    this.population.add(this.individual2);

    this.crossoverFunction = new ExampleCrossoverFunction();
    this.selectionFunction = new FitnessProportionateSelection<ExampleIndividual>();
    this.mutationFunction = new ExampleMutationFunction();
    this.fitnessFunction = new ExampleFitnessFunction();

    this.unsetContext = new ExampleEvolutionContext(this.population);
    this.context = new ExampleEvolutionContext(this.population);

    this.context.setCrossoverFunction(this.crossoverFunction);
    this.context.setMutationFunction(this.mutationFunction);
    this.context.setSelectionFunction(this.selectionFunction);
    this.context.setFitnessFunction(this.fitnessFunction);

  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#AbstractEvolutionContext(List)}
   * .
   */
  @Test
  public void testAbstractEvolutionContext() {
    try {
      this.context = new ExampleEvolutionContext(this.emptyPopulation);
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }

    this.emptyPopulation.add(this.individual1);
    try {
      this.context = new ExampleEvolutionContext(this.emptyPopulation);
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }

    this.emptyPopulation.add(this.individual2);
    try {
      this.context = new ExampleEvolutionContext(this.emptyPopulation);
    } catch (final IllegalArgumentException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#crossoverFunction()}.
   */
  @Test
  public void testCrossoverFunction() {
    assertNull(this.unsetContext.crossoverFunction());
    assertSame(this.crossoverFunction, this.context.crossoverFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#crossoverProbability()}.
   */
  @Test
  public void testCrossoverProbability() {
    assertEquals(AbstractGeneticEvolutionContext.DEFAULT_CROSSOVER_PROBABILITY,
        this.context.crossoverProbability(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#currentGeneration()}.
   */
  @Test
  public void testCurrentGeneration() {
    assertEquals(0, this.context.currentGeneration());
    assertEquals(0, this.unsetContext.currentGeneration());

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    assertEquals(1, this.context.currentGeneration());

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    assertEquals(2, this.context.currentGeneration());

  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#currentPopulation()}.
   */
  @Test
  public void testCurrentPopulation() {
    assertSame(this.population, this.context.currentPopulation());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#fitnessFunction()}.
   */
  @Test
  public void testFitnessFunction() {
    assertSame(this.fitnessFunction, this.context.fitnessFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#mutationFunction()}.
   */
  @Test
  public void testMutationFunction() {
    assertNull(this.unsetContext.mutationFunction());
    assertSame(this.mutationFunction, this.context.mutationFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#mutationProbability()}.
   */
  @Test
  public void testMutationProbability() {
    assertEquals(AbstractGeneticEvolutionContext.DEFAULT_MUTATION_PROBABILITY,
        this.context.mutationProbability(), ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.impl.context.AbstractEvolutionContext#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    try {
      this.unsetContext.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      // mutation function has not been set
      assertNotNull(this.unsetContext.fitnessFunction());
      assertNull(this.unsetContext.mutationFunction());
      assertNull(this.unsetContext.selectionFunction());
      assertNull(this.unsetContext.crossoverFunction());
      this.unsetContext.setMutationFunction(new ExampleMutationFunction());
    }

    try {
      this.unsetContext.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      // selection function has not been set
      assertNotNull(this.unsetContext.fitnessFunction());
      assertNotNull(this.unsetContext.mutationFunction());
      assertNull(this.unsetContext.selectionFunction());
      assertNull(this.unsetContext.crossoverFunction());
      this.unsetContext
          .setSelectionFunction(new FitnessProportionateSelection<ExampleIndividual>());
    }

    try {
      this.unsetContext.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      // crossover function has not been set
      assertNotNull(this.unsetContext.fitnessFunction());
      assertNotNull(this.unsetContext.mutationFunction());
      assertNotNull(this.unsetContext.selectionFunction());
      assertNull(this.unsetContext.crossoverFunction());
      this.unsetContext.setCrossoverFunction(new ExampleCrossoverFunction());
    }

    try {
      // all functions have been set
      assertNotNull(this.unsetContext.fitnessFunction());
      assertNotNull(this.unsetContext.mutationFunction());
      assertNotNull(this.unsetContext.selectionFunction());
      assertNotNull(this.unsetContext.crossoverFunction());
      this.unsetContext.sanityCheck();
    } catch (final NullPointerException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#selectionFunction()}.
   */
  @Test
  public void testSelectionFunction() {
    assertNull(this.unsetContext.selectionFunction());
    assertSame(this.selectionFunction, this.context.selectionFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setCrossoverFunction(jmona.CrossoverFunction)}
   * .
   */
  @Test
  public void testSetCrossoverFunction() {
    final CrossoverFunction<ExampleIndividual> newFunction = new ExampleCrossoverFunction();

    this.context.setCrossoverFunction(newFunction);

    assertSame(newFunction, this.context.crossoverFunction());
    assertNotSame(this.crossoverFunction, this.context.crossoverFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setCrossoverProbability(double)}
   * .
   */
  @Test
  public void testSetCrossoverProbability() {
    final double newProbability = this.context.crossoverProbability() / 2.0;
    this.context.setCrossoverProbability(newProbability);
    assertEquals(newProbability, this.context.crossoverProbability(),
        ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setCurrentPopulation(List)} .
   */
  @Test
  public void testSetCurrentPopulation() {
    final List<ExampleIndividual> newPopulation = new Vector<ExampleIndividual>();
    this.context.setCurrentPopulation(newPopulation);
    assertSame(newPopulation, this.context.currentPopulation());
    assertNotSame(this.population, this.context.currentPopulation());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setFitnessFunction(jmona.FitnessFunction)}
   * .
   */
  @Test
  public void testSetFitnessFunction() {
    final FitnessFunction<ExampleIndividual> newFunction = new ExampleFitnessFunction();

    this.context.setFitnessFunction(newFunction);

    assertSame(newFunction, this.context.fitnessFunction());
    assertNotSame(this.fitnessFunction, this.context.fitnessFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setMutationFunction(MutationFunction)}
   * .
   */
  @Test
  public void testSetMutationFunction() {
    final MutationFunction<ExampleIndividual> newFunction = new ExampleMutationFunction();

    this.context.setMutationFunction(newFunction);

    assertSame(newFunction, this.context.mutationFunction());
    assertNotSame(this.mutationFunction, this.context.mutationFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setMutationProbability(double)}.
   */
  @Test
  public void testSetMutationProbability() {
    final double newProbability = this.context.mutationProbability() / 2.0;
    this.context.setMutationProbability(newProbability);
    assertEquals(newProbability, this.context.mutationProbability(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setSelectionFunction(jmona.SelectionFunction)}
   * .
   */
  @Test
  public void testSetSelectionFunction() {
    final IndependentSelectionFunction<ExampleIndividual> newFunction = new FitnessProportionateSelection<ExampleIndividual>();

    this.context.setSelectionFunction(newFunction);

    assertSame(newFunction, this.context.selectionFunction());
    assertNotSame(this.selectionFunction, this.context.selectionFunction());
  }

}