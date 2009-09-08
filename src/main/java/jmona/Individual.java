/**
 * Individual.java
 */
package jmona;

/**
 * An individual in a population.
 * 
 * @author jfinke
 */
public interface Individual {
  /**
   * Get a cloned copy of this individual.
   * 
   * @param <T>
   *          A class implementing the Individual interface.
   * @return A cloned copy of this individual.
   */
  // TODO do i have to make this generic? this leaves many unchecked conversions
  <T extends Individual> T copy();
}
