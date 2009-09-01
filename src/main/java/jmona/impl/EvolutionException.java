/**
 * EvolutionException.java
 */
package jmona.impl;

/**
 * This Exception is thrown when there is a problem during evolution.
 * 
 * @author jeff
 */
public class EvolutionException extends Exception {

  /**
   * Default generated serial version UID.
   */
  private static final long serialVersionUID = -3155931134086340567L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public EvolutionException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public EvolutionException(final String message) {
    super(message);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public EvolutionException(final Throwable cause) {
    super(cause);
  }

  /**
   * Instantiate this Exception with the specified human-readable message and
   * the specified cause.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   * @param cause
   *          The cause of this Exception.
   */
  public EvolutionException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
