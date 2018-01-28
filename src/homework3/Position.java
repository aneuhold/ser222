package homework3;


/**
 * @author Lewis and Chase
 * @author Anton G Neuhold Jr
 */
public class Position
{
  private int x; 
  private int y;
  private Position prevPos;

  /**
   * Constructs a position and sets the x & y coordinates to 0,0.
   */
  Position ()
  {
    x = 0;
    y = 0;
    prevPos = null;
  }

  /**
   * Returns the x-coordinate value of this position.
   * @return int the x-coordinate of this position
   */
  public int getx()
  {
    return x;
  }

  /**
   * Returns the y-coordinate value of this position.
   * @return int the y-coordinate of this position
   */
  public int gety()
  {
    return y;
  }
  
  /**
   * Returns the previous position to this one
   * @return Position the parent position to this position
   */
  public Position getParent() 
  {
    return prevPos;
  }
  
  /**
   * Sets the parent position of this position.
   * @param parent the previous position to this one
   */
  public void setParent(Position parent) 
  {
    prevPos = parent;
  }

  /**
   * Sets the value of the current position's x-coordinate.
   * @param a value of x-coordinate
   */
  public void setx(int a)
  {
    x = a;
  }

   /**
   * Sets the value of the current position's x-coordinate.
   * @param a value of y-coordinate
   */ 
  public void sety(int a)
  {
    y = a;
  }
  
  /**
   * This tests to see if an object is equal to this position object, or 
   * if their coordinates match.
   */
  @Override
  public boolean equals(Object other) 
  {
    if (other == null) {return false;}
    if (this == other) {return true;}
    if (!(other instanceof Position)) {return false;}
    if (this.getx() == ((Position)other).getx() && 
        this.gety() == ((Position)other).gety()) 
    {
      return true;
    } 
    else 
    {
      return false;
    }
  }
}