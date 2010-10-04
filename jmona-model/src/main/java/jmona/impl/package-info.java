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
 * <li>{@link jmona.impl.DeepCopyableVector}, a Vector containing elements which
 * implement {@link jmona.DeepCopyable}, and which can itself be deep-copied.
 * This is good for creating list-like individuals, like in the
 * {@link jmona.example.ones} evolution in which the individuals are lists of
 * bits, or the {@link jmona.example.monalisa.ga} evolution, in which the
 * individuals are lists of colored polygons.</li>
 * <li>{@link jmona.impl.DeepCopyableListFactory}, a factory for creating a List
 * of objects which implement {@link jmona.DeepCopyable}, for use as, for
 * example, an individual in a population in an EvolutionContext.</li>
 * </ul>
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
package jmona.impl;

