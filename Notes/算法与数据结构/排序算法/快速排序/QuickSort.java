package com.wuwei.yanyi.test;

/**
 * @ClassName QuickSort
 * @Description: TODO 快速排序
 * @Author beyondyanyi@gmail.com
 * @Date 2021/4/23
 * @Version V1.0
 **/
public class QuickSort {
    private int[] array;
    public QuickSort(int[] array) {
        this.array = array;
    }
    public void sort() {
        quickSort(array, 0, array.length - 1);
    }
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 递归排序
     * @param src
     * @param begin
     * @param end
     */
    private void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            int key = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                //找出基准数左边的数
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                //找出基准数右边的数
                while (i < j && src[i] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            //基准数
            src[i] = key;
            //递归
            quickSort(src, begin, i - 1);
            quickSort(src, i + 1, end);
        }
    }
}
