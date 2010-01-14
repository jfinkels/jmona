/**
 * OnesEvolutionTester.java
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
package jmona.example.ones.code;

import java.util.List;

import jmona.DeepCopyableList;
import jmona.EvolutionException;
import jmona.Factory;
import jmona.GeneticEvolutionContext;
import jmona.InitializationException;
import jmona.example.ones.OnesFitnessFunction;
import jmona.example.ones.OnesMutationFunction;
import jmona.ga.impl.BitFactory;
import jmona.ga.impl.GAEvolutionContext;
import jmona.ga.impl.TwoPointCrossoverFunction;
import jmona.impl.DefaultListFactory;
import jmona.impl.PartialDeepCopyableListFactory;
import jmona.impl.completion.MaxGenerationCompletionCondition;
import jmona.impl.selection.FitnessProportionateSelection;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Test for the "most ones" evolution, using code.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class OnesEvolutionTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(OnesEvolutionTester.class);

  /** Test for the "most ones" evolution, using code. */
  @Test
  public void testOnesEvolution() {
    Factory<Byte> bitFactory = new BitFactory();
    int length = 20;

    PartialDeepCopyableListFactory<Byte> individualFactory = new PartialDeepCopyableListFactory<Byte>();
    individualFactory.setElementFactory(bitFactory);
    individualFactory.setSize(length);

    int numberOfIndividuals = 100;

    DefaultListFactory<DeepCopyableList<Byte>> populationFactory = new DefaultListFactory<DeepCopyableList<Byte>>();
    populationFactory.setSize(numberOfIndividuals);
    populationFactory.setElementFactory(individualFactory);

    List<DeepCopyableList<Byte>> population = null;
    try {
      population = populationFactory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    GeneticEvolutionContext<DeepCopyableList<Byte>> context = new GAEvolutionContext<DeepCopyableList<Byte>>(
        population);

    context.setCrossoverFunction(new TwoPointCrossoverFunction<Byte>());
    context.setFitnessFunction(new OnesFitnessFunction(length));
    context.setMutationFunction(new OnesMutationFunction());
    context
        .setSelectionFunction(new FitnessProportionateSelection<DeepCopyableList<Byte>>());

    int maxGenerations = 20;

    MaxGenerationCompletionCondition<DeepCopyableList<Byte>> condition = new MaxGenerationCompletionCondition<DeepCopyableList<Byte>>();
    condition.setMaxGenerations(maxGenerations);

    try {
      while (!condition.execute(context)) {
        context.stepGeneration();
        LOG.debug("Generation " + context.currentGeneration() + ": "
            + context.currentPopulation());
      }
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }
  }
}
