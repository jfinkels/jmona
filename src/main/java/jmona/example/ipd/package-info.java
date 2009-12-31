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
 * Provides an implementation of the iterated prisoner's dilemma (IPD), as a
 * {@link jmona.game.Game}, and a {@link jmona.example.ipd.IPDStrategyFactory}
 * which generates strategies.
 * </p>
 * 
 * <p>
 * A brief description of the game can be found in the
 * {@link jmona.example.ipd.IPDGame} class.
 * </p>
 * 
 * <p>
 * The IPD game evolution has {@link jmona.example.ipd.strategy.IPDStrategy}
 * objects as individuals competing against one another. An example of the
 * configuration of the evolution can be found in the {@code
 * src/test/resources/jmona/example/ipd/IPDEvolutionTester-context.xml} Spring
 * XML configuration file.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 */
package jmona.example.ipd;

