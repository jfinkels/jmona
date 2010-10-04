/**
 * ElementwiseMutationFunctionTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.impl.mutation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import jmona.MutationFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;

import org.junit.Test;

/**
 * Test class for the ElementwiseMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ElementwiseMutationFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.mutation.ElementwiseMutationFunction#setElementMutationFunction(jmona.MutationFunction)}
   * .
   */
  @Test
  public void testSetElementMutationFunction() {
    final ElementwiseMutationFunction<ExampleIndividual, List<ExampleIndividual>> function = new ElementwiseMutationFunction<ExampleIndividual, List<ExampleIndividual>>() {
      @Override
      public void mutate(final List<ExampleIndividual> object) {
        // intentionally unimplemented
      }
    };
    function.setElementMutationFunction(new ExampleMutationFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.ElementwiseMutationFunction#elementMutationFunction()}
   * .
   */
  @Test
  public void testElementMutationFunction() {
    final ElementwiseMutationFunction<ExampleIndividual, List<ExampleIndividual>> function = new ElementwiseMutationFunction<ExampleIndividual, List<ExampleIndividual>>() {
      @Override
      public void mutate(final List<ExampleIndividual> object) {
        // intentionally unimplemented
      }
    };
    assertNull(function.elementMutationFunction());
    final MutationFunction<ExampleIndividual> elementMutationFunction = new ExampleMutationFunction();
    function.setElementMutationFunction(elementMutationFunction);
    assertSame(elementMutationFunction, function.elementMutationFunction());
  }

}
