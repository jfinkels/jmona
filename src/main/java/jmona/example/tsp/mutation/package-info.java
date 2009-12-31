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
 * Provides mutation functions for tours in the traveling salesman problem
 * evolution.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.tsp.mutation.DisplacementMutationFunction} chooses a
 * slice of a tour, then removes and reinserts that slice to a different index
 * in the tour.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.tsp.mutation.InsertionMutationFunction} chooses a
 * random city in the tour, the removes it and reinserts it at a new random
 * location in the tour.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.tsp.mutation.InversionMutationFunction} chooses a
 * random slice in the tour, then inverts the order of the cities in that slice.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.tsp.mutation.SwapMutationFunction} chooses two
 * cities at random in the tour, then swaps them.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @see jmona.example.tsp
 * @see jmona.example.tsp.crossover
 */
package jmona.example.tsp.mutation;

