/**
 * DefaultPopulation.java
 */
package jmona.impl;

import java.util.Collection;
import java.util.Vector;

import jmona.Individual;
import jmona.Population;

/**
 * A default implementation of a population which inherits from a Vector.
 * 
 * @param <T>
 *          The type of Individual in this population.
 * @author jfinke
 */
public class DefaultPopulation<T extends Individual> extends Vector<T>
    implements Population<T> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -3999577414252879241L;

  /**
   * Instantiate this population by calling the default constructor of the
   * superclass.
   */
  public DefaultPopulation() {
    super();
  }

  /**
   * Instantiate this population with the specified initial collection of
   * individuals.
   * 
   * @param initialCollection
   *          The initial collection of individuals.
   */
  public DefaultPopulation(final Collection<T> initialCollection) {
    super(initialCollection);
  }

}
