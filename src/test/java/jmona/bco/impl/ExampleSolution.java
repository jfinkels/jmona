/**
* ExampleSolution.java
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
package jmona.bco.impl;

import java.util.ArrayList;

import jmona.CopyingException;
import jmona.bco.Solution;

/**
* @author Jeffrey Finkelstein
* @since 0.5
*/
public class ExampleSolution extends ArrayList<Integer> implements Solution<Integer> {

  /**
*
*/
  private static final long serialVersionUID = 3999837851986498653L;

  public ExampleSolution() {
    super();
  }

  /**
* @param exampleSolution
*/
  public ExampleSolution(ExampleSolution exampleSolution) {
    super(exampleSolution);
  }

  /* (non-Javadoc)
* @see jmona.bco.Solution#append(java.lang.Object)
*/
  @Override
  public void append(Integer solutionPart) {
    this.add(solutionPart);
  }

  /* (non-Javadoc)
* @see jmona.bco.Solution#last()
*/
  @Override
  public Integer last() {
    return this.get(this.size() - 1);
  }



  /* (non-Javadoc)
* @see jmona.DeepCopyable#deepCopy()
*/
  @Override
  public Solution<Integer> deepCopy() throws CopyingException {
    return new ExampleSolution(this);
  }

}

