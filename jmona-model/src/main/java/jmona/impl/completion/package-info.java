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
 * Provides basic CompletionConditions, which provide a mechanism for
 * determining when an EvolutionContext is complete (and should stop).
 * </p>
 * 
 * <p>
 * The {@link jmona.impl.completion.MaxGenerationCompletionCondition} returns
 * true when a specified number of generations have passed in an
 * EvolutionContext. The
 * {@link jmona.impl.completion.PerfectMatchCompletionCondition} returns true
 * when an individual in an EvolutionContext has the best fitness possible. The
 * {@link jmona.impl.completion.AggregatorCompletionCondition} simply checks
 * whether all of a given Collection of CompletionConditions are true.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
package jmona.impl.completion;

