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
 * Provides an {@link jmona.example.calc.EquivalenceTester} which measures
 * equivalence of functions for use in evaluating {@link jmona.gp.Tree} objects
 * in the "calc" evolution, which evolves a formula matching a specified target
 * polynomial.
 * </p>
 * 
 * <p>
 * The "calc" genetic programming requires a target function, like a polynomial,
 * and the evolution evolves the abstract syntax tree of an expression which
 * tries to match the target polynomial.
 * </p>
 * 
 * <p>
 * The "calc" example essentially just implements a {@link jmona.Factory} for
 * function nodes and terminal nodes (and implements the Nodes themselves), a
 * fitness function, and a Tree evaluator. The rest of the evolution is simply
 * configured in the {@code
 * src/test/resources/jmona/example/calc/CalcEvolutionTester-context.xml} Spring
 * XML configuration file.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @see jmona.example.calc.factories
 * @see jmona.example.calc.functions
 * @see jmona.example.calc.nodes
 * @see jmona.example.calc.operations
 */
package jmona.example.calc;

