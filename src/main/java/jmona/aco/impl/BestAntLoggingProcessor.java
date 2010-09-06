/**
 * BestAntLoggingProcessor.java
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

import java.util.Collections;

import jmona.PopulationEvolutionContext;
import jmona.aco.Ant;
import jmona.graph.DirectedGraph;
import jmona.impl.postprocessing.LoggingProcessor;

/**
 * Logs the Ant with the best tour.
 * 
 * @author Jeffrey Finkelstein
 * @param <A>
 *          The type of Ant in the evolution.
 * @since 0.5
 */
public class BestAntLoggingProcessor<A extends Ant, E extends PopulationEvolutionContext<A>>
    extends LoggingProcessor<A, E> {

  /**
   * The comparator which compares Ants based on the distance of the tour stored
   * in their memory.
   */
  private final AntComparator<A> comparator;

  /**
   * The function which gets the total distance of the tour specified in the
   * memory of an Ant.
   */
  private final DistanceGetter<A> distanceGetter;

  /**
   * Instantiates this Processor with the specified graph with which to
   * determine distances of tours.
   * 
   * @param initialGraph
   *          The graph with which to determine distances of tours.
   */
  public BestAntLoggingProcessor(
      final DirectedGraph<Integer, Double> initialGraph) {
    this.comparator = new AntComparator<A>(initialGraph);
    this.distanceGetter = new DistanceGetter<A>(initialGraph);
  }

  /**
   * Returns the String representation of the Ant with the best tour.
   * 
   * @param context
   *          {@inheritDoc}
   * @return The String representation of the Ant with the best tour.
   * @see jmona.impl.postprocessing.LoggingProcessor#message(jmona.EvolutionContext)
   */
  @Override
  protected String message(final E context) {

    final StringBuilder result = new StringBuilder();

    final A bestAnt = Collections.min(context.currentPopulation(),
        this.comparator);

    result.append("distance " + this.distanceGetter.execute(bestAnt));
    result.append(": ");
    result.append(bestAnt);

    return result.toString();
  }
}
