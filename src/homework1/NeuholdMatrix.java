package homework1;
/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author Anton Neuhold, Ruben Acuna
 * @version 1.0
 */
public class NeuholdMatrix implements Matrix {
  private int[][] matrix;
    
  public NeuholdMatrix(int[][] matrix) {
    if (matrix.length == 0) {
      this.matrix = new int[0][0];
    } else {
      int tempMatrix[][] = new int[matrix.length][matrix[0].length];
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          tempMatrix[i][j] = matrix[i][j];
        }
      }
      this.matrix = tempMatrix;
    }
  }
  
  @Override
  public int getElement(int y, int x) {
    return matrix[x][y];
  }

  @Override
  public int getRows() {
    return matrix.length;
  }

  @Override
  public int getColumns() {
    if (matrix.length == 0) {
      return 0;
    } else {
      return matrix[0].length;
    }
  }

  @Override
  public Matrix scale(int scalar) {
    int[][] tempMatrix = new int[this.getRows()][this.getColumns()];
    
    // Multiply each value in the matrix by the scalar. 
    // Create a new matrix out of these values, and return it.
    for (int i = 0; i < this.getRows(); i++) {
      for (int j = 0; j < this.getColumns(); j++) {
        tempMatrix[i][j] = scalar * this.getElement(j, i);
      }
    }
    return new NeuholdMatrix(tempMatrix);
  }

  @Override
  public Matrix plus(Matrix other) {
    matchingMatrixCheck(this, other);
    int[][] tempMatrix = new int[this.getRows()][this.getColumns()];
    for (int i = 0; i < this.getRows(); i++) {
      for (int j = 0; j < this.getColumns(); j++) {
        tempMatrix[i][j] = this.getElement(j, i) + other.getElement(j, i);
      }
    }
    return new NeuholdMatrix(tempMatrix);
  }

  @Override
  public Matrix minus(Matrix other) {
    matchingMatrixCheck(this, other);
    int[][] tempMatrix = new int[this.getRows()][this.getColumns()];
    for (int i = 0; i < this.getRows(); i++) {
      for (int j = 0; j < this.getColumns(); j++) {
        tempMatrix[i][j] =  this.getElement(j, i) - other.getElement(j, i);
      }
    }
    return new NeuholdMatrix(tempMatrix);
  }
  
  /**
   * Checks if the matrices are of matching dimensions
   * @param matrix1 the first matrix
   * @param matrix2 the second matrix
   * @throws RuntimeException if they are of different dimensions.
   */
  private void matchingMatrixCheck(Matrix matrix1, Matrix matrix2) {
    if (matrix1.getRows() != matrix2.getRows() || 
        matrix1.getColumns() != matrix2.getColumns()) {
      throw new RuntimeException("Matrices of different dimensions");
    }
  }
  
  /**
   * Returns true if this matrix matches another matrix.
   * @param other another matrix
   * @return equality
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {return false;}
    if (this == other) {return true;}
    if (!(other instanceof Matrix)) {return false;}
    if (this.getRows() != ((Matrix)other).getRows() || 
        this.getColumns() != ((Matrix)other).getColumns()) {
      return false;
    }
    for (int i = 0; i < this.getRows(); i++) {
      for (int j = 0; j < this.getColumns(); j++) {
        if (this.getElement(j, i) != ((Matrix)other).getElement(j, i)) {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Returns a string representation of this matrix. A new line character will
   * separate each row, while a space will separate each column.
   * @return string representation
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i < this.getRows(); i++) {
      for (int j = 0; j < this.getColumns(); j++) {
        str += this.getElement(j, i) + " ";
      }
      str += "\n";
    }
    return str;
  }
    
  /**
   * Entry point for matrix testing.
   * @param args the command line arguments
   */
  @SuppressWarnings("unlikely-arg-type")
  public static void main(String[] args) {
    int[][] data1 = new int[0][0];
    int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
    
    Matrix m1 = new NeuholdMatrix(data1);
    Matrix m2 = new NeuholdMatrix(data2);
    Matrix m3 = new NeuholdMatrix(data3);
    
    System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
    System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
    System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
    
    //check for reference issues
    System.out.println("m2 -->\n" + m2);
    data2[1][1] = 101;
    System.out.println("m2 -->\n" + m2);

    //test equals
    System.out.println("m2==null: " + m2.equals(null));             //false
    System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
    System.out.println("m2==m1: " + m2.equals(m1));                 //false
    System.out.println("m2==m2: " + m2.equals(m2));                 //true
    System.out.println("m2==m3: " + m2.equals(m3));                 //false
    
    //test operations (valid)
    System.out.println("2 * m2:\n" + m2.scale(2));
    System.out.println("m2 + m3:\n" + m2.plus(m3));
    System.out.println("m2 - m3:\n" + m2.minus(m3));
    
    //test operations (invalid) tested these :-)
    //System.out.println("m1 + m2" + m1.plus(m2));
    //System.out.println("m1 - m2" + m1.minus(m2));
  }
}