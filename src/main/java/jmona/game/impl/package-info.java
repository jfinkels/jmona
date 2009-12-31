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
 * Provides a default implementation of a game evolution.
 * </p>
 * 
 * <p>
 * The {@link GameEvolutionContext} matches {@link Strategy} objects (as
 * individuals in a population) against each other, and assigns fitness based on
 * their overall score. The {@link UniformPopulationCompletionCondition} is
 * provided for stopping the evolution when all individuals are of the same
 * class.
 * </p>
 * 
 * @author jfinkels
 * @see jmona.game
 */
package jmona.game.impl;
