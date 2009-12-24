/**
 * TSPGraphReader.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import jmona.example.ga.tsp.AdjacencyMatrixGraph;
import jmona.example.ga.tsp.DirectedGraph;

import org.apache.log4j.Logger;

/**
 * A class which reads graphs from files.
 * 
 * @author jfinkels
 */
public class TSPGraphReader {

  /** A regular expression matching any whitespace character. */
  public static final String WHITESPACE = "\\s+";
  
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TSPGraphReader.class);

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
   */
  public static DirectedGraph<Integer, Double> fromFile(final File file)
      throws IOException {
    LOG.debug("Reading from file: " + file);
    
    // instantiate a list for all lines
    final List<String[]> allLines = allLines(file);

    LOG.debug("Number of lines read: " + allLines.size());
    
    // get the number of vertices in the graph
    final int numberOfVertices = allLines.size();

    // initialize an adjacency matrix (assume the matrix is square)
    final double[][] adjacencyMatrix = new double[numberOfVertices][numberOfVertices];

    // iterate over each line read from the file
    int i = 0;
    int j = 0;
    for (final String[] weights : allLines) {

      LOG.debug("weights: " + weights[0] + ", " + weights[1] + ", " + weights[2]);
      
      // iterate over each edge weight in the line
      j = 0;
      for (final String weight : weights) {

        LOG.debug("weight = " + weight);
        
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

  /**
   * Read each line in the specified file and split on whitespace.
   * 
   * @param file
   *          The file from which to read.
   * @return A list of each line of the file, split on whitespace.
   * @throws IOException
   *           If there is a problem reading from the specified File.
   */
  protected static List<String[]> allLines(final File file) throws IOException {
    BufferedReader reader = null;
    final List<String[]> result = new Vector<String[]>();

    LOG.debug("Reading from file: " + file);
    
    try {
      // create a file reader
      reader = new BufferedReader(new FileReader(file));

      LOG.debug("Created reader: " + reader);
      
      // add each line, split on whitespace, to the result list
      String currentLine = reader.readLine();
      while (currentLine != null) {
        LOG.debug("Current line: " + currentLine);
        if (!currentLine.isEmpty()) {
          result.add(currentLine.trim().split(WHITESPACE));
        }
        currentLine = reader.readLine();
      }
    } finally {
      // close the file
      reader.close();
    }

    return result;
  }

  /** Instantiation is disallowed. */
  protected TSPGraphReader() {
    // intentionally unimplemented
  }
}
