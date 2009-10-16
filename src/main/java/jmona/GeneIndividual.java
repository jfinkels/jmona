/**
 * GeneIndividual.java
 */
package jmona;

/**
 * An Individual with a gene of type T.
 * 
 * @param <T>
 *          The type of the gene.
 * 
 * @author jeff
 */
public interface GeneIndividual<T> extends Individual {
  /**
   * Get the gene of this Individual.
   * 
   * @return The gene of this Individual.
   */
  T gene();
}
