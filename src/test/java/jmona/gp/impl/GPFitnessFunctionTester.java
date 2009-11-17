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
import jmona.FitnessException;
import jmona.Metric;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.IntegerNode;
import jmona.impl.metrics.EuclideanMetric;
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
  private GPFitnessFunction<Integer> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPFitnessFunction<Integer>();
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPFitnessFunction#fitness(jmona.gp.Tree)}.
   */
  @Test
  public void testFitness() {
    final Metric<Integer> metric = new EuclideanMetric<Integer>();
    this.function.setMetric(metric);
    this.function.setTarget(0);

    final Node<Integer> root = new IntegerNode(0);
    final Tree<Integer> tree0 = new DefaultTree<Integer>(root);

    try {
      double epsilon = 0.0;
      assertEquals(Double.POSITIVE_INFINITY, this.function.fitness(tree0),
          epsilon);

      this.function.setTarget(1);
      assertEquals(1, this.function.fitness(tree0), epsilon);

      this.function.setTarget(2);
      assertEquals(1.0 / 2.0, this.function.fitness(tree0), epsilon);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.GPFitnessFunction#setMetric(Metric)}.
   */
  @Test
  public void testSetMetric() {
    final Metric<Integer> metric = new EuclideanMetric<Integer>();
    this.function.setMetric(metric);
    this.function.setTarget(0);

    final Node<Integer> root = new IntegerNode(0);
    final Tree<Integer> tree0 = new DefaultTree<Integer>(root);

    try {
      double epsilon = 0.0;
      assertEquals(Double.POSITIVE_INFINITY, this.function.fitness(tree0),
          epsilon);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }
}
