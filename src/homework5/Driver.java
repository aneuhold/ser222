package homework5;

/**
 * DoubleOrderedList testing area.
 * 
 * @author Anton Neuhold, Acuna
 * @version 1.0
 */
public class Driver {
    public static void main(String [] args) {
        DoubleOrderedList<Integer> list = new DoubleOrderedList<>();
        
        System.out.println("List contents: " + list);
        System.out.println("Adding elements...");
        list.add(1);
        list.add(9);
        list.add(22);
        list.add(5);
        System.out.println("List contents: " + list + "\n");
        System.out.println("Removing the first element which is: " + list.removeFirst());
        System.out.println("Result: " + list);
        System.out.println("Removing the last element which is: " + list.removeLast());
        System.out.println("Result:  " + list + "\n");
        
        System.out.println("Adding elements...");
        list.add(45);
        list.add(7);
        list.add(7);
        list.add(7);
        System.out.println("List contents: " + list + "\n");
        
        System.out.println("Attempting to remove 9 from the list...");
        list.remove(9);
        System.out.println("Result: " + list);
        
        System.out.println("Removing all elements");
        while (list.size() != 0) {
          list.removeFirst();
        }
        System.out.println("Result: " + list + "\n");
        
        System.out.println("Adding elements...");
        list.add(80);
        list.add(22);
        list.add(33);
        list.add(92);
        list.add(4);
        System.out.println("List contents: " + list);
        
        System.out.println("The first element is: " + list.first());
        System.out.println("The last element is: " + list.last());
        System.out.println("Does the list contain 80?: " + list.contains(80));
        System.out.println("Does the list contain 7?: " + list.contains(7) + "\n");
        
        System.out.println("Removing 92...");
        list.remove(92);
        System.out.println("Result: " + list);
        System.out.println("Removing 4...");
        list.remove(4);
        System.out.println("Result: " + list);
        System.out.println("Removing the rest...");
        list.remove(22); list.remove(33); list.remove(80);
        System.out.println("Result: " + list);
        
        list.add(22);
        list.removeLast();
        
        // Exceptions testing
        
    }
}