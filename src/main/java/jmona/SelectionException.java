/**
 * SelectionException.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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
package jmona;

/**
 * This Exception is thrown when there is a problem during selection of an
 * Individual based on its fitness.
 *
 * @see jmona.SelectionFunction#select(java.util.Map)
 * @author jfinkels
 */
public class SelectionException extends Exception {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -3865345257168503780L;

  /**
   * Instantiate this class by calling the default constructor of the
   * superclass.
   */
  public SelectionException() {
    super();
  }

  /**
   * Instantiate this Exception with the specified human-readable message.
   * 
   * @param message
   *          A human-readable message explaining the problem.
   */
  public SelectionException(final String message) {
    super(message);
  }

  /**
   * Instantiate this Exception with the specified cause.
   * 
   * @param cause
   *          The cause of this Exception.
   */
  public SelectionException(final Throwable cause) {
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
  public SelectionException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
