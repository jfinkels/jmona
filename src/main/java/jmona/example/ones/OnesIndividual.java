/**
 * OnesIndividual.java
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
   * Instantiate the individual with the specified initial gene.
   * 
   * @param initialGene
   *          The initial gene of this individual.
   */
  public OnesIndividual(final short[] initialGene) {
    this.gene = initialGene.clone();
  }

  /**
   * Get a cloned copy of this individual.
   * 
   * @return A cloned copy of this individual.
   */
  public OnesIndividual copy() {
    return new OnesIndividual(this.gene.clone());
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
