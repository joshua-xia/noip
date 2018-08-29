package com.yunlongstudio.algorithm.sort;

/**
 * 快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序n个项目要Ο(nlogn)次比较。
 * 在最坏状况下则需要Ο(n2)次比较，但这种状况并不常见。事实上，快速排序通常明显比其他Ο(nlogn) 算法更快，
 * 因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来。
 * 
 * 快速排序使用分治法（Divide and conquer）策略来把一个串行（list）分为两个子串行（sub-lists）。
 * 步骤为：
 * 从数列中挑出一个元素，称为 "基准"（pivot），
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。虽然一直递归下去，但是这个算法总会退出，
 * 因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
 **/

public class QuickSort {

  public static void sort(int[] array, int from, int to) { 
    if (from < to) { 
      int temp = array[to]; 
      int i = from - 1; 
      for (int j = from; j < to; j++) { 
        if (array[j] <= temp) { 
          i++; 
          int tempValue = array[j]; 
          array[j] = array[i]; 
          array[i] = tempValue; 
        } 
      } 

      array[to] = array[i+1]; 
      array[i+1] = temp; 

      sort(array, from, i); 
      sort(array, i + 1, to); 
    } 
  } 

}
