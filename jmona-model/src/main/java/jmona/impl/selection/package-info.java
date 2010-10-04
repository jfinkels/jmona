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
 * Provides some commonly used fitness-dependent selection algorithms.
 * </p>
 * 
 * <p>
 * This package includes:
 * <ul>
 * <li>{@link jmona.impl.selection.ElitismSelectionFunction}, which selects an
 * arbitrary number of the most fit individuals</li>
 * <li>{@link jmona.impl.selection.FitnessProportionateSelection} (also known as
 * "roulette wheel selection"), which selects individuals with probability
 * proportionate to their fitnesses</li>
 * <li>{@link jmona.impl.selection.LinearRankingSelection}, which selects
 * individuals by using their ranks as their fitnesses</li>
 * <li>{@link jmona.impl.selection.StochasticUniversalSampling}, which selects
 * multiple individuals simultaneously, with probabilities proportionate to
 * their fitnesses</li>
 * <li>{@link jmona.impl.selection.TournamentSelection}, which selects
 * individuals by pitting them against one another in a tournament of fitnesses</li>
 * </ul>
 * </p>
 * 
 * <p>
 * For more information, see:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Stochastic_universal_sampling">
 * Wikipedia article on stochastic universal sampling</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Fitness_proportionate_selection">
 * Wikipedia article on fitness proportionate selection</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Tournament_selection">Wikipedia
 * article on tournament selection</a></li>
 * </ul>
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
package jmona.impl.selection;

