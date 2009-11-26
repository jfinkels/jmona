/**
 * OnesIndividualFactory.java
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
