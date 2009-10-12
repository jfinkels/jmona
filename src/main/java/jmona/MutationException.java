/**
 * MutationException.java
 */
package jmona;

/**
 * This Exception is thrown if there is a problem during the mutation of an
 * Individual in the evolution.
 * 
 * @author jeff
 */
public class MutationException extends Exception {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -5889972880558622242L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public MutationException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public MutationException(final String message) {
    super(message);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public MutationException(final Throwable cause) {
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
  public MutationException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
