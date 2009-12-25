/**
 * TSPGraphReaderTester.java
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
package jmona.example.ga.tsp.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import jmona.example.ga.tsp.DirectedGraph;
import jmona.example.ga.tsp.io.TSPGraphReader;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the TSPGraphReader class.
 * 
 * @author jfinkels
 */
public class TSPGraphReaderTester {

  /** The location of the test file. */
  public static final String TESTFILE = "src/test/resources/jmona/example/ga/tsp/input/test-graph.txt";
  /** The number of vertices in the test file. */
  public static final int NUM_VERTICES = 2;

  /**
   * Test method for
   * {@link jmona.example.ga.tsp.io.TSPGraphReader#fromFile(java.io.File)}.
   */
  @Test
  public void testFromFile() {
    DirectedGraph<Integer, Double> graph = null;
    try {
      graph = TSPGraphReader.fromFile(new File(TESTFILE));
    } catch (final IOException exception) {
      Util.fail(exception);
    }

    for (int i = 0; i < NUM_VERTICES; ++i) {
      for (int j = 0; j < NUM_VERTICES; ++j) {
        assertEquals(i + j, graph.edgeBetween(i, j), ZERO_DELTA);
      }
    }

  }

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

}