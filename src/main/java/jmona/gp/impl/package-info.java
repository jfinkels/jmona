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
 * Provides a default implementation of the framework required to run an
 * instance of genetic programming.
 * </p>
 * 
 * <p>
 * The {@link jmona.gp.impl.GPEvolutionContext},
 * {@link jmona.gp.impl.GPMutationFunction}, and
 * {@link jmona.gp.impl.GPCrossoverFunction} classes provide most of the
 * functionality of the genetic programming evolution (with various parameters
 * adjustable through setter methods).
 * </p>
 * 
 * <p>
 * Users wishing to implement an instance of a genetic programming evolution
 * will likely need to implement a set of {@link jmona.gp.FunctionNode} classes
 * (perhaps simply providing a custom
 * {@link jmona.example.calc.operations.BinaryOperation} to a
 * {@link jmona.gp.impl.BinaryNode}, for example), and a set of
 * {@link jmona.gp.TerminalNode} classes, as well as the {@link jmona.Factory}
 * classes for creating them. Provide these factories to one of the tree
 * factories.
 * </p>
 * 
 * <p>
 * For more information on genetic programming, see the <a
 * href="http://en.wikipedia.org/wiki/Genetic_programming">Wikipedia article on
 * genetic programming</a>
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @see jmona.gp
 */
package jmona.gp.impl;

