/**
 * AntTerminalNodeFactory.java
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

import java.util.HashSet;
import java.util.Set;

import jmona.Factory;
import jmona.example.anttrail.nodes.MoveForwardNode;
import jmona.example.anttrail.nodes.TurnLeftNode;
import jmona.example.anttrail.nodes.TurnRightNode;
import jmona.gp.TerminalNode;
import jmona.impl.Util;

/**
 * A factory for terminal nodes in the ant trail evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTerminalNodeFactory extends AntNodeFactory implements
    Factory<TerminalNode> {

  /** The actions from which to choose when creating a new terminal node. */
  private enum Action {
    /** Move forward. */
    MOVE_FORWARD,
    /** Turn left. */
    TURN_LEFT,
    /** Turn right. */
    TURN_RIGHT
  }

  /** The set of possible actions representing nodes to create. */
  private Set<Action> actions = new HashSet<Action>();

  /**
   * Instantiate this factory by building the set of all possible nodes to
   * create.
   */
  public AntTerminalNodeFactory() {
    this.actions.add(Action.MOVE_FORWARD);
    this.actions.add(Action.TURN_LEFT);
    this.actions.add(Action.TURN_RIGHT);
  }

  /**
   * Create a TerminalNode chosen with uniformly random probability from the set
   * containing MoveForwardNode, TurnLeftNode, and TurnRightNode.
   * 
   * @return A MoveForwardNode, a TurnLeftNode, or a TurnRightNode.
   * @see jmona.Factory#createObject()
   */
  @Override
  public TerminalNode createObject() {

    TerminalNode result = null;

    switch (Util.randomFromCollection(this.actions)) {
    case MOVE_FORWARD:
    default:
      result = new MoveForwardNode(this.ant());
      break;
    case TURN_LEFT:
      result = new TurnLeftNode(this.ant());
      break;
    case TURN_RIGHT:
      result = new TurnRightNode(this.ant());
      break;
    }

    return result;
  }
}
