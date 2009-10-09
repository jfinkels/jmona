/**
 * InitializationException.java
 */
package jmona;

/**
 * This Exception is thrown when there is a problem during initialization of
 * Individuals or Populations.
 * 
 * @author jeff
 */
public class InitializationException extends Exception {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 1295080406489730581L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public InitializationException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public InitializationException(final String message) {
    super(message);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public InitializationException(final Throwable cause) {
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
  public InitializationException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
