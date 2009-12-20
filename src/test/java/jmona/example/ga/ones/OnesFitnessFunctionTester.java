/**
 * OnesFitnessFunctionTester.java
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
package jmona.example.ga.ones;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the OnesFitnessFunction class.
 * 
 * @author jfinkels
 */
public class OnesFitnessFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.ga.ones.OnesFitnessFunction#fitness(jmona.example.ga.ones.OnesIndividual)}
   * .
   */
  @Test
  public void testFitness() {
    final OnesFitnessFunction function = new OnesFitnessFunction();
    OnesIndividual individual = new OnesIndividual(new short[] { 0, 1, 0, 1 });

    final double epsilon = 0;
    assertEquals(2, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] { 1, 0, 1, 0 });
    assertEquals(2, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] { 1, 1, 1, 1 });
    assertEquals(4, function.fitness(individual), epsilon);

    individual = new OnesIndividual(new short[] {});
    assertEquals(0, function.fitness(individual), epsilon);
  }

}
