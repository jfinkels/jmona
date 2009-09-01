/**
 * Individual.java
 */
package jmona;

/**
 * An individual in a population.
 * 
 * @author jfinke
 */
public interface Individual extends Cloneable {
  /**
   * Get a cloned copy of this individual.
   * 
   * @return A cloned copy of this individual.
   */
  Individual copy();
}
