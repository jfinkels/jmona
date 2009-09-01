/**
 * OnesMutatorFunction.java
 */
package jmona.example.ones;

import jmona.MutatorFunction;
import jmona.impl.Util;

/**
 * A class which mutates genes by flipping bits with a certain probability.
 * 
 * @author jfinke
 */
public class OnesMutatorFunction implements MutatorFunction<OnesIndividual> {
  /** The probability of a bitwise mutation. */
  public static final double PROB_BITWISE_MUTATION = 0.05;

  /**
   * Perform a bitwise mutation on bits in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose gene will be mutated.
   * @see jmona.MutatorFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final OnesIndividual individual) {
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
