/**
 * CalcTerminalNodeFactory.java
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
package jmona.example.calc.factories;

import jmona.Factory;
import jmona.example.calc.nodes.NumberNode;
import jmona.example.calc.nodes.VariableNode;
import jmona.gp.TerminalNode;
import jmona.impl.Util;

/**
 * A TerminalNodeFactory for the Calc example, which creates either
 * {@link VariableNode} or {@link NumberNode} objects.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class CalcTerminalNodeFactory implements Factory<TerminalNode> {

  /** The default maximum value for a NumberNode. */
  public static final int DEFAULT_MAX_VALUE = 2;
  /** The default minimum value for a NumberNode. */
  public static final int DEFAULT_MIN_VALUE = 1;

  /** The maximum value for a NumberNode. */
  private int maxValue = DEFAULT_MAX_VALUE;
  /** The minimum value for a NumberNode. */
  private int minValue = DEFAULT_MIN_VALUE;

  /**
   * Create a TerminalNode of type {@link VariableNode} or {@link NumberNode}.
   * 
   * @return Either a {@code VariableNode} or a {@code NumberNode}.
   * @see jmona.Factory#createObject()
   */
  @Override
  public TerminalNode createObject() {
    TerminalNode result = null;

    if (Util.RANDOM.nextBoolean()) {
      result = new VariableNode();
    } else {
      final int difference = this.maxValue - this.minValue;
      result = new NumberNode((double) (this.minValue + Util.RANDOM
          .nextInt(difference)));
    }

    return result;
  }

  /**
   * Set the maximum value for a NumberNode.
   * 
   * @param newMaxValue
   *          The maximum value for a NumberNode.
   */
  public void setMaxValue(final int newMaxValue) {
    this.maxValue = newMaxValue;
  }

  /**
   * Set the minimum value for a NumberNode.
   * 
   * @param newMinValue
   *          The minimum value for a NumberNode.
   */
  public void setMinValue(final int newMinValue) {
    this.minValue = newMinValue;
  }

}
