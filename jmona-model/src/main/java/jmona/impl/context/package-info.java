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
 * Provides base classes for EvolutionContext implementations.
 * </p>
 * 
 * <p>
 * The {@link jmona.impl.context.AbstractEvolutionContext} is the base class for
 * all EvolutionContexts.
 * {@link jmona.impl.context.AbstractPopulationEvolutionContext} is an
 * EvolutionContext which involves a population in some way (for example, a
 * population of genes to evolve, or a population of game strategies to play
 * against one another). And
 * {@link jmona.impl.context.AbstractGeneticEvolutionContext} is the base class
 * for genetic evolutions, which involve crossover and mutation, among other
 * features.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
package jmona.impl.context;

