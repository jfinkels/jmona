/**
 * ExampleMapping.java
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
package jmona.impl.example;

import jmona.SingleInputFunction;
import jmona.exceptions.MappingException;

/**
 * @author jfinkels
 */
public class ExampleMapping implements
    SingleInputFunction<ExampleIndividual, Double> {

  /* (non-Javadoc)
   * @see jmona.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public Double execute(final ExampleIndividual input) throws MappingException {
    return input.fitness();
  }

}