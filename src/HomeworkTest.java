import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import homework5.DoubleNode;

class ReverseIterator<T> implements Iterator<T>{
  private int modChange;
  private DoubleNode current;

  // Constructor
  public ReverseIterator(){
     modChange = modCount;
     current = tail; // Assuming tail is in the doubly linked list's variables
  }
  // Returns true if this iterator has at least one more element
  public boolean hasNext() throws ConcurrentModificationException{
     if (modChange != modCount)
       throw new ConcurrentModificationException();
     
     return (current != null);
  }
  // Returns the next element in the iteration
  public int next() throws NoSuchElementException{
     if (current == null) 
       throw new NoSuchElementException();
     int result = current.getElement();
     current = current.getPrevious();
     return result;
  }
  // Remove not supported, left out for brevity.
}
