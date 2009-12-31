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
 * Provides classes for evolving solutions to the traveling salesman problem
 * (TSP).
 * </p>
 * 
 * <p>
 * Individuals in the genetic algorithm solution to the traveling salesman
 * problem can be modeled as a sequence of integers, representing the sequence
 * of cities traveled in a graph of known size. The evolution evolves toward a
 * minimum distance tour of all vertices in the graph (the shortest Hamiltonian
 * cycle). Mutation functions are provided in {@link jmona.example.tsp.mutation}
 * package and crossover functions are provided in
 * {@link jmona.example.tsp.crossover} package (these are the most involved part
 * of the TSP evolution).
 * </p>
 * 
 * @author jfinkels
 * @see jmona.example.tsp.crossover
 * @see jmona.example.tsp.io
 * @see jmona.example.tsp.mutation
 */
package jmona.example.tsp;

