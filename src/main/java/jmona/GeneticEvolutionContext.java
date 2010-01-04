/**
 * GeneticEvolutionContext.java
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
package jmona;

/**
 * A context in which genetic evolution occurs.
 * 
 * @param <T>
 *          The type of individual on which this evolution occurs.
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
// TODO allow multiple MutationFunctions or CrossoverFunctions?
public interface GeneticEvolutionContext<T extends DeepCopyable<T>> extends
    EvolutionContext<T> {

  /**
   * Get the crossover function used by this context.
   * 
   * @return The crossover function used by this context.
   */
  CrossoverFunction<T> crossoverFunction();

  /**
   * Get the probability of crossover for selected Individuals.
   * 
   * @return The probability of crossover for selected Individuals.
   */
  double crossoverProbability();

  /**
   * Get the fitness function used by this context.
   * 
   * @return The fitness function used by this context.
   */
  FitnessFunction<T> fitnessFunction();

  /**
   * Get the mutation function used by this context.
   * 
   * @return The mutation function used by this context.
   */
  MutationFunction<T> mutationFunction();

  /**
   * Get the probability of mutation for selected Individuals.
   * 
   * @return The probability of mutation for selected Individuals.
   */
  double mutationProbability();

  /**
   * Get the selection function used by this context.
   * 
   * @return The selection function used by this context.
   */
  IndependentSelectionFunction<T> selectionFunction();

  /**
   * Set the crossover function used by this context.
   * 
   * @param function
   *          The crossover function used by this context.
   */
  void setCrossoverFunction(final CrossoverFunction<T> function);

  /**
   * Set the probability of crossover for selected Individuals.
   * 
   * @param newCrossoverProbability
   *          The probability of crossover for selected Individuals.
   */
  void setCrossoverProbability(final double newCrossoverProbability);

  /**
   * Set the fitness function used by this context.
   * 
   * @param function
   *          The fitness function used by this context.
   */
  void setFitnessFunction(final FitnessFunction<T> function);

  /**
   * Set the mutation function used by this context.
   * 
   * @param function
   *          The mutation function used by this context.
   */
  void setMutationFunction(final MutationFunction<T> function);

  /**
   * Set the probability of mutation for selected Individuals.
   * 
   * @param newMutationProbability
   *          The probability of mutation for selected Individuals.
   */
  void setMutationProbability(final double newMutationProbability);

  /**
   * Set the selection function used by this context.
   * 
   * @param function
   *          The selection function used by this context
   */
  void setSelectionFunction(final IndependentSelectionFunction<T> function);
}
