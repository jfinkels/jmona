/**
 * Util.java
 */
package jmona.impl;

import java.util.Random;

/**
 * Utility class containing a random number generator.
 * 
 * @author jfinke
 */
public class Util {
  /** Random number generator. */
  public static final Random RANDOM = new Random();

  /** Instantiation disallowed. */
  protected Util() {
    // intentionally unimplemented
  }
}
