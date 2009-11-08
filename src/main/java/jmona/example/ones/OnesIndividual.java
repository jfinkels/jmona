/**
 * OnesIndividual.java
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

import jmona.Individual;

/**
 * An individual containing an array of shorts.
 * 
 * @author jfinke
 */
public class OnesIndividual implements Individual {
  /** The gene of this individual. */
  private short[] gene;

  /**
   * Instantiation with default constructor disallowed except for subclasses of
   * this class.
   */
  protected OnesIndividual() {
    // intentionally unimplemented
  }

  /**
   * Instantiate the individual with the specified initial gene.
   * 
   * @param initialGene
   *          The initial gene of this individual.
   */
  public OnesIndividual(final short[] initialGene) {
    this.gene = initialGene.clone();
  }

  /**
   * Get a copy of the gene of this individual. Returns a cloned copy of the
   * array, for the sake of security.
   * 
   * @return A copy of the gene of this individual.
   */
  public short[] gene() {
    return this.gene.clone();
  }

  /**
   * Set the gene of this individual. Stores a cloned copy of the array, for the
   * sake of security.
   * 
   * @param newGene
   *          The new gene of this individual.
   */
  public void setGene(final short[] newGene) {
    this.gene = newGene.clone();
  }

  /**
   * Get the string representation of the gene of this individual.
   * 
   * @return The string representation of the gene of this individual.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();

    // append each of the bits to the string builder
    for (int i = 0; i < this.gene.length; ++i) {
      sb.append(this.gene[i]);
    }

    return sb.toString();
  }
}
