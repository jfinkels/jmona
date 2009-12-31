/**
 * package-info.java
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
/**
 * <p>
 * Provides a driver for running an evolutionary algorithm from the command
 * line.
 * </p>
 * 
 * <p>
 * This class is the executable main class when jmona is packaged as an
 * executable JAR, so running <code>java -jar jmona.jar</code> will execute the
 * {@link Main#main(String[])} method. Provide the Spring XML configuration of
 * the evolution you want to run as a command line argument, like this:
 * </p>
 * 
 * <blockquote><code>java -jar --config=/path/to/application-context.xml</code>
 * </blockquote>
 * 
 * @author jfinkels
 */
package jmona.driver;

