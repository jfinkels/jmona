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

import jfcommon.test.TestUtils;
import jmona.DeepCopyableList;
import jmona.EvolutionException;
import jmona.Factory;
import jmona.FitnessException;
import jmona.GeneticEvolutionContext;
import jmona.InitializationException;
import jmona.example.ones.OnesFitnessFunction;
import jmona.ga.impl.BitFactory;
import jmona.ga.impl.BitFlipMutationFunction;
import jmona.ga.impl.GAEvolutionContext;
import jmona.ga.impl.TwoPointCrossoverFunction;
import jmona.impl.DeepCopyableListFactory;
import jmona.impl.ListFactory;
import jmona.impl.completion.MaxGenerationCompletionCondition;
import jmona.impl.mutable.MutableByte;
import jmona.impl.mutation.SingleElementwiseMutationFunction;
import jmona.impl.selection.FitnessProportionateSelection;

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
    Factory<MutableByte> bitFactory = new BitFactory();
    int length = 20;

    DeepCopyableListFactory<MutableByte> individualFactory = new DeepCopyableListFactory<MutableByte>(
        length);
    individualFactory.setElementFactory(bitFactory);

    int numberOfIndividuals = 100;

    ListFactory<DeepCopyableList<MutableByte>> populationFactory = new ListFactory<DeepCopyableList<MutableByte>>(
        numberOfIndividuals);
    populationFactory.setElementFactory(individualFactory);

    List<DeepCopyableList<MutableByte>> population = null;
    try {
      population = populationFactory.createObject();
    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }

    GeneticEvolutionContext<DeepCopyableList<MutableByte>> context = new GAEvolutionContext<DeepCopyableList<MutableByte>>(
        population);

    context.setCrossoverFunction(new TwoPointCrossoverFunction<MutableByte>());
    try {
      context.setFitnessFunction(new OnesFitnessFunction(length));
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
    final SingleElementwiseMutationFunction<MutableByte, DeepCopyableList<MutableByte>> mutationFunction = new SingleElementwiseMutationFunction<MutableByte, DeepCopyableList<MutableByte>>();
    mutationFunction.setElementMutationFunction(new BitFlipMutationFunction());
    context.setMutationFunction(mutationFunction);
    context
        .setSelectionFunction(new FitnessProportionateSelection<DeepCopyableList<MutableByte>>());

    int maxGenerations = 20;

    MaxGenerationCompletionCondition<DeepCopyableList<MutableByte>> condition = new MaxGenerationCompletionCondition<DeepCopyableList<MutableByte>>();
    condition.setMaxGenerations(maxGenerations);

    try {
      while (!condition.execute(context)) {
        context.stepGeneration();
        LOG.debug("Generation " + context.currentGeneration() + ": "
            + context.currentPopulation());
      }
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }
}
