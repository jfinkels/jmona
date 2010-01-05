/**
 * FitnessFunctionCollaboratorTester.java
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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the FitnessFunctionCollaborator class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class FitnessFunctionCollaboratorTester {

  /**
   * Test method for
   * {@link jmona.impl.fitness.FitnessFunctionCollaborator#fitnessFunction()}.
   */
  @Test
  public void testFitnessFunction() {
    final FitnessFunctionCollaborator<ExampleIndividual> object = new FitnessFunctionCollaborator<ExampleIndividual>();
    assertNull(object.fitnessFunction());
    final ExampleFitnessFunction function = new ExampleFitnessFunction();
    object.setFitnessFunction(function);
    assertSame(function, object.fitnessFunction());
  }

}
