package homework4;


/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it. Most linear node referenced from Java Software
 * Structures 4th Edition.
 * 
 * @author Anton Neuhold, Acuna, Joseph Chase
 * @version 1.0
 */
import java.util.NoSuchElementException;
    
public class NeuholdDeque<Item> implements Deque<Item> {
  private int size;
  private LinearNode<Item> first;
  private LinearNode<Item> last;
  
  /**
   * Creates an empty Deque
   */
  public NeuholdDeque() {
    this.size = 0;
    this.first = null;
    this.last = null;
  }
  
  @Override
  public void enqueueFront(Item element) {
    if (size == 0) {
      first = new LinearNode(element);
      last = first;
    } else {
      LinearNode tmp = new LinearNode(element);
      tmp.setNext(first);
      first.setPrevious(tmp);
      first = tmp;
    }
    size++;
  }

  @Override
  public void enqueueBack(Item element) {
    if (size == 0) {
      last = new LinearNode(element);
      first = last;
    } else {
      LinearNode tmp = new LinearNode(element);
      tmp.setPrevious(last);
      last.setNext(tmp);
      last = tmp;
    }
    size++;
  }

  @Override
  public Item dequeueFront() throws NoSuchElementException {
    Item element;
    if (size == 0) {
      throw new NoSuchElementException("Empty Deque");
    } else if (size == 1) {
      element = first.getElement();
      first = null;
      last = null;
    } else {
      element = first.getElement();
      first = first.getNext();
      first.setPrevious(null);
    } 
    size--;
    return element;
  }

  @Override
  public Item dequeueBack() throws NoSuchElementException {
    Item element;
    if (size == 0) {
      throw new NoSuchElementException("Empty Deque");
    } else if (size == 1) {
      element = last.getElement();
      first = null;
      last = null;
    } else {
      element = last.getElement();
      last = last.getPrevious();
      last.setNext(null);
    } 
    size--;
    return element;
  }

  @Override
  public Item first() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Empty Deque");
    } else {
      return (Item) first.getElement();
    }
  }

  @Override
  public Item last() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Empty Deque");
    } else {
      return (Item) last.getElement();
    }
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
      LinearNode current = last;
      result += current.getElement() + " ";
      while (current.getPrevious() != null) {
        current = current.getPrevious();
        result += current.getElement() + " ";
      }
    }
    return result;
  }
  
  public class LinearNode<T> {
    private LinearNode<T> next;
    private LinearNode<T> previous;
    private T element;
    
    /**
     * Creates an empty node
     */
    public LinearNode() {
      next = null;
      previous = null;
      element = null;
    }
    
    /**
     * Creates a node with the specified element
     * @param element the chosen element
     */
    public LinearNode(T element) {
      next = null;
      previous = null;
      this.element = element;
    }
    
    public LinearNode<T> getNext() {
      return this.next;
    }
    
    public LinearNode<T> getPrevious() {
      return this.previous;
    }
    
    public T getElement() {
      return this.element;
    }
    
    public void setNext(LinearNode<T> node) {
      this.next = node;
    }
    
    public void setPrevious(LinearNode<T> node) {
      this.previous = node;
    }
    
    public void setElement(T element) {
      this.element = element;
    } 
  }
  
  /**
   * Program entry point for deque. 
   * @param args command line arguments
   */    
  public static void main(String[] args) {
      NeuholdDeque<Integer> deque = new NeuholdDeque<>();

      //standard queue behavior
      deque.enqueueBack(3);
      deque.enqueueBack(7);
      deque.enqueueBack(4);
      deque.dequeueFront();        
      deque.enqueueBack(9);
      deque.enqueueBack(8);
      deque.dequeueFront();
      System.out.println("size: " + deque.size());
      System.out.println("contents:\n" + deque.toString());   

      //deque features
      System.out.println(deque.dequeueFront());        
      deque.enqueueFront(1);
      deque.enqueueFront(11);                         
      deque.enqueueFront(3);                 
      deque.enqueueFront(5);         
      System.out.println(deque.dequeueBack());
      System.out.println(deque.dequeueBack());        
      System.out.println(deque.last());                
      deque.dequeueFront();
      deque.dequeueFront();        
      System.out.println(deque.first());        
      System.out.println("size: " + deque.size());
      System.out.println("contents:\n" + deque.toString());    
      
      // Testing empty deque conditionals
      deque.dequeueBack();
      deque.dequeueBack();
      deque.enqueueFront(13);
      deque.dequeueFront();
      System.out.println("Deque is empty: " + deque.isEmpty());
      System.out.println("Contents: " + deque.toString());
      //deque.dequeueFront();
      //deque.dequeueBack();
      //deque.first();
      //deque.last();
      
      // Printing deque from both ends
      deque.enqueueFront(2);
      deque.enqueueFront(3);
      deque.enqueueFront(4);
      deque.enqueueFront(5);
      deque.enqueueFront(6);
      deque.enqueueFront(7);
      deque.enqueueFront(8);
      while(deque.size() != 0) {
        System.out.print(deque.dequeueBack() + " ");
      }
      
      System.out.println();
      
      deque.enqueueBack(2);
      deque.enqueueBack(3);
      deque.enqueueBack(4);
      deque.enqueueBack(5);
      deque.enqueueBack(6);
      deque.enqueueBack(7);
      deque.enqueueBack(8);
      while(deque.size() != 0) {
        System.out.print(deque.dequeueFront() + " ");
      }
  }
} 