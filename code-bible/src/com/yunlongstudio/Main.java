package com.yunlongstudio;

import java.util.Random;

import com.yunlongstudio.algorithm.graph.DefaultGraph;
import com.yunlongstudio.algorithm.graph.Graph;
import com.yunlongstudio.algorithm.graph.GraphVisitor;
import com.yunlongstudio.algorithm.sort.BubbleSort;
import com.yunlongstudio.algorithm.sort.HeapSort;
import com.yunlongstudio.algorithm.sort.MergeSort;
import com.yunlongstudio.algorithm.sort.QuickSort;
import com.yunlongstudio.algorithm.sort.SelectSort;

public class Main {

  public static void main(String[] args) {
    double start, end;

    int[] testArray = generateIntArray(30000);
    start = System.currentTimeMillis();
    BubbleSort.sort(testArray);
    end = System.currentTimeMillis();
    System.out.println("Bubble Sort takes: " + (end - start) + "ms");

    testArray = generateIntArray(30000);
    start = System.currentTimeMillis();
    SelectSort.sort(testArray);
    end = System.currentTimeMillis();
    System.out.println("Select Sort takes: " + (end - start) + "ms");

    testArray = generateIntArray(30000);
    start = System.currentTimeMillis();
    QuickSort.sort(testArray, 0, 29999);
    end = System.currentTimeMillis();
    System.out.println("Quick Sort takes: " + (end - start) + "ms");

    testArray = generateIntArray(30000);
    start = System.currentTimeMillis();
    MergeSort.sort(testArray);
    end = System.currentTimeMillis();
    System.out.println("Merge Sort takes: " + (end - start) + "ms");

    testArray = generateIntArray(30000);
    start = System.currentTimeMillis();
    HeapSort.sort(testArray);
    end = System.currentTimeMillis();
    System.out.println("Heap Sort takes: " + (end - start) + "ms");
    
    
    
    // Graph
    
    DefaultGraph g=new DefaultGraph(9);
    g.setEdge(0, 1, 0);
    g.setEdge(0, 3, 0);
    g.setEdge(1, 2, 0);
    g.setEdge(4, 1, 0);
    g.setEdge(2, 5, 0);
    
    g.setEdge(3, 6, 0);
    g.setEdge(7, 4, 0);
    g.setEdge(5, 8, 0);
    g.setEdge(6, 7, 0);
    g.setEdge(8, 7, 0);
    
    //now we visit it
    GraphVisitor visitor=new GraphVisitor()
    {
        @Override
        public void visit(Graph g, int vertex) {
            System.out.print(g.getVertexLabel(vertex)+" ");
            
        }
        
    };
    System.out.println("DFS==============:");
    g.deepFirstTravel(visitor);
    System.out.println();
    System.out.println("BFS==============:");
    g.breathFirstTravel(visitor);
    System.out.println();
    
    int[] test = {9, 9, 9, 9, 6, 9};
    int[] results = transfer(test, test.length - 1);
    for (int i = 0; i < results.length; i++) {
    	System.out.print(results[i] + " ");
    }
  }

  private static int[] generateIntArray(int count) {
    Random r = new Random();

    int a[] = new int[count];
    for(int i = 0; i < count; i++) {
      a[i] = r.nextInt(10000);
    }

    return a;
  }

  /*
  int[] transfer(int[] param, int length) {
	 
	  if (param == null || param.length < 1 || param.length < length) {
		  return param;
	  }
	  
	  param[param.length - 1]++;
	  if (param[param.length - 1] > 9) {
		  param[param.length - 1] = 0;
		  transfer(param, param.length - 2);
	  }
	  
	  return param;
  }*/

  public static int[]  transfer(int[] param, int length) {
    if (param == null || param.length < 1 || param.length < length) {
       return param;
	 }
	  
	  param[length]++;
	  if (param[length] > 9) {
		  param[length] = 0;
          if (length < 1) {
            // create new array length+1 and copy ...
        	int[] newarrary = new int[param.length + 1];
        	newarrary[0] = 1;
        	System.arraycopy(param, 0, newarrary, 1, param.length);
            return newarrary; 
          } else {
            return transfer(param,length - 1);
          }
        }
      
	  return param;
  }


}
