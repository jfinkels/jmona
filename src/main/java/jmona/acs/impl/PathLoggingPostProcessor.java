/**
 * PathLoggingPostProcessor.java
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
package jmona.acs.impl;

import jmona.EvolutionContext;
import jmona.acs.Ant;
import jmona.graph.DirectedGraph;
import jmona.graph.GraphUtil;
import jmona.impl.postprocessing.LoggingPostProcessor;

/**
 * A PostProcessor which logs the tour from each Ant's memory and the total
 * distance if a graph is specified.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class PathLoggingPostProcessor<A extends Ant> extends
    LoggingPostProcessor<A> {

  /** The graph with which total distance of a tour can be determined. */
  private DirectedGraph<Integer, Double> graph = null;

  /**
   * Sets the graph with which total distance of a tour can be determined.
   * 
   * @param newGraph
   *          The graph with which total distance of a tour can be determined.
   */
  public void setGraph(final DirectedGraph<Integer, Double> newGraph) {
    this.graph = newGraph;
  }

  /**
   * Gets the tour from each individual in the current population of the
   * specified EvolutionContext, and prints its total distance if a graph has
   * been set on this object.
   * 
   * @param context
   *          {@inheritDoc}
   * @return The tours (and total distances if a graph has been set) from
   *         individuals in the specified context.
   * @see jmona.impl.postprocessing.LoggingPostProcessor#message(jmona.EvolutionContext)
   */
  @Override
  protected String message(final EvolutionContext<A> context) {
    final StringBuilder result = new StringBuilder();

    for (final A agent : context.currentPopulation()) {
      result.append(NEWLINE);
      result.append(agent.memory());
      if (this.graph != null) {
        result.append(", total distance: ");
        result.append(GraphUtil.totalDistance(agent.memory(), this.graph));
      }
    }

    return result.toString();
  }

}
