/**
 * UtilTester.java
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
package jmona.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.Factory;
import jmona.InitializationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.AbstractTreeFactory;
import jmona.gp.impl.DefaultTree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTreeFactory;

import org.junit.Test;

/**
 * Test class for the testing utility class.
 * 
 * @author Jeffrey Finkelstein
 */
public class UtilTester {

  /** Test method for {@link jmona.test.Util#allNodes(Tree)}. */
  @Test
  public void testAllNodes() {
    // initialize a list of known nodes
    final List<Node> allNodes = new Vector<Node>();

    // initialize a root for the tree
    final Node root = new ExampleBinaryNode();
    final Tree tree = new DefaultTree(root);

    // add the root to the list of known nodes
    allNodes.add(root);

    List<Node> utilAllNodes = Util.allNodes(tree);

    assertEquals(allNodes.size(), utilAllNodes.size());
    for (final Node node : allNodes) {
      assertTrue(utilAllNodes.contains(node));
    }

    // initialize a left child for the tree
    final Node leftChild = new ExampleBinaryNode();
    leftChild.setParent(root);
    root.children().add(leftChild);

    // add the left child node to the list of known nodes
    allNodes.add(leftChild);

    utilAllNodes = Util.allNodes(tree);

    assertEquals(allNodes.size(), utilAllNodes.size());
    for (final Node node : allNodes) {
      assertTrue(utilAllNodes.contains(node));
    }

    // initialize a right child for the tree
    final Node rightChild = new ExampleBinaryNode();
    rightChild.setParent(root);
    root.children().add(rightChild);

    // add the right child node to the list of known nodes
    allNodes.add(rightChild);

    utilAllNodes = Util.allNodes(tree);

    assertEquals(allNodes.size(), utilAllNodes.size());
    for (final Node node : allNodes) {
      assertTrue(utilAllNodes.contains(node));
    }

  }

  /**
   * Test method for {@link jmona.test.Util#areEqual(List, List)}.
   */
  @Test
  public void testAreEqual() {
    final List<Object> list1 = new Vector<Object>();
    final List<Object> list2 = new Vector<Object>();
    final List<Object> list3 = new Vector<Object>();

    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object object3 = new Object();

    list1.add(object1);
    list1.add(object2);
    list1.add(object3);

    list2.add(object1);
    list2.add(object3);
    list2.add(object2);

    list3.add(object1);
    list3.add(object2);

    assertTrue(Util.areEqual(list1, list1));
    assertTrue(Util.areEqual(list2, list2));
    assertTrue(Util.areEqual(list3, list3));

    assertFalse(Util.areEqual(list1, list2));
    assertFalse(Util.areEqual(list2, list1));
    assertFalse(Util.areEqual(list1, list3));
    assertFalse(Util.areEqual(list3, list1));
    assertFalse(Util.areEqual(list2, list3));
    assertFalse(Util.areEqual(list3, list2));

  }

  /**
   * Test method for {@link jmona.test.Util#countNodes(jmona.gp.Tree)}.
   */
  @Test
  public void testCountNodes() {
    final Factory<Tree> factory = new ExampleTreeFactory();

    Tree tree = null;
    try {
      tree = factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    final double epsilon = 0;
    assertEquals(Math.pow(2, AbstractTreeFactory.DEFAULT_MAX_DEPTH) - 1, Util
        .countNodes(tree), epsilon);
  }

  /**
   * Test method for {@link jmona.test.Util#fail(java.lang.Throwable)}.
   */
  @Test
  public void testFail() {
    try {
      Util.fail(new Exception());
    } catch (final AssertionError error) {
      assertTrue(error instanceof AssertionError);
    }
  }

  /** Test method for {@link jmona.test.Util#shouldHaveThrownException()}. */
  @Test
  public void testShouldHaveThrownException() {
    try {
      Util.shouldHaveThrownException();
    } catch (final AssertionError error) {
      assertTrue(error instanceof AssertionError);
    }
  }

}
