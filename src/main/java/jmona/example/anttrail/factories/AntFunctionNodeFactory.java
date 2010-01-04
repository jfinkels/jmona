/**
 * AntFunctionNodeFactory.java
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
package jmona.example.anttrail.factories;

import jmona.Factory;
import jmona.example.anttrail.nodes.DoEachNode;
import jmona.example.anttrail.nodes.IfFoodAheadNode;
import jmona.gp.FunctionNode;
import jmona.impl.Util;

/**
 * A factory for creating function nodes for ant trail evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntFunctionNodeFactory extends AntNodeFactory implements
    Factory<FunctionNode> {

  /** The default maximum arity of a DoEachNode. */
  public static final int DEFAULT_MAX_ARITY = 3;
  /** The default minimum arity of a DoEachNode. */
  public static final int DEFAULT_MIN_ARITY = 2;
  /** The maximum arity of a DoEachNode. */
  private int maxArity = DEFAULT_MAX_ARITY;
  /** The minimum arity of a DoEachNode. */
  private int minArity = DEFAULT_MIN_ARITY;

  /**
   * Create a FunctionNode chosen with uniformly random probability from the set
   * containing IfFoodAheadNode and DoEachNode.
   * 
   * If a DoEachNode is created, it will have arity between {@link #minArity}
   * and {@link #maxArity}.
   * 
   * @return A DoEachNode or an IfFoodAheadNode.
   * @see jmona.Factory#createObject()
   */
  @Override
  public FunctionNode createObject() {
    FunctionNode result = null;

    if (Util.RANDOM.nextBoolean()) {
      result = new IfFoodAheadNode(this.ant());
    } else {
      result = new DoEachNode(this.ant(), Util.randomBetween(this.minArity,
          this.maxArity));
    }

    return result;
  }

  /**
   * Sets the maximum + 1 arity of a DoEachNode.
   * 
   * @param newMaxArity
   *          The maximum + 1 arity of a DoEachNode.
   */
  public void setMaxArity(final int newMaxArity) {
    this.maxArity = newMaxArity;
  }

  /**
   * Sets the minimum arity of a DoEachNode.
   * 
   * @param newMinArity
   *          The minimum arity of a DoEachNode.
   */
  public void setMinArity(final int newMinArity) {
    this.minArity = newMinArity;
  }
}
