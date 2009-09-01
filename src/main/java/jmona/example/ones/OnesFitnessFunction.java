/**
 * OnesFitnessFunction.java
 */
package jmona.example.ones;

import jmona.FitnessFunction;

/**
 * A fitness function which gives higher fitness to individuals with a greater
 * number of ones in their genes.
 * 
 * @author jfinke
 */
public class OnesFitnessFunction implements FitnessFunction<OnesIndividual> {

  /**
   * Get the number of ones in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose fitness is to be determined.
   * @return The number of ones in the gene of the specified individual.
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   */
  @Override
  public double fitness(final OnesIndividual individual) {
    double result = 0.0;

    for (final short bit : individual.gene()) {
      result += bit;
    }

    return result;
  }

}
