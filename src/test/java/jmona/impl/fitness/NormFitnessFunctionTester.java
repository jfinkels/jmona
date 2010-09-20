/**
 * NormFitnessFunctionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.impl.fitness;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the NormFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class NormFitnessFunctionTester {
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /** Test for {@link jmon.impl.fitness.NormFitnessFunction#rawFitness(Number)}. */
  @Test
  public void testRawFitness() {
    assertEquals(10.0,
        NormFitnessFunction.INTEGER_FITNESS_FUNCTION
            .rawFitness(new Integer(10)), ZERO_DELTA);

    assertEquals(10.0,
        NormFitnessFunction.LONG_FITNESS_FUNCTION.rawFitness(new Long(10L)),
        ZERO_DELTA);

    assertEquals(10.0,
        NormFitnessFunction.DOUBLE_FITNESS_FUNCTION
            .rawFitness(new Double(10.0)), ZERO_DELTA);

    assertEquals(10.0,
        NormFitnessFunction.FLOAT_FITNESS_FUNCTION.rawFitness(new Float(10f)),
        ZERO_DELTA);

  }
}
