/**
 * AntNodeFactory.java
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

import jmona.example.anttrail.Ant;

/**
 * A factory with access to an Ant.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public abstract class AntNodeFactory {

  /** The Ant to provide to created Nodes. */
  private Ant ant = null;

  /**
   * Gets the Ant to provide to created Nodes.
   * 
   * @return The Ant to provide to created Nodes.
   */
  public Ant ant() {
    return this.ant;
  }

  /**
   * Sets the Ant to provide to created Nodes.
   * 
   * @param newAnt
   *          The Ant to provide to created Nodes.
   */
  public void setAnt(final Ant newAnt) {
    this.ant = newAnt;
  }
}
