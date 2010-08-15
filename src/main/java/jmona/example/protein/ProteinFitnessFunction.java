/**
 * ProteinFitnessFunction.java
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

import jmona.DeepCopyableList;
import jmona.impl.fitness.MaximizingFitnessFunction;

/**
 * Scores protein chains based on a profile motif.
 * 
 * For more information on generating a score from a test sequence, see BioJava.
 * http://www.biojava.org/wiki/BioJava:CookBook:DP:HMM
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ProteinFitnessFunction extends
    MaximizingFitnessFunction<DeepCopyableList<Residue>> {

  /**
   * @param maximumRawFitness
   */
  public ProteinFitnessFunction(double maximumRawFitness) {
    super(maximumRawFitness);
  }

//  private DP targetMotif = null;
//  
//  public void setTargetMotif(final DP newTargetMotif) {
//    this.targetMotif = newTargetMotif;
//  }
  
  /* (non-Javadoc)
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final DeepCopyableList<Residue> individual) {
    // query the specified individual against a given structural motif
    //SequenceConverter.toSymbolList(individual);
    
    return 0;
  }

}
