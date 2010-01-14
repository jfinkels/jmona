/**
 * AntColonyEvolutionContext.java
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

import java.util.List;

import jmona.EvolutionException;
import jmona.aco.Ant;
import jmona.aco.PheromoneDirectedGraph;
import jmona.aco.PheromoneUpdateStrategy;
import jmona.functional.Range;
import jmona.graph.GraphUtil;
import jmona.impl.completion.MaxGenerationCompletionCondition;
import jmona.impl.context.AbstractEvolutionContext;

/**
 * An evolution of the Ant colony system for optimization of a shortest
 * Hamiltonian cycle in a graph with real-valued edge weights.
 * 
 * Each generation of this evolution moves each Ant in the population around the
 * graph to make one complete Hamiltonian cycle.
 * 
 * @author Jeffrey Finkelstein
 * @param <A>
 *          The type of Ant which is moved around the graph.
 * @since 0.5
 */
public class AntColonyEvolutionContext<A extends Ant> extends
    AbstractEvolutionContext<A> {

  /** The default pheromone persistence factor. */
  public static final double DEFAULT_TRAIL_PERSISTENCE = 0.5;

  /** The completion condition for the TourEvolutionContext. */
  private final MaxGenerationCompletionCondition<A> condition = new MaxGenerationCompletionCondition<A>();
  /** The graph on which the Ants in this evolution live. */
  private final PheromoneDirectedGraph<Integer, Double> graph;
  /** The number of vertices in the graph. */
  private final int numberOfVertices;
  /** The trail persistence factor. */
  private double pheromonePersistence = DEFAULT_TRAIL_PERSISTENCE;
  /**
   * The EvolutionContext which controls the Ants making their way around the
   * graph.
   */
  private final TourEvolutionContext<A> tourContext;
  /**
   * The strategy which determines how much pheromone each Ant leaves on edges
   * both as it encounters them and after its tour is complete.
   */
  private final PheromoneUpdateStrategy updateStrategy;

  /**
   * Instantiates this EvolutionContext with the specified initial population of
   * Ants (each on their respective initial city), the graph on which the Ants
   * move, and the specified PheromoneUpdateStrategy, which describes how much
   * pheromone each Ant leaves on edges in its tour both as it encounters them
   * and after its tour is complete.
   * 
   * @param initialPopulation
   *          The initial population of Ants, each one on its initial city.
   * @param initialGraph
   *          The graph on which the Ants move.
   * @param initialUpdateStrategy
   *          The strategy which describes how much pheromone to leave on edges
   *          in an Ant's tour.
   */
  public AntColonyEvolutionContext(final List<A> initialPopulation,
      final PheromoneDirectedGraph<Integer, Double> initialGraph,
      final PheromoneUpdateStrategy initialUpdateStrategy) {
    super(initialPopulation);

    // set the graph on which the ants live
    this.graph = initialGraph;

    // get the number of vertices in the graph
    this.numberOfVertices = this.graph.allVertices().size();

    // set the pheromone update strategy
    this.updateStrategy = initialUpdateStrategy;

    // set the TourEvolutionContext, which moves ants around the graph
    this.tourContext = new TourEvolutionContext<A>(initialPopulation,
        initialGraph, initialUpdateStrategy);

    // set the number of steps to evolve the TourEvolutionContext
    this.condition.setMaxGenerations(this.numberOfVertices - 1);
  }

  /**
   * Moves each Ant around the graph to make a complete Hamiltonian cycle.
   * 
   * @throws EvolutionException
   *           If the EvolutionContext moving the Ants around on their tour
   *           throws an EvolutionException.
   * @see jmona.impl.context.AbstractEvolutionContext#executeGenerationStep()
   */
  @Override
  protected void executeGenerationStep() throws EvolutionException {

    // reset each ant's memory (retain only the initial city)
    for (final A ant : this.currentPopulation()) {
      ant.reset();
    }

    // move the ants around the graph so that each ant completes a tour
    while (!this.condition.execute(this.tourContext)) {
      this.tourContext.stepGeneration();
    }

    // reset the condition for next evolution step
    this.condition.setMaxGenerations(this.tourContext.currentGeneration()
        + this.numberOfVertices);

    // evaporate trail from every edge after the ant's have chosen their tours
    this.graph.evaporate(this.pheromonePersistence);

    // iterate over each ant
    for (final A ant : this.currentPopulation()) {

      // get the tour described by this ant's memory
      final List<Integer> tour = ant.memory();

      // get the total distance of the tour
      final double totalDistance = GraphUtil.totalDistance(tour, this.graph);

      // get the amount of trail to add on all edges in the tour
      final double trailToAdd = this.updateStrategy
          .pheromoneToAddFullCycle(totalDistance);

      // get the number of vertices in the tour
      final int size = tour.size();

      // iterate over each edge in the tour
      for (final int i : new Range(1, size + 1)) {

        // get the incident vertices of the current edge
        final Integer source = tour.get(i - 1);
        final Integer target = tour.get(i % size);

        // add trail to the current edge
        this.graph.addPheromoneBetween(source, target, trailToAdd);
      }
    }
  }

  /**
   * Sets the persistence factor (the percentage which remains after
   * evaporation) of the pheromone on the edges of the graph.
   * 
   * @param newPheromonePersistence
   *          The persistence factor of the pheromone on the edges of the graph.
   * @see jmona.aco.PheromoneDirectedGraph#evaporate(double)
   */
  public void setPheromonePersistence(final double newPheromonePersistence) {
    this.pheromonePersistence = newPheromonePersistence;
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
    this.tourContext.setPheromoneImportance(newPheromoneImportance);
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
    this.tourContext.setVisibilityImportance(newVisibilityImportance);
  }

}
