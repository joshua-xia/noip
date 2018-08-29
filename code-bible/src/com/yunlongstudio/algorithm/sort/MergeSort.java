package com.yunlongstudio.algorithm.sort;

/**
 * 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 
 * 归并操作(merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作。
 * 算法描述[编辑]
 * 归并操作的过程如下：
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 重复步骤3直到某一指针达到序列尾
 * 将另一序列剩下的所有元素直接复制到合并序列尾
 * 
 * @author joshua
 * 
 */

public class MergeSort{

  public static int[] sort(int[] arr){//归并排序 --递归
    if (arr == null || arr.length <= 1) {
      return arr;
    }

    int half = arr.length/2;
    int[] arr1 = new int[half];
    int[] arr2 = new int[arr.length - half];

    System.arraycopy(arr, 0, arr1, 0, arr1.length);
    System.arraycopy(arr, half, arr2, 0, arr2.length);

    arr1 = sort(arr1);
    arr2 = sort(arr2);

    return merge(arr1, arr2);
  }

  private static int[] merge(int[] arr1, int[] arr2) {
    int[] result = new int[arr1.length + arr2.length];
    int i = 0;
    int j = 0;
    int k = 0;
    while(true) {
      if (arr1[i] < arr2[j]) {
        result[k] = arr1[i];
        if (++i > arr1.length - 1) {
          break;
        }
      } else {
        result[k] = arr2[j];
        if (++j > arr2.length - 1) {
          break;
        }
      }
      k++;
    }

    for(;i < arr1.length; i++){
      result[++k] = arr1[i];
    }
    for(;j < arr2.length; j++){
      result[++k] = arr2[j];
    }

    return result;
  }

}