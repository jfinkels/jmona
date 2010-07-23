/**
 * TourEvolutionContext.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.EvolutionException;
import jmona.IndependentSelectionFunction;
import jmona.SelectionException;
import jmona.aco.Ant;
import jmona.aco.PheromoneDirectedGraph;
import jmona.aco.PheromoneUpdateStrategy;
import jmona.impl.context.AbstractPopulationEvolutionContext;
import jmona.impl.selection.FitnessProportionateSelection;

/**
 * An EvolutionContext in which each generation moves an Ant to another vertex
 * in a graph.
 * 
 * @author Jeffrey Finkelstein
 * @param <A>
 *          The type of Ant which is moved around the graph.
 * @since 0.5
 */
class TourEvolutionContext<A extends Ant> extends
    AbstractPopulationEvolutionContext<A> {

  /**
   * The default relative importance of pheromone on an edge with respect to the
   * visibility of the vertices incident to that edge.
   */
  public static final double DEFAULT_PHEROMONE_IMPORTANCE = 1.0;
  /**
   * The default relative importance of visibility of vertices with respect to
   * the amount of pheromone on the edge incident to both of those vertices.
   */
  public static final double DEFAULT_VISIBILITY_IMPORTANCE = 1.0;
  /**
   * Fitness-proportionate selection, used to select a city to which an Ant will
   * move on each generation.
   */
  private static final IndependentSelectionFunction<Integer> FPS = new FitnessProportionateSelection<Integer>();

  /** The graph in which the Ants live. */
  private final PheromoneDirectedGraph<Integer, Double> graph;
  /**
   * The relative importance of pheromone on an edge with respect to the
   * visibility of the vertices incident to that edge.
   */
  private double pheromoneImportance = DEFAULT_PHEROMONE_IMPORTANCE;
  /**
   * The strategy which determines how much pheromone each Ant leaves on edges
   * as it encounters them.
   */
  private final PheromoneUpdateStrategy updateStrategy;
  /**
   * The relative importance of visibility of vertices with respect to the
   * amount of pheromone on the edge incident to both of those vertices.
   */
  private double visibilityImportance = DEFAULT_VISIBILITY_IMPORTANCE;

  /**
   * Instantiates this EvolutionContext with the specified initial population of
   * Ants (each on their respective initial city), the graph on which the Ants
   * move, and the specified PheromoneUpdateStrategy, which describes how much
   * pheromone each Ant leaves on edges in its tour as it encounters them.
   * 
   * @param initialPopulation
   *          The initial population of Ants, each one on its initial city.
   * @param initialGraph
   *          The graph on which the Ants move.
   * @param initialUpdateStrategy
   *          The strategy which describes how much pheromone to leave on edges
   *          in an Ant's tour.
   */
  TourEvolutionContext(final List<A> initialPopulation,
      final PheromoneDirectedGraph<Integer, Double> initialGraph,
      final PheromoneUpdateStrategy initialUpdateStrategy) {
    super(initialPopulation);

    this.graph = initialGraph;
    this.updateStrategy = initialUpdateStrategy;
  }

  /**
   * Gets the next vertex to which the Ant will move (but does not move it
   * there).
   * 
   * @param ant
   *          The Ant whose next vertex will be determined.
   * @return The next vertex to which the Ant will move.
   * @throws SelectionException
   *           If there is a problem selecting a vertex to which to move.
   */
  protected Integer chooseNextVertex(final A ant) throws SelectionException {

    // get the (asymmetric) set difference between all vertices in the graph and
    // all vertices already visited by the ant
    final List<Integer> allowedVertices = new Vector<Integer>(this.graph
        .allVertices());
    allowedVertices.removeAll(ant.memory());

    // initialize a map from vertex to probability of moving to that vertex
    final Map<Integer, Double> probabilities = new HashMap<Integer, Double>();

    // get the ant's current vertex
    final int currentVertex = ant.currentVertex();

    // initialize some variables to use in the next loop
    double probability = 0;
    double weightedPheromone = 0;
    double weightedVisibility = 0;

    // iterate over each vertex in the list of allowed vertices to which the ant
    // may move
    for (final Integer vertex : allowedVertices) {

      // get the weighted value of the pheromone on this edge
      weightedPheromone = Math.pow(this.graph.pheromoneBetween(currentVertex,
          vertex), this.pheromoneImportance);

      // get the weighted value of the visibility on this edge
      weightedVisibility = Math.pow(this.graph.visibilityBetween(currentVertex,
          vertex), this.visibilityImportance);

      // get the product of those values
      probability = weightedPheromone * weightedVisibility;

      // put that product in the map from vertex to probability of moving there
      probabilities.put(vertex, probability);
    }

    // use fitness-proportionate selection to choose a vertex to which to move
    return FPS.select(probabilities);
  }

  /**
   * Moves each Ant in the current population to one vertex not already visited
   * by the Ant.
   * 
   * @throws EvolutionException
   *           If there is a problem selecting a vertex to which to move an Ant.
   * @see jmona.impl.context.AbstractEvolutionContext#executeGenerationStep()
   */
  @Override
  protected void executeGenerationStep() throws EvolutionException {
    try {

      // iterate over each ant
      for (final A ant : this.currentPopulation()) {

        // get the current city of the ant
        final Integer currentCity = ant.currentVertex();

        // get the next city to which to move
        final Integer nextCity = this.chooseNextVertex(ant);

        // get the distance between these two cities
        final double distance = this.graph.edgeBetween(currentCity, nextCity);

        // get the amount of pheromone to add on this edge
        final double amountToAdd = this.updateStrategy
            .pheromoneToAddSingleEdge(distance);

        // add that amount of pheromone to this edge
        this.graph.addPheromoneBetween(currentCity, nextCity, amountToAdd);

        // move the ant to the next city
        ant.moveTo(nextCity);
      }
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed to select next city for ant.",
          exception);
    }
  }

  /**
   * Sets the relative importance of pheromone on edges with respect to
   * visibility of vertices.
   * 
   * @param newPheromoneImportance
   *          The relative importance of pheromone with respect to visibility of
   *          vertices.
   */
  public void setPheromoneImportance(final double newPheromoneImportance) {
    this.pheromoneImportance = newPheromoneImportance;
  }

  /**
   * Sets the relative importance of visibility of vertices with respect to
   * pheromone on edges.
   * 
   * @param newVisibilityImportance
   *          The relative importance of visibility of vertices with respect to
   *          pheromone on edges.
   */
  public void setVisibilityImportance(final double newVisibilityImportance) {
    this.visibilityImportance = newVisibilityImportance;
  }
}
