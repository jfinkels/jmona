/**
 * MonaIndividualTester.java
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
package jmona.example.monalisa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;

import org.junit.Test;

/**
 * Test class for the MonaIndividual class.
 * 
 * @author jeff
 */
public class MonaIndividualTester {

  /**
   * Test method for {@link jmona.example.monalisa.MonaIndividual#copy()}.
   */
  @Test
  public void testCopy() {
    final MonaIndividual original = new MonaIndividual();
    
    final Polygon polygon = new Polygon();
    final Color color = new Color(0); 
    
    original.gene().put(polygon, color);

    final MonaIndividual clone = original.copy();

    assertNotSame(original, clone);
    assertNotSame(original.gene(), clone.gene());
    
    assertTrue(original.gene().containsKey(polygon));
    assertTrue(original.gene().containsValue(color));
    
    assertEquals(original.gene().size(), clone.gene().size());
    
    assertFalse(clone.gene().containsKey(polygon));
    assertTrue(clone.gene().containsValue(color));
    
  }

}
