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
 * Provides reusable, extendable and generic fitness functions.
 * </p>
 * 
 * <p>
 * We utilize John Koza's notion of raw fitness, adjusted fitness and
 * standardized fitness. For more information, see John R. Koza's
 * <em>Genetic programming: on the programming of computers by means of natural selection</em>
 * , section 6.3.1, pages 95-98. Briefly, the raw fitness is the domain-specific
 * measure of how good the individual is (for example, distance to a target or
 * amount of food eaten). The adjusted fitness is the raw fitness score adjusted
 * so that a lower number (specifically closer to 0) is better. The standardized
 * fitness is the adjusted fitness score scaled to be a real number between 0
 * and 1.
 * </p>
 * 
 * <p>
 * The {@link jmona.impl.fitness.MaximizingFitnessFunction} and
 * {@link jmona.impl.fitness.MinimizingFitnessFunction} are the most important
 * classes in this package. Evolutions can simply extend these classes to use as
 * fitness functions, specifying the appropriate maximum or minimum raw score
 * for that particular evolution if necessary. Then all that needs to be done is
 * to write the code which determines the raw fitness score of an individual.
 * This package also provides a {@link jmona.impl.fitness.NormFitnessFunction},
 * which is a minimizing fitness function which specifically measures the
 * distance between a number and zero (specifically, the Euclidean norm of the
 * number).
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
package jmona.impl.fitness;

