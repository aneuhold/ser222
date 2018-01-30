import java.awt.List;

public class HomeworkTest {
  public static void main(String[] args) {
    int[] intArray = new int[10];
    int n =  1000;
    int actualCount = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j=j*2) {
        actualCount++;
      }
    }
    List list = new List();
    System.out.println(actualCount);
    double sum = n * (Math.log(n)/Math.log(2));
    System.out.println("Sum = " + sum);
  } 

}
