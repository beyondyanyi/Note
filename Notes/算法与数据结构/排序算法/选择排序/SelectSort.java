package com.wuwei.yanyi.test;

/**
 * @ClassName SelectSort
 * @Description: TODO 选择排序
 * @Author beyondyanyi@gmail.com
 * @Date 2021/4/26
 * @Version V1.0
 **/
public class SelectSort {

    private int[] array;
    public SelectSort(int[] array) {
        this.array = array;
    }
    public void sort() {
        select(array);
    }
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    private void select(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            //默认最大数为i
            int maxIndex=i;
            int maxNum=array[i];
            //交换最大数
            for (int j = i+1; j < array.length-1; j++) {
                if(maxNum<array[j]){
                    maxIndex=j;
                    maxNum=array[j];
                }
            }
            //交换最大数到前面
            if(maxIndex!=i){
                int mid=array[i];
                array[i]=array[maxIndex];
                array[maxIndex]=mid;
            }
        }
    }

}
