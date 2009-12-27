/**
 * PartiallyMatchedCrossoverFunctionTester.java
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
package jmona.example.ga.tsp.crossover;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for the PartiallyMatchedCrossoverFunction class.
 * 
 * @author jfinkels
 */
public class PartiallyMatchedCrossoverFunctionTester extends
    AbstractTSPCrossoverFunctionTester {

  /** Instantiate this test class with a PartiallyMatchedCrossoverFunction. */
  public PartiallyMatchedCrossoverFunctionTester() {
    super(new PartiallyMatchedCrossoverFunction());
  }

  /**
   * Test method for
   * {@link jmona.example.ga.tsp.crossover.PartiallyMatchedCrossoverFunction#crossover(jmona.example.ga.tsp.Tour, jmona.example.ga.tsp.Tour)}
   * .
   */
  @Test
  public void testCrossover() {
    fail("Not yet implemented");
  }

}
