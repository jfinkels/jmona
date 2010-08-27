/**
 * ExampleBadMapping.java
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
package jmona.impl.example;

import jfcommon.functional.Function;
import jfcommon.functional.MappingException;

/**
 * A mapping which always throws an Exception.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ExampleBadMapping implements Function<Double, Double> {

  /**
   * Always throws a MappingException.
   * 
   * @param input
   *          This parameter is ignored.
   * @return Never returns.
   * @throws MappingException
   *           Always throws this Exception.
   * @see jfcommon.functional.Function#execute(java.lang.Object)
   */
  @Override
  public Double execute(final Double input) throws MappingException {
    throw new MappingException();
  }

}
