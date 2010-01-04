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
package jmona.example.tsp;

import java.util.List;

import jmona.impl.fitness.MinimizingFitnessFunction;

/**
 * A FitnessFunction which determines the raw fitness of a tour based on its
 * total distance in a graph.
 * 
 * @author Jeffrey Finkelstein
 */
public class TSPFitnessFunction extends
    MinimizingFitnessFunction<List<Integer>> {

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
   * Gets the total distance of the specified tour, based on the edge weights in
   * the Graph specified in the constructor to this class.
   * 
   * @param tour
   *          The tour whose raw fitness will be determined.
   * @return The total distance of this tour.
   * @throws IllegalArgumentException
   *           If the specified tour is empty (that is, it has size 0).
   * @see jmona.FitnessFunction#fitness(Object)
   */
  @Override
  public double rawFitness(final List<Integer> tour) {
    if (tour.size() == 0) {
      throw new IllegalArgumentException("Tour must have size greater than 0.");
    }

    return GraphUtil.totalDistance(tour, this.graph);
  }

}
