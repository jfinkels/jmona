/**
 * package-info.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
 * Provides a default implementation of genetic algorithms interface with some
 * common necessary functionality.
 * </p>
 * 
 * <p>
 * The most important class is the {@link jmona.ga.impl.GAEvolutionContext},
 * which provides the context in which a genetic algorithm can run.
 * </p>
 * 
 * <p>
 * The user of this package can create a genetic algorithm evolution in whatever
 * way he or she wants. However, we also provide an interface for creating the
 * "standard" genetic algorithm, which evolves binary strings. To that effect,
 * this class provides the {@link jmona.ga.impl.BitFactory} class, which can be
 * used to create a sequence of bits (represented as Byte objects) which can be
 * used in a genetic algorithm in the more traditional way. A mutation function
 * which flips bits is provided in
 * {@link jmona.ga.impl.BinaryStringMutationFunction} (which has a
 * {@link jmona.ga.impl.BitFlipMutationFunction} instance). The plain old
 * {@link jmona.ga.impl.OnePointCrossoverFunction} and
 * {@link jmona.ga.impl.TwoPointCrossoverFunction} are provided as well.
 * </p>
 * 
 * <p>
 * Therefore to write a genetic algorithm, one simply needs
 * </p>
 * 
 * <p>
 * For more information, see the <a
 * href="http://en.wikipedia.org/wiki/Genetic_algorithm">Wikipedia article on
 * genetic algorithms</a>.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
package jmona.ga.impl;

