/**
 * CompletionCriteria.java
 */
package jmona;

/**
 * An object which contains a method which tells whether the criteria for the
 * completion of the evolution have been met. For example, a certain number of
 * generations have been evolved, or an individual with a maximum fitness has
 * been born, etc.
 * 
 * @param <T>
 *          The type of the Individual in the evolution whose completion
 *          criteria this object encapsulates.
 * @author jeff
 */
public interface CompletionCriteria<T extends Individual> {
  /**
   * Whether the criteria for completion of the evolution have been met.
   * 
   * @param context
   *          The evolution context which contains this completion criteria.
   * @return Whether the criteria for completion of the evolution have been met.
   */
  boolean isSatisfied(final EvolutionContext<T> context);
}
