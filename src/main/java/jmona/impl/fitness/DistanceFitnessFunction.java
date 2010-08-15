/**
 * DistanceFitnessFunction.java
 * 
 * Copyright 2010 "Jeffrey Finkelstein"
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
 * @author "Jeffrey Finkelstein"
 * @since 0.5
 */
public class DistanceFitnessFunction<N extends Number> extends
    MinimizingFitnessFunction<N> {

  public static final DistanceFitnessFunction<Double> DOUBLE_FITNESS_FUNCTION = new DistanceFitnessFunction<Double>();
  public static final DistanceFitnessFunction<Long> LONG_FITNESS_FUNCTION = new DistanceFitnessFunction<Long>();
  public static final DistanceFitnessFunction<Float> FLOAT_FITNESS_FUNCTION = new DistanceFitnessFunction<Float>();
  public static final DistanceFitnessFunction<Integer> INTEGER_FITNESS_FUNCTION = new DistanceFitnessFunction<Integer>();

  /*
   * (non-Javadoc)
   * 
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final N individual)  {
    return individual.doubleValue();
  }

}
