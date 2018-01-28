package homework3;

import java.util.*;
import java.io.*;

/**
 * MazeTester uses recursion to determine if a maze can be traversed.
 *
 * @author Lewis and Chase
 * @author Anton G Neuhold Jr
 * @version 4.1
 */
public class MazeTester
{
    /**
     * Creates a new maze, prints its original form, attempts to
     * solve it, and prints out its final form.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        
        Position start = new Position();
        Position end = new Position();
        
        System.out.print("Enter the name of the file containing the maze: ");
        String filename = scan.nextLine();
        Maze labyrinth = new Maze(filename);
        
        boolean validStart = false;
        do {
          System.out.print("Enter the starting coordinate of the maze seperated "
              + "by a space (ex: \'0 2\') within the bounds of the maze: ");
          Scanner startScan = new Scanner(scan.nextLine());
          
          boolean completeInput = false;
          if (startScan.hasNextInt()) 
          {
            start.setx(startScan.nextInt());
          }
          
          if (startScan.hasNextInt()) 
          {
            start.sety(startScan.nextInt());
            completeInput = true;
          } 
          
          // coordinates start at 0, column and row counts start at 1
          if (start.getx() < labyrinth.getColumns() &&
              start.gety() < labyrinth.getRows() &&
              completeInput)
          {
            validStart = true;
          }
          startScan.close();
        } while (!validStart);
        
        boolean validEnd = false;
        do {
          System.out.print("Enter the ending coordinate of the maze seperated "
              + "by a space (ex: \'5 5\') within the bounds of the maze: ");
          Scanner endScan = new Scanner(scan.nextLine());
          
          boolean completeInput = false;
          if (endScan.hasNextInt()) 
          {
            end.setx(endScan.nextInt());
          }
          
          if (endScan.hasNextInt()) 
          {
            end.sety(endScan.nextInt());
            completeInput = true;
          } 
          
          if (end.getx() < labyrinth.getColumns() &&
              end.gety() < labyrinth.getRows() &&
              completeInput)
          {
            validEnd = true;
          }
          
          endScan.close();
        } while (!validEnd);
        scan.close();
        
      
        System.out.println(labyrinth);
        
        MazeSolver solver = new MazeSolver(labyrinth);

        if (solver.traverse(start, end))
            System.out.println("The maze was successfully traversed!");
        else
            System.out.println("There is no possible path.");

        System.out.println(labyrinth);
    }
}