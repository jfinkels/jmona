/**
 * AggregatorCrossoverFunction.java
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
package jmona.impl.crossover;

import java.util.Collection;

import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.impl.UnmodifiableCollectionAggregator;
import jmona.random.RandomUtils;

/**
 * A CrossoverFunction which chooses from a specified Collection of
 * CrossoverFunctions when performing crossover.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual on which to perform crossover.
 * @since 0.5
 */
public class AggregatorCrossoverFunction<T> extends
    UnmodifiableCollectionAggregator<CrossoverFunction<T>> implements CrossoverFunction<T> {

  /**
   * Instantiates this CrossoverFunction with the specified Collection of
   * CrossoverFunctions from which to choose when performing crossover.
   * 
   * @param initialCollection
   *          The Collection of CrossoverFunctions from which to choose when
   *          performing crossover.
   */
  public AggregatorCrossoverFunction(
      final Collection<CrossoverFunction<T>> initialCrossoverFunctions) {
    super(initialCrossoverFunctions);
  }

  /**
   * Chooses a CrossoverFunction with uniformly random probability over the
   * Collection of CrossoverFunctions supplied to the constructor of this class.
   * 
   * @param individual1
   *          An individual.
   * @param individual2
   *          Another individual.
   * @throws CrossoverException
   *           If the chosen CrossoverFunction throws a CrossoverException.
   * @see jmona.CrossoverFunction#crossover(java.lang.Object, java.lang.Object)
   */
  @Override
  public void crossover(final T individual1, final T individual2)
      throws CrossoverException {
    RandomUtils.choice(this.collection()).crossover(individual1, individual2);
  }

}
