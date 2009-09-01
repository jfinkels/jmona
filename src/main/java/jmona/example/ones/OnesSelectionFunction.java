/**
 * OnesSelectionFunction.java
 */
package jmona.example.ones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import jmona.Population;
import jmona.SelectionFunction;
import jmona.impl.DefaultPopulation;

/**
 * A class which has a single method for selecting an arbitrary number of the
 * most fit individuals from a given set.
 * 
 * @author jfinke
 */
public class OnesSelectionFunction implements SelectionFunction<OnesIndividual> {

  /**
   * Select the specified number of individuals with the greatest fitnesses as
   * specified in the fitness map.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @param numberOfIndividuals
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * 
   * @see jmona.SelectionFunction#select(java.util.Map, int)
   */
  @Override
  public Population<OnesIndividual> select(
      final Map<OnesIndividual, Double> fitnesses, final int numberOfIndividuals) {
    final Population<OnesIndividual> result = new DefaultPopulation<OnesIndividual>();

    // get the set of individuals from the map
    final Set<OnesIndividual> individuals = fitnesses.keySet();
    
    // create an array of individuals
    final OnesIndividual[] array = new OnesIndividual[individuals.size()];

    // add each of the individuals from the set to the array
    int i = 0;
    for (final OnesIndividual individual : individuals) {
      array[i] = individual;
      i += 1;
    }
    
    // sort the individuals in the array increasing order
    Arrays.sort(array, new Comparator<OnesIndividual>() {

      /**
       * Compare the fitnesses of the two specified individuals.
       * 
       * @param o1
       *          An individual.
       * @param o2
       *          Another individual.
       * @return The comparison of the fitnesses of the two individuals.
       */
      @Override
      public int compare(final OnesIndividual o1, final OnesIndividual o2) {
        return fitnesses.get(o1).compareTo(fitnesses.get(o2));
      }
    });

    final int lowerLimit = array.length - numberOfIndividuals;

    for (int j = array.length - 1; j >= lowerLimit; --j) {
      result.add(array[j]);
    }

    return result;
  }

}
