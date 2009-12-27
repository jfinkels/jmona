/**
 * ExamplePostProcessor.java
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

import jmona.EvolutionContext;
import jmona.ProcessingException;
import jmona.impl.PeriodicPostProcessor;

/**
 * @author jfinkels
 */
public class ExamplePostProcessor extends
    PeriodicPostProcessor<ExampleIndividual> {

  private int count = 0;

  /*
   * (non-Javadoc)
   * 
   * @see
   * jmona.impl.PeriodicPostProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(
      final EvolutionContext<ExampleIndividual> evolutionContext) {
    this.count += 1;
  }

  public int count() {
    return this.count;
  }

}
