/**
 * MapUtilsTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Test class for the MapUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class MapUtilsTester {

  /**
   * Test method for {@link jmona.impl.MapUtils#firstValue(java.util.Map)}.
   */
  @Test
  public void testFirstValue() {
    final Map<Object, Object> map = new HashMap<Object, Object>();

    final Object key = new Object();
    final Object value = new Object();

    map.put(key, value);

    assertEquals(value, MapUtils.firstValue(map));
  }

  /**
   * Test method for {@link jmona.impl.MapUtils#MapUtils()}.
   */
  @Test
  public void testMapUtils() {
    new MapUtils();
  }
}
