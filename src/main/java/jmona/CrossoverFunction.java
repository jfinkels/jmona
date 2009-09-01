/**
 * CrossoverFunction.java
 */
package jmona;

/**
 * A class which provides a single method which performs a crossover between
 * genes of individuals in the specified population.
 * 
 * @param <T>
 *          The type of Individual whose genes will be crossed over.
 * @author jfinke
 */
public interface CrossoverFunction<T extends Individual> {

  /**
   * Crossover the genes of the specified parents, resulting in a pair of
   * children with the crossed over genes.
   * 
   * @param parents
   *          The parents whose genes will be crossed to yield children.
   * @return A pair of individuals having the crossed over genes of the parents.
   */
  Pair<T, T> crossover(final Pair<T, T> parents);
}
