/**
 * GraphReaderTester.java
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
package jmona.graph.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.MappingException;
import jmona.MetricException;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Test class for the GraphReader class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class GraphReaderTester {

  /** The name of the file from which to read. */
  public static final String FILENAME = "src/test/resources/jmona/example/tsp/graphs/eil51.tsp";
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(GraphReaderTester.class);
  /** The file from which to read. */
  public static final File TESTFILE = new File(FILENAME);

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.graph.io.GraphReader#distances(java.util.List)}.
   */
  @Test
  public void testDistances() {
    final List<List<Double>> coordinatesList = new Vector<List<Double>>();
    final List<Double> three = new Vector<Double>();
    three.add(3.0);
    three.add(0.0);

    final List<Double> four = new Vector<Double>();
    four.add(0.0);
    four.add(4.0);

    coordinatesList.add(three);

    coordinatesList.add(four);

    double[][] distances = null;
    try {
      distances = GraphReader.distances(coordinatesList);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(0.0, distances[0][0], ZERO_DELTA);
    assertEquals(5.0, distances[0][1], ZERO_DELTA);
    assertEquals(5.0, distances[1][0], ZERO_DELTA);
    assertEquals(0.0, distances[1][1], ZERO_DELTA);

  }

  /**
   * Test method for {@link jmona.graph.io.GraphReader#adjacencyMatrix(java.io.File)}.
   */
  @Test
  public void testFromFile() {
    double[][] adjacencyMatrix = null;
    try {
      adjacencyMatrix = GraphReader.adjacencyMatrix(TESTFILE);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(51, adjacencyMatrix.length);

    int i = 0;
    for (final double[] vertex : adjacencyMatrix) {
      int j = 0;
      for (final double distance : vertex) {
        LOG.debug("distance from " + i + " to " + j + ": " + distance);
        if (i == j) {
          assertEquals(0, distance, ZERO_DELTA);
        }
        j += 1;
      }
      i += 1;
    }

  }

  /**
   * Test method for {@link jmona.graph.io.GraphReader#GraphReader()}.
   */
  @Test
  public void testGraphReader() {
    new GraphReader();
  }

}
