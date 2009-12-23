/**
 * TSPFitnessFunction.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
 * 
 * This file is part of jmona.
 * 
 * jmona is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * jmona is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jmona. If not, see <http://www.gnu.org/licenses/>.
 */
package jmona.example.ga.tsp;

import jmona.FitnessFunction;

/**
 * 
 * We can't use the ready-made {@link jmona.ga.impl.GAFitnessFunction} here
 * because we don't know the target minimum length of the tour, so we have no
 * basis of comparison.
 * 
 * @author jfinkels
 */
public class TSPFitnessFunction implements FitnessFunction<Tour> {

  /** The graph containing distances between cities. */
  private final DirectedGraph<Integer, Double> graph;

  /**
   * Instantiate this FitnessFunction with access to the specified graph
   * containing distances between cities.
   * 
   * @param initialGraph
   *          The graph containing distances between cities.
   */
  public TSPFitnessFunction(final DirectedGraph<Integer, Double> initialGraph) {
    this.graph = initialGraph;
  }

  /**
   * Get the fitness of the specified Tour, which is the multiplicative inverse
   * of its total distance, based on the Graph specified in the constructor to
   * this class.
   * 
   * @param individual
   *          The Tour whose fitness will be determined.
   * @return The fitness of the specified Tour.
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   */
  @Override
  public double fitness(final Tour individual) {

    // get the sum of the distances between each of the vertices
    double sum = 0;
    for (int i = 1; i < individual.size(); ++i) {
      sum += this.graph.edgeBetween(individual.get(i - 1), individual.get(i));
    }

    // add the distance between the first and last vertices
    sum += this.graph.edgeBetween(individual.get(0), individual.get(individual
        .size() - 1));

    // a larger total distance should result in a lower fitness
    double result = 0;
    if (sum == 0) {
      result = Double.POSITIVE_INFINITY;
    } else {
      result = 1.0 / sum;
    }

    return result;
  }

}
