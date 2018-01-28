package homework5;

/**
 * A class that extends the functionality from DoubleList by way of adding
 * a method that adds new elements, which are automatically sorted.
 * @author Anton G Neuhold Jr
 *
 * @param <T>
 */
public class DoubleOrderedList<T> extends DoubleList<T> implements OrderedListADT<T> {
  
  public DoubleOrderedList() {
    super();
  }
  
  /**
   * Adds an element to the ordered list using the comparable interface.
   * 
   * @throws NonComparableElementException if the element does not implement
   * the comparable interface.
   */
  public void add(T element) throws NonComparableElementException {
    if (!(element instanceof Comparable)) {
      throw new NonComparableElementException("" + this.getClass());
    } 
    
    if (isEmpty()) {
      first()
    }
  }
}
