/**
 * ExampleIndividual.java
 */
package jmona.impl;

import jmona.Individual;

/**
 * A basic implementation of an Individual.
 * 
 * @author jeff
 */
public class ExampleIndividual implements Individual {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.Individual#copy()
   */
  @Override
  public <T extends Individual> T copy() {
    return (T) new ExampleIndividual();
  }

}
