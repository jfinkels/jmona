/**
 * OnesCrossoverFunction.java
 */
package jmona.example.ones;

import jmona.CrossoverFunction;
import jmona.Pair;
import jmona.impl.Util;

/**
 * Class containing one method for performing a two-point crossover in place on
 * a pair of individuals.
 * 
 * @author jfinke
 */
public class OnesCrossoverFunction implements CrossoverFunction<OnesIndividual> {

  /**
   * Perform a two-point crossover.
   * 
   * @param parents
   *          The parents on which to cross over slices of genes.
   * @return Children having genes resulting from a random two-point crossover
   *         of the parent genes.
   * @see jmona.CrossoverFunction#crossover(jmona.Pair)
   */
  @Override
  public Pair<OnesIndividual, OnesIndividual> crossover(
      final Pair<OnesIndividual, OnesIndividual> parents) {

    // get a cloned copy of the parents to be the base children
    final OnesIndividual leftChild = parents.left().copy();
    final OnesIndividual rightChild = parents.right().copy();

    // get the genes of the children
    final short[] gene1 = leftChild.gene();
    final short[] gene2 = rightChild.gene();

    // get the total length of a gene
    final int geneLength = gene1.length;

    // get a random start for the slice
    final int start = Util.RANDOM.nextInt(geneLength);

    // get a random end for the slice
    final int end = start + Util.RANDOM.nextInt(geneLength - start);

    // swap the bits from the two gene slices, from start to end
    for (int i = start; i < end; ++i) {
      final short temp = gene1[i];
      gene1[i] = gene2[i];
      gene2[i] = temp;
    }

    return new Pair<OnesIndividual, OnesIndividual>(leftChild, rightChild);
  }

}
