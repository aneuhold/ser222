import java.util.LinkedList;
import java.util.List;

public class HomeworkTest {
  public static void main(String[] args) {
    int n =  1000;
    int actualCount = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j=j*2) {
        actualCount++;
      }
    }
    
    List<Integer> list = new LinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    
    System.out.println(list);
    
    System.out.println(actualCount);
    double sum = n * (Math.log(n)/Math.log(2));
    System.out.println("Sum = " + sum);
  } 

}
