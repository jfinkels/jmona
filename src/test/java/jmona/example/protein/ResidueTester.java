/**
 * ResidueTester.java
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
package jmona.example.protein;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for the Residue class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ResidueTester {

  /**
   * Test method for
   * {@link jmona.example.protein.Residue#Residue(jmona.example.protein.ResidueType)}
   * .
   */
  @Test
  public void testResidue() {
    final Residue residue = new Residue(ResidueType.ALANINE);
  }

  /**
   * Test method for {@link jmona.example.protein.Residue#type()}.
   */
  @Test
  public void testGetType() {
    final Residue residue = new Residue(ResidueType.ALANINE);
    assertSame(ResidueType.ALANINE, residue.type());
  }

  /**
   * Test method for
   * {@link jmona.example.protein.Residue#setType(jmona.example.protein.ResidueType)}
   * .
   */
  @Test
  public void testSetType() {
    final Residue residue = new Residue(ResidueType.ALANINE);
    residue.setType(ResidueType.GLUTAMINE);
    assertNotSame(ResidueType.ALANINE, residue.type());
    assertSame(ResidueType.GLUTAMINE, residue.type());
  }

  /**
   * Test method for {@link jmona.example.protein.Residue#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final Residue residue = new Residue(ResidueType.ALANINE);
    final Residue clone = residue.deepCopy();

    assertNotSame(residue, clone);
    assertSame(residue.type(), clone.type());
  }

}
