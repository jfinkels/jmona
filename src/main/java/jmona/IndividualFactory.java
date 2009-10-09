/**
 * IndividualFactory.java
 */
package jmona;

/**
 * An object which provides a single factory method for generating an
 * Individual.
 * 
 * @param <T>
 *          The type of Individual to generate.
 * @author jeff
 */
public interface IndividualFactory<T extends Individual> {
  /**
   * Create a new instance of an Individual of type T.
   * 
   * @return A new instance of an Individual of type T.
   * @throws InitializationException
   *           If there is a problem creating an Individual.
   */
  T createIndividual() throws InitializationException;
}
