/**
 * DistanceFitnessFunction.java
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
package jmona.impl.fitness;

/**
 * Returns the Euclidean norm of a number, which is simply the distance of that
 * number from zero (or just the value of that number as a double).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class NormFitnessFunction<N extends Number> extends
    MinimizingFitnessFunction<N> {

  /** The fitness function which measures Doubles. */
  public static final NormFitnessFunction<Double> DOUBLE_FITNESS_FUNCTION = new NormFitnessFunction<Double>();
  /** The fitness function which measures Longs. */
  public static final NormFitnessFunction<Long> LONG_FITNESS_FUNCTION = new NormFitnessFunction<Long>();
  /** The fitness function which measures Floats. */
  public static final NormFitnessFunction<Float> FLOAT_FITNESS_FUNCTION = new NormFitnessFunction<Float>();
  /** The fitness function which measures Integers. */
  public static final NormFitnessFunction<Integer> INTEGER_FITNESS_FUNCTION = new NormFitnessFunction<Integer>();

  /**
   * Returns the value of the specified number as a double.
   * 
   * @param number
   *          The number to measure.
   * @return The value of the specified number as a double.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final N number) {
    return number.doubleValue();
  }

}
