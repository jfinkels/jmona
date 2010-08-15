/**
 * ResidueType.java
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

/**
 * A type of a residue in a protein or a peptide chain.
 * 
 * For more information on standard three-letter and one-letter abbreviations
 * for amino acids, see <a href="http://en.wikipedia.org/wiki/Amino_acid#Table_of_standard_amino_acid_abbreviations_and_properties"
 * >the Wikipedia article on amino acids</a>.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public enum ResidueType {
  ALANINE("ALA", 'A'), ARGININE("ARG", 'R'), ASPARAGINE("ASN", 'N'), ASPARTIC_ACID(
      "ASP", 'D'), CYSTEINE("CYS", 'C'), GLUTAMIC_ACID("GLU", 'E'), GLUTAMINE(
      "GLN", 'Q'), GLYCINE("GLY", 'G'), HISTIDINE("HIS", 'H'), ISOLEUCINE(
      "ILE", 'I'), LEUCINE("LEU", 'L'), LYSINE("LYS", 'K'), METHIONINE("MET",
      'M'), PHENYLALANINE("PHE", 'F'), PROLINE("PRO", 'P'), SERINE("SER", 'S'), THREONINE(
      "THR", 'T'), TRYPTOPHAN("TRP", 'W'), TYROSINE("TYR", 'Y'), VALINE("VAL",
      'V'),

  SELENOCYSTEINE("SEC", 'U'), PYRROLYSINE("PYL", 'O'),

  /** A placeholder for either asparagine or aspartic acid. */
  ASPAR_("ASX", 'B'),
  /** A placeholder for either glutamine or glutamic acid. */
  GLUTAM_("GLX", 'Z'),
  /** A placeholder for either leucine or isoleucine. */
  _LEUCINE("XLE", 'J'),
  /** A placeholder for an unknown or otherwise unspecified residue. */
  UNKNOWN("XAA", 'X');

  public static final ResidueType[] PROTEINOGENIC_RESIDUE_TYPES = { ALANINE,
      ARGININE, ASPARAGINE, ASPARTIC_ACID, CYSTEINE, GLUTAMIC_ACID, GLUTAMINE,
      GLYCINE, HISTIDINE, ISOLEUCINE, LEUCINE, LYSINE, METHIONINE,
      PHENYLALANINE, PROLINE, SERINE, THREONINE, TRYPTOPHAN, TYROSINE, VALINE };

  /** The standard three-letter abbreviation of this residue type. */
  private final String threeLetterAbbreviation;
  /** The standard one-letter abbreviation of this residue type. */
  private final char oneLetterAbbreviation;

  /**
   * Instantiates this type of Residue with the specified three-letter and
   * one-letter abbreviations (they should both match the standard known
   * abbreviations).
   * 
   * @param threeLetterAbbreviation
   *          The three-letter abbreviation of this type of Residue.
   * @param oneLetterAbbreviation
   *          The one-letter abbreviation of this type of Residue.
   */
  private ResidueType(final String threeLetterAbbreviation,
      final char oneLetterAbbreviation) {
    this.threeLetterAbbreviation = threeLetterAbbreviation;
    this.oneLetterAbbreviation = oneLetterAbbreviation;
  }

  /**
   * Gets the three-letter abbreviation of this Residue.
   * 
   * @return The three-letter abbreviation of this Residue.
   */
  public String threeLetterAbbreviation() {
    return this.threeLetterAbbreviation;
  }

  /**
   * Gets the one-letter abbreviation of this Residue.
   * 
   * @return The one-letter abbreviation of this Residue.
   */
  public char oneLetterAbbreviation() {
    return this.oneLetterAbbreviation;
  }

}
