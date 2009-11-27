/**
 * OnesCrossoverFunction.java
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
package jmona.example.ga.ones;

import jmona.CrossoverFunction;
import jmona.impl.Util;

/**
 * Class containing one method for generating copies of specified individuals
 * with two-point crossover.
 * 
 * @author jfinke
 */
public class OnesCrossoverFunction implements CrossoverFunction<OnesIndividual> {

  /**
   * Perform a two-point crossover.
   * 
   * @param parent1
   *          An individual.
   * @param parent2
   *          Another individual.
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final OnesIndividual parent1,
      final OnesIndividual parent2) {

    // get the genes of the children
    final short[] gene1 = parent1.gene();
    final short[] gene2 = parent2.gene();

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

    parent1.setGene(gene1);
    parent2.setGene(gene2);
  }

}