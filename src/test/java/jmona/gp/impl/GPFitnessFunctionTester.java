/**
 * GPFitnessFunctionTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import jmona.exceptions.FitnessException;
import jmona.exceptions.InitializationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPFitnessFunction class.
 * 
 * @author jfinkels
 */
public class GPFitnessFunctionTester {

  /** The function under test. */
  private GPFitnessFunction<Integer, Object> function = null;
  /** The Set of objects for use in evaluating equality of Trees. */
  private Set<Object> inputs = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPFitnessFunction<Integer, Object>();

    this.inputs = new HashSet<Object>();
    this.inputs.add(new Object());
    this.inputs.add(new Object());
    this.inputs.add(new Object());
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPFitnessFunction#fitness(jmona.gp.Tree)}.
   */
  @Test
  public void testFitness() {
    // set the necessary properties
    this.function.setEvaluationInputs(this.inputs);
    this.function.setEquivalenceTester(new EqualityTester<Integer>());
    this.function.setTarget(0);

    // create a tree with a single Node
    final Node<Integer> root = new IntegerNode(0);
    final Tree<Integer> tree0 = new DefaultTree<Integer>(root);

    try {
      double epsilon = 0.0;
      assertEquals(this.inputs.size(), this.function.fitness(tree0), epsilon);

      this.function.setTarget(1);
      assertEquals(0, this.function.fitness(tree0), epsilon);

      this.function.setTarget(2);
      assertEquals(0, this.function.fitness(tree0), epsilon);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.GPFitnessFunction#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      this.function.setEvaluationInputs(this.inputs);
    }

    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      this.function.setEquivalenceTester(new EqualityTester<Integer>());
    }

    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      this.function.setTarget(0);
    }

    this.function.setTarget(null);
    final ExampleTreeFactory factory = new ExampleTreeFactory();
    try {
      this.function.fitness(factory.createObject());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof NullPointerException);
    } catch (InitializationException exception) {
      Util.fail(exception);
    }
  }
}
