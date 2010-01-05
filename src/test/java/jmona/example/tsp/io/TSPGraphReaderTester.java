/**
 * TSPGraphReaderTester.java
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
package jmona.example.tsp.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import jmona.MappingException;
import jmona.example.tsp.DirectedGraph;
import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the TSPGraphReader class.
 * 
 * @author Jeffrey Finkelstein
 */
public class TSPGraphReaderTester {

  /** The number of vertices in the test file. */
  public static final int NUM_VERTICES = 2;
  /** The location of the test file. */
  public static final String TESTFILE = "src/test/resources/jmona/example/tsp/io/test-graph.txt";
  /** The location of the test file with empty lines in it. */
  public static final String TESTFILE_EMPTY_LINES = "src/test/resources/jmona/example/tsp/io/test-graph.txt";
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.tsp.io.TSPGraphReader#fromFile(java.io.File)}.
   */
  @Test
  public void testFromFile() {
    new TSPGraphReader();

    DirectedGraph<Integer, Double> graph = null;
    try {
      graph = TSPGraphReader.fromFile(new File(TESTFILE));
    } catch (final IOException exception) {
      Util.fail(exception);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    for (final int i : new Range(NUM_VERTICES)) {
      for (final int j : new Range(NUM_VERTICES)) {
        assertEquals(i + j, graph.edgeBetween(i, j), ZERO_DELTA);
      }
    }

    try {
      graph = TSPGraphReader.fromFile(new File(TESTFILE_EMPTY_LINES));
    } catch (final IOException exception) {
      Util.fail(exception);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    for (final int i : new Range(NUM_VERTICES)) {
      for (final int j : new Range(NUM_VERTICES)) {
        assertEquals(i + j, graph.edgeBetween(i, j), ZERO_DELTA);
      }
    }
  }

}
