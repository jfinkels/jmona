/**
 * DeepCopyable.java
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

import jmona.exceptions.CopyingException;

/**
 * A class which can be deep copied.
 * 
 * @param <T>
 *          The type of object which can be deep copied.
 * @author jfinkels
 */
public interface DeepCopyable<T> {
  /**
   * Return a new instance of this class, recursively performing a deep copy on
   * all of the fields of this object as well.
   * 
   * Implementing classes should ensure that no object referenced from this
   * object is also referenced from the object which is returned by this method.
   * 
   * Implementing classes should alter the signature of this method so that it
   * returns an Object of the same class in which the method is implemented.
   * 
   * This could potentially cause complications and unexpected behavior in the
   * case of, for example, circular references among objects.
   * 
   * For more information, see <a
   * href="http://en.wikipedia.org/wiki/Object_copy#Copying_in_Java">the
   * Wikipedia article on deep copying</a>.
   * 
   * @return A deep copy of this object.
   * @throws CopyingException
   *           If there is a problem copying this object.
   */
  T deepCopy() throws CopyingException;
}
