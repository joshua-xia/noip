package com.yunlongstudio.algorithm.sort;

/**
 * 选择排序(Selection sort)是一种简单直观的排序算法。
 * 它的工作原理如下。首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。
 * 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，因此对n个元素的表进行排序总共进行至多n-1次交换。
 * 在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 * 
 * 复杂度分析
 * 选择排序的交换操作介于0和(n-1)次之间。选择排序的比较操作为n(n-1)/2次之间。选择排序的赋值操作介于0和3(n-1)次之间。
 * 比较次数O(n^2),比较次数与关键字的初始状态无关，总的比较次数N=(n-1)+(n-2)+...+1=n*(n-1)/2。 
 * 交换次数O(n),最好情况是，已经有序，交换0次；最坏情况是，逆序，交换n-1次。 
 * 交换次数比冒泡排序少多了，由于交换所需CPU时间比比较所需的CPU时间多，n值较小时，选择排序比冒泡排序快。
 * 
 * @author joshua
 *
 */

public class SelectSort {

  public static void sort(int[] array) {
    int temp;
    int min;
    for (int index = 0; index < array.length; index++) {
      //假定第一个元素为最小元素
      min = index;
      //循环遍历元素，每遍历一个元素，与当前最小元素比较，若此元素比当前最小元素小，则将此元素置为最小元素
      for (int i = index + 1; i < array.length; i++) {
        if (array[i] < array[min]) {
          min = i;
        }
      }
      //遍历一遍，找到一个最小元素，把此最小元素放在数组的第一个位置
      if (min != index) {
        temp = array[index];
        array[index] = array[min];
        array[min] = temp;
      }
    }
  }

}
