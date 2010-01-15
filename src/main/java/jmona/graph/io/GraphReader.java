/**
 * GraphReader.java
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jmona.MappingException;
import jmona.MetricException;
import jmona.functional.Functional;
import jmona.functional.Range;
import jmona.impl.ArrayUtils;
import jmona.impl.metrics.EuclideanVectorMetric;
import jmona.io.LineReader;
import jmona.io.SplitOnWhitespace;
import joptsimple.internal.Strings;

import org.apache.log4j.Logger;

/**
 * Class which reads an adjacency matrix from a TSPLIB graph file.
 * 
 * The TSPLIB website can be found at <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/"
 * >http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/</a>. This website
 * specified a standard file format for traveling salesman problem graph files.
 * A description of the file format can be found at <a
 * href="http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS"
 * >http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/DOC.PS</a>. It's a
 * little bit outdated, but there is a moderately sized library of sample
 * graphs.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class GraphReader {

  /** The comment identifier. */
  public static final String COMMENT = "COMMENT:";
  /** The dimension identifier. */
  public static final String DIMENSION = "DIMENSION:";
  /** The edge weight type identifier. */
  public static final String EDGE_WEIGHT_TYPE = "EDGE_WEIGHT_TYPE:";
  /** The end-of-file identifier. */
  public static final String EOF = "EOF";
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(GraphReader.class);
  /** The Euclidean metric for <em><b>R</b><sup>n</sup></em>. */
  private static final EuclideanVectorMetric<Double> METRIC = new EuclideanVectorMetric<Double>();
  /** The graph name identifier. */
  public static final String NAME = "NAME:";
  /** The vertex coordinate section identifier. */
  public static final String NODE_COORD_SECTION = "NODE_COORD_SECTION";
  /** The graph type identifier. */
  public static final String TYPE = "TYPE:";

  /**
   * Gets the adjacency matrix representing distances between vertices with
   * coordinates specified by the input List.
   * 
   * @param coordinatesList
   *          The List of coordinate vectors of each vertex.
   * @return The adjacency matrix representing distances between vertices with
   *         coordinates specified by the input List.
   * @throws MetricException
   *           If there is a problem determining the distance between some pair
   *           of vertices.
   */
  protected static double[][] distances(final List<List<Double>> coordinatesList)
      throws MetricException {

    // get the number of vertices in the graph
    final int numberOfVertices = coordinatesList.size();

    // create a square two-dimensional array to be initialized with distances
    // between each vertex
    final double[][] result = new double[numberOfVertices][numberOfVertices];

    // iterate over each vertex index, for the source vertex
    for (final int i : new Range(numberOfVertices)) {

      // get the coordinates of vertex i
      final List<Double> coordinates1 = coordinatesList.get(i);

      // iterate over each vertex index again, for the target vertex
      for (final int j : new Range(numberOfVertices)) {

        // get the coordinates of vertex j
        final List<Double> coordinates2 = coordinatesList.get(j);

        // get the distance from vertex i to vertex j
        result[i][j] = METRIC.measure(coordinates1, coordinates2);
      }
    }

    return result;
  }

  /**
   * Gets the adjacency matrix, representing edge weights between vertices, of a
   * graph from the specified File.
   * 
   * @param file
   *          The file from which to read the graph.
   * @return The adjacency matrix, representing edge weights between vertices,
   *         of a graph from the specified File.
   * @throws IOException
   *           If the specified File does not exist, or if there is a problem
   *           while reading the specified File.
   * @throws MappingException
   *           If there is a problem splitting the lines on whitespace.
   * @throws MetricException
   *           If there is a problem determining the distance between some pair
   *           of vertices.
   */
  public static double[][] adjacencyMatrix(final File file) throws IOException,
      MappingException, MetricException {

    LOG.debug("Reading from file " + file);
    
    // get all lines, split on whitespace
    final List<String[]> allLines = Functional.map(new SplitOnWhitespace(),
        LineReader.readLines(file));

    // create a list of coordinates for each of the vertices in the graph
    final List<List<Double>> coordinatesList = new Vector<List<Double>>();

    // iterate over each line in the file
    boolean nodeCoordSection = false;
    for (final String[] line : allLines) {

      // get the first word in the line, for determining what metadata is on
      // this line, if any
      final String key = line[0];

      // get the remaining words in the line
      final String[] remaining = (String[]) ArrayUtils.slice(line, 1,
          line.length);

      // if we have reached the "node coordinates" section of the file
      if (nodeCoordSection) {

        // if we are at the end of the file, break
        if (key.equals(EOF)) {
          break;
        }

        // create a vector to contain the coordinate components of the current
        // vertex
        final List<Double> coordinates = new Vector<Double>();

        // iterate over each coordinate component in the line
        for (final String coordinate : remaining) {

          // add the current coordinate component to the vector
          coordinates.add(Double.valueOf(coordinate));
        }

        // add the coordinates of this vertex to the list of all vertex coords
        coordinatesList.add(coordinates);

      } else {

        // join the remaining words on the line
        final String remainingString = Strings.join(remaining, " ");

        if (key.equals(NAME)) {
          LOG.debug("Graph name: " + remainingString);
        } else if (key.equals(TYPE)) {
          LOG.debug("Graph type: " + remainingString);
        } else if (key.equals(COMMENT)) {
          LOG.debug("Graph comment: " + remainingString);
        } else if (key.equals(EDGE_WEIGHT_TYPE)) {
          LOG.debug("Graph edge weight type: " + remainingString);
        } else if (key.equals(DIMENSION)) {
          LOG.debug("Number of nodes: " + remainingString);
        } else if (key.equals(NODE_COORD_SECTION)) {
          LOG.debug("Node coordinate section begins");
          nodeCoordSection = true;
        }
      }

    }

    return distances(coordinatesList);
  }

  /** Instantiation disallowed except by subclasses. */
  protected GraphReader() {
    // intentionally unimplemented
  }
}
