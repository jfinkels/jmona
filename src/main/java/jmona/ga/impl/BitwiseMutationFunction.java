/**
 * BitwiseMutationFunction.java
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
package jmona.ga.impl;

import jmona.MutationFunction;
import jmona.ga.BinaryString;

/**
 * A class which mutates genes by flipping bits with a certain probability.
 * 
 * @author jfinke
 */
public class BitwiseMutationFunction implements MutationFunction<BinaryString> {
  /** The default probability of flipping a single bit. */
  public static final double DEFAULT_MUTATION_PROBABILITY = 0.05;
  /** The probability of flipping a single bit. */
  private double mutationProbability = DEFAULT_MUTATION_PROBABILITY;

  /**
   * Perform a bitwise mutation on bits in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose gene will be mutated.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final BinaryString individual) {
    // iterate over each bit in the binary string
    for (int i = 0; i < individual.length(); ++i) {

      // if a mutation is needed
      if (Math.random() <= this.mutationProbability) {

        // flip the bit
        individual.flipBit(i);
      }
    }
  }

  /**
   * Set the probability of flipping a bit.
   * 
   * @param newMutationProbability
   *          The probability of flipping a bit.
   */
  public void setMutationProbability(final double newMutationProbability) {
    this.mutationProbability = newMutationProbability;
  }
}
