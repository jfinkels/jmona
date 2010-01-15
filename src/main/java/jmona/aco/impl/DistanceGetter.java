/**
 * DistanceGetter.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.aco.impl;

import jmona.Function;
import jmona.aco.Ant;
import jmona.graph.DirectedGraph;
import jmona.graph.GraphUtils;

/**
 * Function which gets the distance of an Ant's currently completed tour.
 * 
 * @author Jeffrey Finkelstein
 * @param <A>
 *          The type of ant in the ant colony optimization.
 * @since 0.5
 */
public class DistanceGetter<A extends Ant> implements Function<A, Double> {

  /** The graph on which to measure distances. */
  private final DirectedGraph<Integer, Double> graph;

  /**
   * Instantiates this function with the specified graph with which to determine
   * distances of tours.
   * 
   * @param initialGraph
   *          The graph with which to determine distances of tours.
   */
  public DistanceGetter(final DirectedGraph<Integer, Double> initialGraph) {
    this.graph = initialGraph;
  }

  /**
   * Gets the total distance of the tour specified by the WorkerAnt's memory.
   * 
   * @param ant
   *          The WorkerAnt whose memory specifies the tour whose distance to
   *          get.
   * @return The total distance of the tour specified by the WorkerAnt's memory.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Double execute(final Ant ant) {
    return GraphUtils.totalDistance(ant.memory(), this.graph);
  }

}
