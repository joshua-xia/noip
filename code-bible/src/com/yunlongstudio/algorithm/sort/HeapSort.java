package com.yunlongstudio.algorithm.sort;

/**
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：
 * 即子结点的键值或索引总是小于（或者大于）它的父节点。
 * 
 * 通常堆是通过一维数组来实现的。在起始数组为 0 的情形中：
 * 父节点i的左子节点在位置 (2*i+1);
 * 父节点i的右子节点在位置 (2*i+2);
 * 子节点i的父节点在位置 floor((i-1)/2);
 * 堆的操作[编辑]
 * 
 * 在堆的数据结构中，堆中的最大值总是位于根节点。堆中定义以下几种操作：
 * 最大堆调整（Max_Heapify）：将堆的末端子节点作调整，使得子节点永远小于父节点
 * 创建最大堆（Build_Max_Heap）：将堆所有数据重新排序
 * 堆排序（HeapSort）：移除位在第一个数据的根节点，并做最大堆调整的递归运算
 * 
 * 堆排序的平均时间复杂度为O(nlogn)，空间复杂度为 delta(1)
 * 
 * @author joshua
 *
 */
public class HeapSort {

  public static void sort(int[] array) {
    buildMaxHeapify(array);
    heapSort(array);
  }

  private static void buildMaxHeapify(int[] data){
    //没有子节点的才需要创建最大堆，从最后一个的父节点开始
    int startIndex = getParentIndex(data.length - 1);
    //从尾端开始创建最大堆，每次都是正确的堆
    for (int i = startIndex; i >= 0; i--) {
      maxHeapify(data, data.length, i);
    }
  }

  /**
   * 创建最大堆
   * @param data
   * @param heapSize 需要创建最大堆的大小，一般在sort的时候用到，因为最多值放在末尾，末尾就不再归入最大堆了
   * @param index 当前需要创建最大堆的位置
   */
  private static void maxHeapify(int[] data, int heapSize, int index){
    // 当前点与左右子节点比较
    int left = getChildLeftIndex(index);
    int right = getChildRightIndex(index);

    int largest = index;
    if (left < heapSize && data[index] < data[left]) {
      largest = left;
    }
    if (right < heapSize && data[largest] < data[right]) {
      largest = right;
    }
    //得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
    if (largest != index) {
      int temp = data[index];
      data[index] = data[largest];
      data[largest] = temp;
      maxHeapify(data, heapSize, largest);
    }
  }

  /**
   * 排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
   * @param data
   */
  private static void heapSort(int[] data){
    //末尾与头交换，交换后调整最大堆
    for (int i = data.length - 1; i > 0; i--) {
      int temp = data[0];
      data[0] = data[i];
      data[i] = temp;
      maxHeapify(data, i, 0);
    }
  }

  /**
   * 父节点位置
   * @param current
   * @return
   */
  private static int getParentIndex(int current){
    return (current - 1) >> 1;
  }

  /**
   * 左子节点position 注意括号，加法优先级更高
   * @param current
   * @return
   */
  private static int getChildLeftIndex(int current){
    return (current << 1) + 1;
  }

  /**
   * 右子节点position
   * @param current
   * @return
   */
  private static int getChildRightIndex(int current){
    return (current << 1) + 2;
  }

}
