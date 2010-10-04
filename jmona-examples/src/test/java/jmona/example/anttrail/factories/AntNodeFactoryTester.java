/**
 * AntNodeFactoryTester.java
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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Trail;

import org.junit.Test;

/**
 * Test class for the AntNodeFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntNodeFactoryTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.factories.AntNodeFactory#ant()}.
   */
  @Test
  public void testAnt() {
    final AntNodeFactory factory = new AntNodeFactory() {
      // intentionally unimplemented
    };

    assertNull(factory.ant());

    final Ant ant = new DefaultAnt(new Trail(new boolean[2][2]));

    factory.setAnt(ant);

    assertSame(ant, factory.ant());
  }

}
