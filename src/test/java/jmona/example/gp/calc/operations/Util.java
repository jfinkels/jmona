/**
 * Util.java
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
package jmona.example.gp.calc.operations;

import jmona.example.gp.calc.functions.ConstantFunction;

/**
 * Utility class for FunctionOperationTester classes.
 * 
 * @author jfinkels
 */
public class Util {
  /** The ConstantFunction which always returns 0.5. */
  public static final ConstantFunction<Double, Double> HALF_FUNCTION = new ConstantFunction<Double, Double>(
      0.5);
  /** The ConstantFunction which always returns 1.0. */
  public static final ConstantFunction<Double, Double> ONE_FUNCTION = new ConstantFunction<Double, Double>(
      1.0);
  /** The ConstantFunction which always returns 2.0. */
  public static final ConstantFunction<Double, Double> TWO_FUNCTION = new ConstantFunction<Double, Double>(
      2.0);
  /** The ConstantFunction which always returns 0.0. */
  public static final ConstantFunction<Double, Double> ZERO_FUNCTION = new ConstantFunction<Double, Double>(
      0.0);
  
  /** Instantiate disallowed. */
  protected Util() {
    // intentionally unimplemented
  }
}
