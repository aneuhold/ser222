package homework1;
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Read a sentence in from a user
 * Print each character backwards using a stack
 * @author Anton G Neuhold Jr
 */

public class SentenceStacker {
  public static void main(String args[]) {
    String text;
    Scanner in = new Scanner(System.in);
    
    System.out.println("Please enter a sentence: ");
    text = in.nextLine();
    in.close();
    
    Scanner parser = new Scanner(text);
    while (parser.hasNext()) {
      String word = parser.next();
      System.out.print(characterReverse(word) + " ");
    }
    parser.close();
    
  }
  
  private static String characterReverse(String word) {
    Deque<Character> sentenceStack = new LinkedList<Character>();
    
    for (int i = 0; i < word.length(); i++) {
      sentenceStack.push(word.charAt(i));
    }
    
    String reversedWord = "";
    for (char a : sentenceStack) {
      reversedWord += a;
    }
    
    return reversedWord;
  }
}
