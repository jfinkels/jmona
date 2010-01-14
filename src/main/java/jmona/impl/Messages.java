/**
 * Messages.java
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
package jmona.impl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A utility class for getting localized Strings from a resource bundle.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public final class Messages {
  /** The name of the resource bundle to get. */
  private static final String BUNDLE_NAME = "jmona.impl.messages"; //$NON-NLS-1$
  /** The exclamation point character. */
  public static final char EXCLAMATION_POINT = '!';
  /** The resource bundle containing the localized Strings to get. */
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
      .getBundle(BUNDLE_NAME);

  /**
   * Get the localized String with the specified key.
   * 
   * @param key
   *          The key of the localized String to get.
   * @return The localized String.
   */
  public static String getString(final String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (final MissingResourceException exception) {
      return EXCLAMATION_POINT + key + EXCLAMATION_POINT;
    }
  }

  /** Instantiation disallowed. */
  private Messages() {
    // intentionally unimplemented
  }
}
