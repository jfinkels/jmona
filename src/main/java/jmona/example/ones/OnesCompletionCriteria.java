/**
 * OnesCompletionCriteria.java
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
