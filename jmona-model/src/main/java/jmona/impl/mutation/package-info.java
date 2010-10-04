/**
 * package-info.java
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
/**
 * <p>
 * Provides off-the-shelf reusable classes which mutate common structure in
 * evolutions (like Lists).
 * </p>
 * 
 * <p>
 * Mutation functions like {@link jmona.impl.mutation.InversionMutationFunction}, {@link jmona.impl.mutation.InsertionMutationFunction},
 * {@link jmona.impl.mutation.SwapMutationFunction} and
 * {@link jmona.impl.mutation.DisplacementMutationFunction} are useful for
 * mutating lists in which order affects the fitness of the individual, like in
 * a list representing a tour in a graph when evolving a solution to the
 * traveling salesman problem, for example.
 * </p>
 * 
 * <p>
 * Mutation functions like
 * {@link jmona.impl.mutation.SingleElementwiseMutationFunction},
 * {@link jmona.impl.mutation.UniformDistributionMutationFunction} are useful
 * for mutating lists in which order does not affect the fitness of the
 * individual, like in the Mona Lisa image matching genetic algorithm.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
package jmona.impl.mutation;

