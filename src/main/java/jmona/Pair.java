/**
 * Pair.java
 */
package jmona;

/**
 * A pair of elements.
 * 
 * @param <S>
 *          The type of the left element.
 * @param <T>
 *          The type of the right element.
 * @author jfinke
 */
public class Pair<S, T> {
  /** The left element. */
  private final S left;
  /** The right element. */
  private final T right;

  /**
   * Instantiate this pair with the specified left and right elements.
   * 
   * @param initialLeft
   *          The left element.
   * @param initialRight
   *          The right element.
   */
  public Pair(final S initialLeft, final T initialRight) {
    this.left = initialLeft;
    this.right = initialRight;
  }

  /**
   * Get the left element.
   * 
   * @return The left element.
   */
  public S left() {
    return this.left;
  }

  /**
   * Get the right element.
   * 
   * @return The right element.
   */
  public T right() {
    return this.right;
  }
}
