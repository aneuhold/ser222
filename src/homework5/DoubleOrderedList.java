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
   * Adds an element to the ordered list using the comparable interface. The
   * list is sorted where the first is the highest value and last is the 
   * lowest value.
   * 
   * @param element the element to add
   * @throws NonComparableElementException if the element does not implement
   * the comparable interface.
   */
  public void add(T element) throws NonComparableElementException {
    if (!(element instanceof Comparable)) {
      throw new NonComparableElementException("" + this.getClass());
    }
    Comparable e = (Comparable) element;
    DoubleNode newNode = new DoubleNode(e);
    
    if (isEmpty()) {
      this.first = newNode;
      this.last = newNode;
    } else if (this.size == 1) {
      if (e.compareTo(first.getElement()) >= 0) {
        newNode.setNext(last);
        first = newNode;
        last.setPrevious(newNode);
      } else {
        last = newNode;
        last.setPrevious(first);
        first.setNext(last);
      }
    } else {
      if (e.compareTo(first.getElement()) >= 0) {
        newNode.setNext(first);
        first.setPrevious(newNode);
        first = newNode;
      } else if (e.compareTo(last.getElement()) < 0) {
        newNode.setPrevious(last);
        last.setNext(newNode);
        last = newNode;
      } else {
        DoubleNode scan = first.getNext();
        Boolean placed = false;
        while (scan != null && !placed) {
          if (e.compareTo(scan.getElement()) >= 0) {
            newNode.setPrevious(scan.getPrevious());
            scan.getPrevious().setNext(newNode);
            scan.setPrevious(newNode);
            newNode.setNext(scan);
            placed = true;
          }
          scan = scan.getNext();
        }
      }      
    }
    size++;
  }
}
