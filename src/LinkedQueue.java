
import java.util.Iterator;

import homework5.DoubleNode;
import homework5.EmptyCollectionException;

public class LinkedQueue<T> implements Iterable<T> {
    private int count;
    private DoubleNode<T> head, tail;

    public LinkedQueue() {
        count = 0;
        head = tail = null;
    }
 
    public void enqueue(T element) {
      DoubleNode<T> node = new DoubleNode<T>(element);
 
        if (isEmpty())
            head = node;
        else
            tail.setNext(node);
 
        tail = node;
        count++;
    }
 
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("queue");
 
        T result = head.getElement();
        head = head.getNext();
        count--;
 
        if (isEmpty())
            tail = null;
 
        return result;
    }
    
    public boolean isEmpty() {
      return (count == 0);
    }
    
    @Override
    public java.util.Iterator iterator() {
      return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T> {
      private DoubleNode<T> node = head;
      
      public boolean hasNext() {
        return node != null;
      }
      
      public T next() {
        T result;
        result = node.getElement();
        node = node.getNext();
        return result;
      }
      public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
      }
    }


}
