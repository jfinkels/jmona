/**
 * AlreadyVisitedException.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.aco;

/**
 * This Exception is thrown when an ant attempts to move to a city which it has
 * already visited on its tour.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AlreadyVisitedException extends RuntimeException {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -6969280694713793422L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public AlreadyVisitedException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public AlreadyVisitedException(final String message) {
    super(message);
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
  public AlreadyVisitedException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public AlreadyVisitedException(final Throwable cause) {
    super(cause);
  }

}
