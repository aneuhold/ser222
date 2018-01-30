package homework5;


public class DoubleNode<T> {
  private DoubleNode<T> next;
  private DoubleNode<T> previous;
  private T element;
  
  /**
   * Creates an empty node
   */
  public DoubleNode() {
    next = null;
    previous = null;
    element = null;
  }
  
  /**
   * Creates a node with the specified element
   * @param element the chosen element
   */
  public DoubleNode(T element) {
    next = null;
    previous = null;
    this.element = element;
  }
  
  public DoubleNode<T> getNext() {
    return this.next;
  }
  
  public DoubleNode<T> getPrevious() {
    return this.previous;
  }
  
  public T getElement() {
    return this.element;
  }
  
  public void setNext(DoubleNode<T> node) {
    this.next = node;
  }
  
  public void setPrevious(DoubleNode<T> node) {
    this.previous = node;
  }
  
  public void setElement(T element) {
    this.element = element;
  } 
}