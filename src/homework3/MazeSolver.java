package homework3;


import java.util.*;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1's. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *
 * @author Lewis and Chase
 * @author Anton G Neuhold Jr
 * @version 4.1
 */
public class MazeSolver
{
    private Maze maze;
    
    /**
     * Constructor for the MazeSolver class.
     */
    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }
    
    /**
     * Attempts to recursively traverse the maze. Inserts special
     * characters indicating locations that have been TRIED and that
     * eventually become part of the solution PATH.
     *
     * @param start the start position in the maze
     * @param end the end position in the maze
     * @return true if the maze has been solved
     */
    public boolean traverse(Position start, Position end)
    {
        boolean done = false;
        Position pos = start;
        Deque<Position> optionsStack = new LinkedList<Position>();
        optionsStack.push(pos);
        
        while (!(done) && !optionsStack.isEmpty())
        {
            pos = optionsStack.pop();
            maze.tryPosition(pos.gety(),pos.getx());  // this cell has been tried
            if (pos.equals(end)) 
            {
              done = true;  // the maze is solved
              markPath(pos);
            }
            else
            {
              push_new_pos(pos.getx() - 1,pos.gety(), optionsStack, pos);
              push_new_pos(pos.getx() + 1,pos.gety(), optionsStack, pos);
              push_new_pos(pos.getx(),pos.gety() - 1, optionsStack, pos);
              push_new_pos(pos.getx(),pos.gety() + 1, optionsStack, pos);
            }
        }
        
        return done;
    }
    
    /**
     * Push a new attempted move onto the stack
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param stack the working stack of moves within the grid
     * @return stack of moves within the grid
     */
    private void push_new_pos(int x, int y, Deque<Position> stack, Position parent)
    {
        Position npos = new Position();
        npos.setx(x);
        npos.sety(y);
        npos.setParent(parent);
        if (maze.validPosition(y,x))
            stack.push(npos);
    }
    
    private void markPath(Position end) 
    {
      maze.markPath(end.gety(), end.getx());
      
      Position pos = end;
      while (pos.getParent() != null) {
        pos = pos.getParent();
        maze.markPath(pos.gety(), pos.getx());
      }
    }
    
}