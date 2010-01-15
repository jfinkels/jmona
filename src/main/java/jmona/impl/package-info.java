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
 * Provides some default implementations of the evolutionary algorithms
 * interface defined in the <code>jmona</code> package.
 * </p>
 * 
 * <p>
 * Among other classes, this package provides:
 * <ul>
 * <li>{@link jmona.impl.context.AbstractEvolutionContext}, a base class for
 * {@link jmona.EvolutionContext} implementations.</li>
 * <li>{@link jmona.impl.completion.MaxGenerationCompletionCondition}, a default
 * {@link jmona.CompletionCondition} implementation which checks an {@code
 * EvolutionContext} for a maximum number of generations evolved.</li>
 * <li>{@link jmona.impl.postprocessing.PeriodicPostProcessor}, a base class for
 * {@link jmona.PostProcessor} implementations which execute periodically.</li>
 * <li>{@link jmona.impl.CompleteDeepCopyableListFactory}, a factory for
 * creating a List of objects which implement {@link jmona.DeepCopyable}, for
 * use as, for example, an individual in a population in an EvolutionContext.</li>
 * <li>{@link jmona.impl.PartialDeepCopyableListFactory}, a factory for creating
 * a DeepCopyable List of objects which are not DeepCopyable themselves. For
 * example, in the "ones" evolution in the {@link jmona.example.ones} package,
 * the genetic algorithm operates on indviduals which are represented by a
 * DeepCopyable List of Byte objects. The List itself is the individual, so it
 * must be DeepCopyable, but the Byte objects contained in the List are
 * immutable, so they do not need to be DeepCopyable.</li>
 * </ul>
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
package jmona.impl;

