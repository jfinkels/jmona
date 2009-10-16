/**
 * BreedingException.java
 */
package jmona;

/**
 * This Exception is thrown if there is a problem breeding two Individuals.
 * 
 * @author jeff
 */
public class BreedingException extends Exception {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -2870047152646431022L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public BreedingException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public BreedingException(final String message) {
    super(message);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public BreedingException(final Throwable cause) {
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
  public BreedingException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
