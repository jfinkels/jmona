/**
 * OnesIndividualFactory.java
 */
package jmona.example.ones;

import jmona.IndividualFactory;
import jmona.InitializationException;
import jmona.impl.Util;

/**
 * A factory for creating OnesIndividuals with random bitstring genes.
 * 
 * @author jeff
 */
public class OnesIndividualFactory implements IndividualFactory<OnesIndividual> {

  /**
   * The default length of the bitstring in the gene of a created Individual.
   */
  public static final int DEFAULT_LENGTH = 4;
  /** The length of the bitstring in the gene of a created Individual. */
  private int length = DEFAULT_LENGTH;

  /**
   * Create a new instance of a OnesIndividual with a uniformly random bitstring
   * gene.
   * 
   * @return A new instance of a OnesIndividual with a uniformly random
   *         bitstring gene.
   * @throws InitializationException
   *           {@inheritDoc}
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public OnesIndividual createIndividual() throws InitializationException {
    // create a new array of shorts of the specified size
    final short[] gene = new short[this.length];

    // iterate over the length of that array
    for (int i = 0; i < gene.length; ++i) {
      // assign either a 1 or a 0 with equal probability to each cell
      gene[i] = (short) Util.RANDOM.nextInt(2);
    }

    return new OnesIndividual(gene);
  }

  /**
   * Set the length of the bitstring in the gene of a created Individual.
   * 
   * @param newLength
   *          The length of the bitstring in the gene of a created Individual.
   */
  public void setLength(final int newLength) {
    this.length = newLength;
  }

}
