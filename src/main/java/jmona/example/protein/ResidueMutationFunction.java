/**
 * ResidueMutationFunction.java
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

import jmona.MutationFunction;
import jmona.random.RandomUtils;

/**
 * Changes the type of a residue to a residue type selected randomly (with
 * uniform probability) from {@link ResidueType#PROTEINOGENIC_RESIDUE_TYPES}.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ResidueMutationFunction implements MutationFunction<Residue> {

  /**
   * Changes the type of a residue to a residue type selected randomly (with
   * uniform probability) from {@link ResidueType#PROTEINOGENIC_RESIDUE_TYPES}.
   * 
   * @param residue
   *          The residue whose type to change.
   * @see jmona.MutationFunction#mutate(java.lang.Object)
   */
  @Override
  public void mutate(final Residue residue) {
    residue
        .setType(RandomUtils.choice(ResidueType.PROTEINOGENIC_RESIDUE_TYPES));
  }

}
