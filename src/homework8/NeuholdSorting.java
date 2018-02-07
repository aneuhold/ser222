package homework8;

import java.util.Arrays;
import java.util.Random;

/**
 * Implements various sorting algorithms.
 * 
 * @author Anton Neuhold, Acuna, Sedgewick
 * @verison 1.0
 */

public class NeuholdSorting {
     
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Q1
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.print("Before sorting: "); show(a);
        quicksortmid(a);
        assert isSorted(a); //requires assertions enabled.
        System.out.print("After sorting with quicksort: "); show(a);
        
        System.out.println();
        
        //Q2
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        System.out.print("Before sorting: "); show(b);
        mergesort(b);
        assert isSorted(b);
        System.out.print("After sorting with mergesort: "); show(b);
        System.out.println();
        
        // Small arrays
        Integer[] c = {25,10};
        showQuickSort(c);
        Integer[] d = {25,10};
        showMergeSort(d);
        Integer[] e = {2};
        showQuickSort(e);
        Integer[] f = {2};
        showMergeSort(f);
        Integer[] g = {};
        showQuickSort(g);
        Integer[] h = {};
        showMergeSort(h);
        
        // Large arrays
        Random rand = new Random();
        Integer[] lgA = new Integer[70];
        for (int i = 0; i < lgA.length; i++) { lgA[i] = rand.nextInt(100); }
        showQuickSort(lgA);
        Integer[] lgB = new Integer[70];
        for (int i = 0; i < lgB.length; i++) { lgB[i] = rand.nextInt(100); }
        showMergeSort(lgB);
    }
    
    private static void showMergeSort(Comparable[] a) {
      System.out.print("Before sorting: "); show(a);
      mergesort(a);
      System.out.print("After sorting with mergesort: "); show(a);
      System.out.println();
    }
    
    private static void showQuickSort(Comparable[] a) {
      System.out.print("Before sorting: "); show(a);
      quicksortmid(a);
      System.out.print("After sorting with quicksort: "); show(a);
      System.out.println();
    }
    
    public static void quicksortmid(Comparable[] a) {
      quickSort(a, 0, a.length - 1);
    }
    
    private static void quickSort(Comparable[] a, int min, int max) {
      if (min < max) {
        int partitionIndex = partition(a, min, max);
        quickSort(a, min, partitionIndex - 1);
        quickSort(a, partitionIndex + 1, max);
      }
    }
    
    private static int partition(Comparable[] a, int min, int max) {
      Comparable partitionElement;
      int left, right, partitionElementIndex;
      int middle = (min + max) / 2;
      
      // Choosing the middle out of 3
      if (a[min].compareTo(a[middle]) <= 0
          && a[middle].compareTo(a[max]) <= 0) {
        partitionElementIndex = middle;
      } else if (a[middle].compareTo(a[min]) <= 0
          && a[min].compareTo(a[max]) <= 0) {
        partitionElementIndex = min;
      } else {
        partitionElementIndex = max;
      }
      partitionElement = a[partitionElementIndex];
      
      swap(a, partitionElementIndex, min);
      left = min;
      right = max;
      
      while (left < right) {
        while (left < right && a[left].compareTo(partitionElement) <= 0)
          left++;
        while (a[right].compareTo(partitionElement) > 0)
          right--;
        if (left < right)
          swap(a, left, right);
      }
      
      swap(a, min, right);
      
      return right;
    }
    
    private static void swap(Comparable[] a, int index1, int index2) {
      Comparable temp = a[index1];
      a[index1] = a[index2];
      a[index2] = temp;
    }
    
    public static void mergesort(Comparable[] a) {
      Comparable[] temp = mergeSort(a);
      for (int i = 0; i < a.length; i++) {
        a[i] = temp[i];
      }
    }
    
    public static Comparable[] mergeSort(Comparable[] a) {
      if (a.length > 1) {
        int mid = (a.length) / 2;
        Comparable[] leftA = mergeSort(Arrays.copyOfRange(a, 0, mid));
        Comparable[] rightA = mergeSort(Arrays.copyOfRange(a, mid, a.length));
        a = merge(leftA, rightA);
      }
      return a;
    }
    
    public static Comparable[] merge(Comparable[] a, Comparable[] b) {
      Comparable[] temp = new Comparable[a.length + b.length];
      
      int firsta = 0, lasta = a.length - 1;
      int firstb = 0, lastb = b.length - 1;
      int index = firsta;
      
      while (firsta <= lasta && firstb <= lastb) {
        if (a[firsta].compareTo(b[firstb]) < 0) {
          temp[index] = a[firsta];
          firsta++;
        } else {
          temp[index] = b[firstb];
          firstb++;
        }
        index++;
      }
      
      while (firsta <= lasta) {
        temp[index] = a[firsta];
        firsta++;
        index++; 
      }
      
      while (firstb <= lastb) {
        temp[index] = b[firstb];
        firstb++;
        index++;
      }

      return temp;
    }
    
    /**
     * Displays contents of array, space separated.
     * @author Sedgewick
     * @param a Array to display.
     */
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    /**
     * Checks if array is in sorted order.
     * @author Sedgewick
     * @param a Array to be checked.
     * @return Returns true if array is sorted.
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    
    //See previous method.
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
