/**
 * MutatorFunction.java
 */
package jmona;

/**
 * A class which provides one method for mutating an individual in place.
 * 
 * @param <T>
 *          The type of Individual to mutate.
 * @author jfinke
 */
public interface MutatorFunction<T extends Individual> {
  /**
   * Mutate the specified individual in place.
   * 
   * @param individual
   *          The individual to mutate.
   * @throws MutationException
   *           If there is a problem during mutation.
   */
  void mutate(final T individual) throws MutationException;
}
