/**
 * WorkerAntVectorTester.java
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
package jmona.aco.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jfcommon.functional.Function;
import jfcommon.functional.Functional;
import jfcommon.functional.MappingException;
import jfcommon.functional.Range;
import jfcommon.test.TestUtils;

import org.junit.Test;

/**
 * Test class for the WorkerAntVector class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class WorkerAntVectorTester {

  /**
   * Test method for {@link jmona.aco.impl.WorkerAntVector#WorkerAntVector(int)}
   * .
   */
  @Test
  public void testWorkerAntVector() {
    final int numberOfVertices = 10;
    final WorkerAntVector vector = new WorkerAntVector(numberOfVertices);

    final Function<WorkerAnt, Integer> antToCity = new Function<WorkerAnt, Integer>() {
      @Override
      public Integer execute(final WorkerAnt ant) {
        return ant.currentVertex();
      }
    };

    List<Integer> cities = null;
    try {
      cities = Functional.map(antToCity, vector);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    }

    for (final int i : new Range(numberOfVertices)) {
      assertEquals(cities.indexOf(i), cities.lastIndexOf(i));
    }
  }

}
