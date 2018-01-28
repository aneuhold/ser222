package homework5;

/**
 * A class that represents a doubly linked structure.
 * @author Anton G Neuhold Jr
 *
 * @param <T>
 */
public class DoubleList<T> implements ListADT<T> {
  protected DoubleNode<T> first;
  protected DoubleNode<T> last;
  protected int size;
  
  public DoubleList() {
    first = null;
    last = null;
    size = 0;
  }
  
  @Override
  public T removeFirst() throws EmptyCollectionException {
    if (isEmpty()) {
      throw new EmptyCollectionException("" + this.getClass());
    }
    T element = first.getElement();
    if (size == 1) {
      first = null;
      last = null;
    } else {
      first = first.getNext();
      first.setPrevious(null);
    }
    size--;
    return element;
  }

  @Override
  public T removeLast() throws EmptyCollectionException {
    if (isEmpty()) {
      throw new EmptyCollectionException("" + this.getClass());
    }
    T element = last.getElement();
    if (size == 1) {
      first = null;
      last = null;
    } else {
      last = last.getPrevious();
      last.setNext(null);
    }
    size--;
    return element;
  }

  /**  
   * Removes and returns the specified element from this list. 
   *
   * @param element the element to be removed from the list
   * @throws ElementNotFoundException if the element is not in the list
   * @throws EmptyCollectionException if the collection is empty
   */
  @Override
  public T remove(T element) throws ElementNotFoundException, EmptyCollectionException {
    DoubleNode foundNode = find(element);
    
    if (size() == 1) {
      first = last = null;
    } else if (foundNode.equals(first)) {
      first = foundNode.getNext();
      first.setPrevious(null);
    } else if (foundNode.equals(last)) {
      last = foundNode.getPrevious();
      last.setNext(null);
    } else {
      foundNode.getPrevious().setNext(foundNode.getNext());
      foundNode.getNext().setPrevious(foundNode.getPrevious());
    }
    size--;
    
    return (T) foundNode.getElement();
  }
  
  

  @Override
  public T first() throws EmptyCollectionException {
    if (size == 0) {
      throw new EmptyCollectionException("" + this.getClass());
    } else {
      return (T) first.getElement();
    }
  }

  /**
   * 
   */
  @Override
  public T last() throws EmptyCollectionException {
    if (size == 0) {
      throw new EmptyCollectionException("" + this.getClass());
    } else {
      return (T) last.getElement();
    }
  }

  @Override
  public boolean contains(T target) throws EmptyCollectionException {
    boolean result;
    try {
      find(target);
      result = true;
    } catch(ElementNotFoundException e) {
      result = false;
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  @Override
  public int size() {
    return size;
  }
  
  @Override
  public String toString() {
    String result = "";
    if (size == 0) {
      result = "empty";
    } else {
      DoubleNode current = last;
      result += current.getElement() + " ";
      while (current.getPrevious() != null) {
        current = current.getPrevious();
        result += current.getElement() + " ";
      }
    }
    return result;
  }
  
  /**
   * Searches for a given element in the list and returns the node that contains it
   * if found. If the element is not found, or the list is empty, it
   * throws an exception.
   * 
   * @param target the element to search for
   * @return DoubleNode the node that contains the target element
   * @throws ElementNotFoundException if the element is not found
   * @throws EmptyCollectionException if the list is empty
   */
  private DoubleNode<T> find(T target) throws ElementNotFoundException, EmptyCollectionException {
    if (isEmpty()) {
      throw new EmptyCollectionException("" + this.getClass());
    }
 
    boolean found = false;
    DoubleNode currentNode = first;
    
    while (!found && currentNode != null) {
      if (target.equals(currentNode.getElement())) {
        found = true;
      } else {
        currentNode = currentNode.getNext();
      }
    }
    
    if (!found) {
      throw new ElementNotFoundException("" + this.getClass());
    }
    return currentNode;
  }

}
