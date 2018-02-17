package homework10;

import java.util.Iterator;
import java.util.Random;

/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Lewis and Chase
 * @author Anton G Neuhold Jr
 * @version 5.0
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> 
implements BinarySearchTreeADT<T> {

  /**
   * Creates an empty binary search tree.
   */
  public LinkedBinarySearchTree() 
  {
    super();
  }

  /**
   * Creates a binary search with the specified element as its root.
   *
   * @param element the element that will be the root of the new binary
   *        search tree
   */
  public LinkedBinarySearchTree(T element) 
  {
    super(element);
 
    if (!(element instanceof Comparable))
      throw new NonComparableElementException("LinkedBinarySearchTree");
  }

  /**
   * Adds the specified object to the binary search tree in the
   * appropriate position according to its natural order.  Note that
   * equal elements are added to the right.
   *
   * @param element the element to be added to the binary search tree
   */
  @Override
  public void addElement(T element) 
  {
    if (!(element instanceof Comparable))
      throw new NonComparableElementException("LinkedBinarySearchTree");

    Comparable<T> comparableElement = (Comparable<T>)element;

    if (isEmpty())
      root = new BinaryTreeNode<T>(element);
    else 
    {
      if (comparableElement.compareTo(root.getElement()) < 0)
      {
        if (root.getLeft() == null) 
          this.getRootNode().setLeft(new BinaryTreeNode<T>(element));
        else
          addElement(element, root.getLeft());
      }
      else
      {
        if (root.getRight() == null) 
          this.getRootNode().setRight(new BinaryTreeNode<T>(element));
        else
          addElement(element, root.getRight());
      }
    }
    modCount++;
  }

  /**
   * Adds the specified object to the binary search tree in the
   * appropriate position according to its natural order.  Note that
   * equal elements are added to the right.
   *
   * @param element the element to be added to the binary search tree
   */
  private void addElement(T element, BinaryTreeNode<T> node) 
  {
    Comparable<T> comparableElement = (Comparable<T>)element;

    if (comparableElement.compareTo(node.getElement()) < 0)
    {
      if (node.getLeft() == null) 
        node.setLeft(new BinaryTreeNode<T>(element));
      else
        addElement(element, node.getLeft());
    }
    else
    {
      if (node.getRight() == null) 
        node.setRight(new BinaryTreeNode<T>(element));
      else
        addElement(element, node.getRight());
    }
  }


  /**
   * Removes the first element that matches the specified target
   * element from the binary search tree and returns a reference to
   * it.  Throws a ElementNotFoundException if the specified target
   * element is not found in the binary search tree.
   *
   * @param targetElement the element being sought in the binary search tree
   * @throws ElementNotFoundException if the target element is not found
   */
  @Override
  public T removeElement(T targetElement)
      throws ElementNotFoundException 
  {
    T result = null;

    if (isEmpty())
      throw new ElementNotFoundException("LinkedBinarySearchTree");
    else
    {
      BinaryTreeNode<T> parent = null;
      if (((Comparable<T>)targetElement).equals(root.element)) 
      {
        result =  root.element;
        BinaryTreeNode<T> temp = replacement(root);
        if (temp == null)
          root = null;
        else 
        {
          root.element = temp.element;
          root.setRight(temp.right);
          root.setLeft(temp.left);
        }

        modCount--;
      }
      else 
      {                
        parent = root;
        if (((Comparable)targetElement).compareTo(root.element) < 0)
          result = removeElement(targetElement, root.getLeft(), parent);
        else
          result = removeElement(targetElement, root.getRight(), parent);
      }
    }

    return result;
  }

  /**
   * Removes the first element that matches the specified target
   * element from the binary search tree and returns a reference to
   * it.  Throws a ElementNotFoundException if the specified target
   * element is not found in the binary search tree.
   *
   * @param targetElement the element being sought in the binary search tree
   * @param node the node from which to search
   * @param parent the parent of the node from which to search
   * @throws ElementNotFoundException if the target element is not found
   */
  private T removeElement(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
      throws ElementNotFoundException 
  {
    T result = null;

    if (node == null)
      throw new ElementNotFoundException("LinkedBinarySearchTree");
    else
    {
      if (((Comparable<T>)targetElement).equals(node.element)) 
      {
        result =  node.element;
        BinaryTreeNode<T> temp = replacement(node);
        if (parent.right == node)
          parent.right = temp;
        else 
          parent.left = temp;

        modCount--;
      }
      else 
      {                
        parent = node;
        if (((Comparable)targetElement).compareTo(node.element) < 0)
          result = removeElement(targetElement, node.getLeft(), parent);
        else
          result = removeElement(targetElement, node.getRight(), parent);
      }
    }

    return result;
  }

  /**
   * Returns a reference to a node that will replace the one
   * specified for removal.  In the case where the removed node has 
   * two children, the inorder successor is used as its replacement.
   *
   * @param node the node to be removed
   * @return a reference to the replacing node
   */
  private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) 
  {
    BinaryTreeNode<T> result = null;

    if ((node.left == null) && (node.right == null))
      result = null;

    else if ((node.left != null) && (node.right == null))
      result = node.left;

    else if ((node.left == null) && (node.right != null))
      result = node.right;

    else
    {
      BinaryTreeNode<T> current = node.right;
      BinaryTreeNode<T> parent = node;

      while (current.left != null)
      {
        parent = current;
        current = current.left;
      }

      current.left = node.left;
      if (node.right != current)
      {
        parent.left = current.right;
        current.right = node.right;
      }

      result = current;
    }

    return result;
  }

  /**
   * Removes elements that match the specified target element from 
   * the binary search tree. Throws a ElementNotFoundException if 
   * the specified target element is not found in this tree.
   *
   * @param targetElement the element being sought in the binary search tree
   * @throws ElementNotFoundException if the target element is not found
   */
  @Override
  public void removeAllOccurrences(T targetElement)
      throws ElementNotFoundException 
  {
    removeElement(targetElement);

    try
    {
      while (contains((T)targetElement))
        removeElement(targetElement);
    }

    catch (Exception ElementNotFoundException)
    {
    }
  }

  /**
   * Removes the node with the least value from the binary search
   * tree and returns a reference to its element.  Throws an
   * EmptyCollectionException if this tree is empty. 
   *
   * @return a reference to the node with the least value
   * @throws EmptyCollectionException if the tree is empty
   */
  @Override
  public T removeMin() throws EmptyCollectionException 
  {
    T result = null;

    if (isEmpty())
      throw new EmptyCollectionException("LinkedBinarySearchTree");
    else 
    {
      if (root.left == null) 
      {
        result = root.element;
        root = root.right;
      }
      else 
      {
        BinaryTreeNode<T> parent = root;
        BinaryTreeNode<T> current = root.left;
        while (current.left != null) 
        {
          parent = current;
          current = current.left;
        }
        result =  current.element;
        parent.left = current.right;
      }

      modCount--;
    }

    return result;
  }

  /**
   * Returns a reference to the specified target element if it is
   * found in this binary tree.  Throws a ElementNotFoundException if
   * the specified target element is not found in the binary tree.
   *
   * @param targetElement the element being sought in this tree
   * @return a reference to the specified target
   * @throws ElementNotFoundException if the element is not in the tree
   */
  @Override
  public T find(T targetElement) throws ElementNotFoundException {
    BinaryTreeNode<T> current = root;
    Comparable targetEl = (Comparable)targetElement;
    
    while (current != null && !current.getElement().equals(targetElement)) {
      Comparable currentEl = (Comparable)current.getElement();
      if (targetEl.compareTo(currentEl) < 0)
        current = current.getLeft();
      else 
        current = current.getRight();
    }
    
    if (current == null)
      throw new ElementNotFoundException(this.getClass().toString());
    
    return current.getElement();
  }

  /**
   * Returns true if this tree contains an element that matches the
   * specified target element and false otherwise.
   *
   * @param targetElement the element being sought in this tree
   * @return true if the element in is this tree, false otherwise
   */
  @Override
  public boolean contains(T targetElement) 
  {
    try {
      find(targetElement);
      return true;
    } catch (ElementNotFoundException e) {
      return false;
    }
  }

  /**
   * Removes the node with the highest value from the binary
   * search tree and returns a reference to its element.  Throws an
   * EmptyCollectionException if this tree is empty. 
   *
   * @return a reference to the node with the highest value
   * @throws EmptyCollectionException if the tree is empty
   */
  @Override
  public T removeMax() { // Implemented because the ADT requires it
    T max = findMax();
    removeElement(max);
    return max;
  }

  /**
   * Returns the element with the least value in the binary search
   * tree. It does not remove the node from the binary search tree. 
   * Throws an EmptyCollectionException if this tree is empty.
   *
   * @return the element with the least value
   * @throws EmptyCollectionException if the tree is empty
   */
  @Override
  public T findMin() { // Implemented because the ADT requires it
    if (isEmpty())
      throw new EmptyCollectionException(this.getClass().toString());
    
    BinaryTreeNode<T> current = root;
    while (current.getLeft() != null)
      current = current.getLeft();
    
    return current.getElement();
  }

  /**
   * Returns the element with the highest value in the binary
   * search tree.  It does not remove the node from the binary
   * search tree.  Throws an EmptyCollectionException if this 
   * tree is empty.
   *
   * @return the element with the highest value
   * @throws EmptyCollectionException if the tree is empty
   */
  @Override //Implemented because the ADT requires it
  public T findMax() throws EmptyCollectionException { 
    if (isEmpty())
      throw new EmptyCollectionException(this.getClass().toString());
    
    BinaryTreeNode<T> current = root;
    while (current.getRight() != null)
      current = current.getRight();
    
    return current.getElement();
  }
  
  /**
   * Driver for Unit 10 Homework
   * 
   * @param args
   */
  public static void main(String[] args) {
    // ArrayUnorderedList
    ArrayUnorderedList<Integer> aL = new ArrayUnorderedList();
    System.out.println("The array list so far: " + aL);
    System.out.println("Adding elements...");
    aL.addToRear(12);
    aL.addToRear(13);
    System.out.println("The array list so far: " + aL);
    aL.addToFront(21);
    aL.addToFront(33);
    System.out.println("The array list so far: " + aL + "\n");
    
    // ArrayList
    System.out.println("Removing elements...");
    aL.removeFirst();
    System.out.println("After removing the first element: " + aL);
    aL.removeLast();
    System.out.println("After removing the last element: " + aL);
    aL.removeFirst();
    aL.removeLast();
    System.out.println("After removing all elements: " + aL);
    // aL.removeFirst();
    System.out.println("Adding more elements...");
    aL.addToFront(35); aL.addToFront(22); aL.addToFront(37); 
    aL.addToFront(93); aL.addToFront(5); aL.addToFront(16); 
    System.out.println("The array list so far: " + aL);
    // Iterator itr = aL.iterator();
    // System.out.println("The first element returned by the iterator is: " + itr.next());
    // System.out.println(aL.removeFirst() + " has been removed from the underlying list...");
    // System.out.println("The next method is being called from the iterator: " + itr.next());
    System.out.println("Creating a new list with size 2...");
    ArrayUnorderedList<Integer> aLSmall = new ArrayUnorderedList(2);
    System.out.println("Adding values...");
    aLSmall.addToFront(33); aLSmall.addToFront(44); aLSmall.addToFront(55);
    aLSmall.addToFront(33); aLSmall.addToFront(44); aLSmall.addToFront(55);
    System.out.println("The small array list so far: " + aL + "\n");
    
    // LinkedBinaryTree
    LinkedBinaryTree lbt = new LinkedBinaryTree(5, new LinkedBinaryTree(8), new LinkedBinaryTree(13));
    System.out.println("The tree so far printed inorder: " + lbt);
    System.out.println("Making the tree a little taller...");
    lbt = new LinkedBinaryTree(10, lbt, new LinkedBinaryTree(
        11, new LinkedBinaryTree(33), new LinkedBinaryTree(55))
    );
    System.out.println("The tree so far printed inorder: " + lbt);
    System.out.println("The root element is: " + lbt.getRootElement() +
        " which should be the same as: " + lbt.getRootNode().getElement());
    System.out.println("The right tree is: " + lbt.getRight() +
        " and the left tree is: " + lbt.getLeft() +
        " printed inorder.");
    System.out.println("The size of the tree is: " + lbt.size());
    System.out.println("The height of the tree is: " + lbt.getHeight());
    System.out.println("Does the tree contain 11? " + lbt.contains(11) +
        " What about 5? " + lbt.contains(5));
    System.out.println("Printing the tree using the preorder iterator: ");
    Iterator lbtItr = lbt.iteratorPreOrder();
    while (lbtItr.hasNext())
      System.out.print(lbtItr.next() + " ");
    System.out.println("\n");
    
    // LinkedBinarySearchTree
    System.out.println("Making a new LinkedBinarySearchTree...");
    LinkedBinarySearchTree lbst = new LinkedBinarySearchTree(5);
    lbst.addElement(7); lbst.addElement(10); lbst.addElement(3);
    lbst.addElement(25); lbst.addElement(1);
    System.out.println("The original tree inorder: " + lbst);
    System.out.println("The left tree is: " + lbst.getLeft() +
        " and the right tree is: " + lbst.getRight());
    lbst.removeMax();
    System.out.println("After removing max, the tree is: " + lbst);
    lbst.removeMin();
    System.out.println("After removing min, the tree is: " + lbst);
    System.out.println("Adding more elements...");
    lbst.addElement(8); lbst.addElement(45); lbst.addElement(6);
    lbst.addElement(2);
    System.out.println("The tree is now: " + lbst);
    System.out.println("Does the tree contain 10? " + lbst.contains(10) +
        "\nDoes it contain 4? " + lbst.contains(4));
    System.out.println("Finding 45 results in: " + lbst.find(45));
    // System.out.println("Finding 99 results in: " + lbst.find(99));
    System.out.println("The tree is now: " + lbst + "\n");
    
    // Benchmarking
    System.out.println("Benchmarking...");
    Random rand = new Random();
    LinkedBinarySearchTree<Integer> lbstBench = new LinkedBinarySearchTree();
    LinkedBinaryTree<Integer> lbtBench;
    for (int i = 0; i < 1000; i++)
      lbstBench.addElement(rand.nextInt(1000));
    lbtBench = new LinkedBinaryTree(lbstBench.root.getElement(), lbstBench.getLeft(), lbstBench.getRight());
    
    System.out.println("The LinkedBinarySearchTree contains: \t" + lbstBench + "\n");
    System.out.println("The LinkedbinaryTree contains: \t\t" + lbtBench);
    
    Integer[] searchArray = new Integer[1000]; // Creating search values
    for (int i = 0; i < searchArray.length; i++) 
      searchArray[i] = rand.nextInt();
    
    double lbstTime = System.currentTimeMillis();
    for (int search : searchArray)
      lbstBench.contains(search);
    lbstTime = System.currentTimeMillis() - lbstTime;
    System.out.println("The time passed for the LinkedBinarySearchTree is: " + lbstTime + "ms");
    
    double lbtTime = System.currentTimeMillis();
    for (int search : searchArray)
      lbtBench.contains(search);
    lbtTime = System.currentTimeMillis() - lbtTime;
    System.out.println("The time passed for the LinkedBinaryTree is: " + lbtTime + "ms");
  }
}