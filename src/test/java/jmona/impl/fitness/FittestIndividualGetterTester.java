/**
 * FittestIndividualGetterTester.java
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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.MappingException;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the FittestIndividualGetter class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class FittestIndividualGetterTester {

  /**
   * Test method for
   * {@link jmona.impl.fitness.FittestIndividualGetter#execute(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testExecute() {
    final FittestIndividualGetter<ExampleIndividual> getter = new FittestIndividualGetter<ExampleIndividual>();
    getter.setFitnessFunction(new ExampleFitnessFunction());

    final ExampleIndividual individual1 = new ExampleIndividual(1);
    final ExampleIndividual individual2 = new ExampleIndividual(2);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    final EvolutionContext<ExampleIndividual> context = new ExampleEvolutionContext(
        population);

    ExampleIndividual fittestIndividual = null;
    try {
      fittestIndividual = getter.execute(context);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    assertSame(individual1, fittestIndividual);

    individual1.setFitness(individual2.fitness() + 1);

    try {
      fittestIndividual = getter.execute(context);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    assertSame(individual2, fittestIndividual);
  }

}
