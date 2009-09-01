/**
 * Population.java
 */
package jmona;

import java.util.List;

/**
 * A list of individuals.
 * 
 * @param <T>
 *          The type of Individual in this population.
 * @author jfinke
 */
public interface Population<T extends Individual> extends List<T> {
}
