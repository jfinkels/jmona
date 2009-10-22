/**
 * OnesCompletionCriteria.java
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

import java.util.Arrays;

import jmona.EvolutionContext;
import jmona.impl.MaxGenerationCompletionCriteria;

/**
 * The completion criteria for the Ones evolution.
 * 
 * @author jeff
 */
public class OnesCompletionCriteria extends
    MaxGenerationCompletionCriteria<OnesIndividual> {
  /** The best gene possible for an individual in the evolution. */
  private final short[] maxGene;

  /**
   * Instantiate this criteria with a gene of the specified size.
   * 
   * @param geneSize
   *          The size of the gene in the individuals.
   */
  public OnesCompletionCriteria(final int geneSize) {

    // create the max gene
    this.maxGene = new short[geneSize];

    // the best gene is all ones
    for (int i = 0; i < geneSize; ++i) {
      this.maxGene[i] = 1;
    }
  }

  /**
   * Whether the maximum number of generations has been met, or whether any of
   * the individuals in the population of the specified evolution context
   * contain the best possible gene.
   * 
   * @param context
   *          {@inheritDoc}
   * @return Whether the maximum number of generations has been met, or whether
   *         any of the individuals in the population of the specified evolution
   *         context contain the best possible gene.
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<OnesIndividual> context) {

    // if the maximum number of generations has been met
    if (super.isSatisfied(context)) {
      return true;
    }

    // if any of the individuals in the population have the best gene
    for (final OnesIndividual individual : context.currentPopulation()) {
      if (Arrays.equals(individual.gene(), this.maxGene)) {
        return true;
      }
    }

    return false;
  }
}
