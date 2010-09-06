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
 * Provides {@link jmona.Processor} classes for examining
 * {@link jmona.EvolutionContext} objects during the run of an evolution.
 * </p>
 * 
 * <p>
 * This package includes, among other classes:
 * <ul>
 * <li>{@link jmona.impl.postprocessing.BestIndividualProcessor}, which logs
 * the individual with the best fitness in the EvolutionContext</li>
 * <li>{@link jmona.impl.postprocessing.FitnessLoggingProcessor}, which logs
 * the fitnesses of all individuals in the EvolutionContext</li>
 * <li>{@link jmona.impl.postprocessing.PopulationLoggingProcessor}, which
 * logs the string representation of all individuals in the EvolutionContext</li>
 * </ul>
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
package jmona.impl.postprocessing;

