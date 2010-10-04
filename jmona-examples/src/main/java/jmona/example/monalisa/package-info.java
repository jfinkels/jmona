/**
 * package-info.java
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
/**
 * <p>
 * Provides classes for randomly generating a list of polygons with associated
 * colors, and for converting those colored polygons into an image.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.monalisa.ColoredPolygon} class provides a unit of a
 * chromosome in an image-matching evolution. These colored polygons are
 * concatenated in a list, then expressed as an image. That generated image can
 * then be compared to a target image. This is the "Mona Lisa" genetic
 * image-matching algorithm (from which the name of this project is inspired).
 * </p>
 * 
 * <p>
 * The {@code src/test/java/jmona/example/monalisa/MonaEvolutionTester.java}
 * provides an example for how to run this evolution as a genetic algorithm. The
 * corresponding Spring XML configuration file is at {@code
 * src/test/resources/jmona/example/monalisa/MonaEvolutionTester-context.xml}.
 * </p>
 * 
 * <p>
 * The {@link jmona.example.monalisa.gp} package provides a version similar to
 * (not exactly the same as) the genetic programming method for matching an
 * image <a href=
 * "http://rogeralsing.com/2008/12/07/genetic-programming-evolution-of-mona-lisa/"
 * >described by Roger Alsing</a>.
 * </p>
 * 
 * @author Jeffrey Finkelstein
 * @see jmona.example.monalisa.gp
 * @see jmona.example.monalisa.io
 */
package jmona.example.monalisa;

