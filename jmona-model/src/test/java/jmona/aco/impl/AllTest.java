/**
 * AllTest.java
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
package jmona.aco.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all tests in this package.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
@RunWith(Suite.class)
@SuiteClasses({ AbstractPheromoneUpdateStrategyTester.class,
    AntColonyEvolutionContextTester.class, AntCycleStrategyTester.class,
    AntDensityStrategyTester.class, AntQuantityStrategyTester.class,
    BestAntLoggingProcessorTester.class,
    DefaultPheromoneDirectedGraphTester.class, DistanceGetterTester.class,
    PathLoggingProcessorTester.class, TourEvolutionContextTester.class,
    WorkerAntTester.class, WorkerAntVectorTester.class })
public class AllTest {
  // intentionally unimplemented
}
