/**
 * AntTrailExecutor.java
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
package jmona.example.anttrail;

import jmona.Function;
import jmona.example.anttrail.nodes.AntNode;
import jmona.gp.Tree;

import org.apache.log4j.Logger;

/**
 * A function which executes the program represented by a Tree containing
 * AntNodes and returns the amount of food that the Ant ate as a result of that
 * program.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTrailExecutor implements Function<Tree, Integer> {

  private static final transient Logger LOG = Logger
      .getLogger(AntTrailExecutor.class);

  /**
   * Gets the amount of food eaten by an Ant whose movements are defined by the
   * program described in the specified Tree (which is comprised of AntNode
   * objects).
   * 
   * @param tree
   *          The Tree representing the program which controls the movements of
   *          the DefaultAnt.
   * @return The amount of food eaten by the Ant.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Integer execute(final Tree tree) {

    // get the root of the Tree
    final AntNode root = (AntNode) tree.root();

    // execute the program specified by the tree
    root.execute();

    // get the ant which the tree controls
    final Ant ant = root.ant();

    // get the total amount of food eaten by the ant
    final int foodEaten = ant.foodEaten();

    LOG.debug("Ant ate " + foodEaten + " units of food.");

    // reset the ant to its initial state
    ant.reset();

    return foodEaten;
  }

}
