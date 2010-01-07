/**
 * TSPGraphReader.java
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

import java.io.File;
import java.io.IOException;
import java.util.List;

import jmona.MappingException;
import jmona.functional.Functional;
import jmona.graph.AdjacencyMatrixGraph;
import jmona.graph.DirectedGraph;
import jmona.io.LineReader;
import jmona.io.SplitOnWhitespace;

/**
 * A class which reads traveling salesman problem graphs from files.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
// TODO use the canonical TSP graph form
public class TSPGraphReader {

  /**
   * Generate a DirectedGraph from the adjacency matrix described in the
   * specified File.
   * 
   * @param file
   *          The file containing the adjacency matrix to read.
   * @return The DirectedGraph representing the adjacency matrix described in
   *         the specified File.
   * @throws IOException
   *           If the specified File does not exist, or if there is a problem
   *           while reading the specified File.
   * @throws MappingException
   *           If there is a problem splitting the lines on whitespace.
   */
  public static DirectedGraph<Integer, Double> fromFile(final File file)
      throws IOException, MappingException {

    // get all lines, split on whitespace
    final List<String[]> allLines = Functional.map(new SplitOnWhitespace(),
        LineReader.readLines(file));

    // get the number of vertices in the graph
    final int numberOfVertices = allLines.size();

    // initialize an adjacency matrix (assume the matrix is square)
    final double[][] adjacencyMatrix = new double[numberOfVertices][numberOfVertices];

    // iterate over each line read from the file
    int i = 0;
    int j = 0;
    for (final String[] weights : allLines) {

      // iterate over each edge weight in the line
      j = 0;
      for (final String weight : weights) {

        // put that value in the adjacency matrix
        adjacencyMatrix[i][j] = Double.valueOf(weight);

        // increment the column vertex number
        j += 1;
      }

      // increment the row vertex number
      i += 1;
    }

    // create the graph object from the adjacency matrix
    final DirectedGraph<Integer, Double> result = new AdjacencyMatrixGraph(
        adjacencyMatrix);

    return result;
  }

  /** Instantiation is disallowed. */
  protected TSPGraphReader() {
    // intentionally unimplemented
  }
}
