/**
 * package-info.java
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
/**
 * <p>
 * Provides crossover functions for a genetic algorithm evolution of solutions
 * to the traveling salesman problem.
 * </p>
 * 
 * <p>
 * The {@link CycleCrossoverFunction} (also known as CX) uses the notion of an
 * algebraic permutation and its distinct decomposition into disjoint cycles to
 * swap a set of cities in two tours so that there will be no repeat cities in
 * either tour after the swap.
 * </p>
 * 
 * <p>
 * The {@link EdgeRecombinationCrossoverFunction} (also known as ERX) uses the
 * popularity of edges to generate a tour consisting of the most popular edges
 * in both tours.
 * </p>
 * 
 * <p>
 * The {@link OrderedCrossoverFunction} (also known as OX) performs a two-point
 * crossover (as in {@link jmona.ga.impl.TwoPointCrossoverFunction}), then
 * removes invalid repeated cities and adds missing ones, in the order they are
 * found in the original tours.
 * </p>
 * 
 * <p>
 * The {@link PartiallyMatchedCrossoverFunction} (also known as PMX) performs a
 * two-point crossover, then uses the permutation defined by the mapping between
 * the cities in the swapped sublists to replace repeated cities in the original
 * tours.
 * </p>
 * 
 * @author jfinkels
 * @see jmona.example.tsp
 * @see jmona.example.tsp.io
 * @see jmona.example.tsp.mutation
 */
package jmona.example.tsp.crossover;

