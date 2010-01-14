/**
 * DeepCopyableListFactory.java
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

import jmona.DeepCopyableList;
import jmona.Factory;

/**
 * A factory which creates DeepCopyableList objects.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to create.
 * @since 0.5
 */
public abstract class DeepCopyableListFactory<E> extends ListFactorySupport<E>
    implements Factory<DeepCopyableList<E>> {

}
