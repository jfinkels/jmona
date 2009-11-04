/**
 * OnesMutationFunction.java
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
package jmona.example.ones;

import jmona.MutationException;
import jmona.MutationFunction;
import jmona.impl.Util;

/**
 * A class which mutates genes by flipping bits with a certain probability.
 * 
 * @author jfinke
 */
public class OnesMutationFunction implements MutationFunction<OnesIndividual> {
  /** The probability of a bitwise mutation. */
  public static final double PROB_BITWISE_MUTATION = 0.05;

  /**
   * Perform a bitwise mutation on bits in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose gene will be mutated.
   * @throws MutationException
   *           {@inheritDoc}
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final OnesIndividual individual) throws MutationException {
    // get the gene
    final short[] gene = individual.gene();

    // iterate over each bit in the gene
    for (int i = 0; i < gene.length; ++i) {
      if (Util.RANDOM.nextDouble() <= PROB_BITWISE_MUTATION) {
        gene[i] = (short) Math.abs(gene[i] - 1);
      }
    }

    // set the gene on the individual
    individual.setGene(gene);
  }
}
