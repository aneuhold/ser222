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
        
        // Testing add and remove
        System.out.println("List contents: " + list);
        list.add(1);
        list.add(9);
        list.add(22);
        list.add(5);
        System.out.println("List contents: " + list);
        System.out.println("Removing the first element: " + list.removeFirst());
        System.out.println("List contents: " + list);
        System.out.println("Removing the last element: " + list.removeLast());
        System.out.println("List Contents: " + list);
        list.add(45);
        list.add(7);
        list.add(7);
        list.add(7);
        System.out.println(list);
        System.out.println("Removing the 9: " + list.remove(9));
        System.out.println(list);
    }
}