/**
 * ColorFactory.java
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
package jmona.example.monalisa;

import java.awt.Color;

import jmona.Factory;
import jmona.random.RandomUtils;

/**
 * A factory which generates Colors with random red, green, blue, and alpha
 * components.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ColorFactory implements Factory<Color> {
  /** The maximum value for a red, green, blue, or alpha color value. */
  public static final int MAX_VALUE = 0xFF;

  /**
   * Create a Color with random values in its red, green, blue, and alpha
   * components.
   * 
   * @return A Color with random values in its red, green, blue, and alpha
   *         components.
   * @see jmona.Factory#createObject()
   */
  @Override
  public Color createObject() {
    final int alpha = RandomUtils.randomData().nextInt(0, MAX_VALUE);
    final int red = RandomUtils.randomData().nextInt(0, MAX_VALUE);
    final int green = RandomUtils.randomData().nextInt(0, MAX_VALUE);
    final int blue = RandomUtils.randomData().nextInt(0, MAX_VALUE);

    return new Color(alpha, red, green, blue);
  }

}
