/**
 * AllTest.java
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
package jmona.ga.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author Jeffrey Finkelstein
 */
@RunWith(Suite.class)
@SuiteClasses({ AbstractListCrossoverFunctionTester.class,
    BitFactoryTester.class, GAEvolutionContextTester.class,
    GAFitnessFunctionTester.class, MappingFitnessFunctionTester.class,
    OnePointCrossoverFunctionTester.class,
    TwoPointCrossoverFunctionTester.class })
public class AllTest {

}
